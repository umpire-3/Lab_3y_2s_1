import Physics.Ball;
import Physics.Scene;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

import Physics.Vector;
import org.json.*;

import static java.lang.Thread.sleep;

/**
 * Created by Umpire on 17.03.2016.
 */
@ServerEndpoint("/server")
public class Server {

    private static ArrayList<Session> peers = new ArrayList<Session>();
    private static Scene scene = new Scene();
    private static boolean Runtime = true;

    static{
        new Thread(new Runnable() {
            public void run() {
                while(Runtime){
                    scene.Update(0.02f);
                    for(Session s : peers){
                        JSONArray msg = new JSONArray();
                        for(Ball b : scene.balls){
                            JSONArray ball = new JSONArray();
                            ball.put(b.position.x);
                            ball.put(b.position.y);
                            ball.put(b.position.z);
                            msg.put(ball);
                        }
                        try {
                            s.getBasicRemote().sendText(msg.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        peers.add(session);
        JSONArray msg = new JSONArray();
        for(Ball b : scene.balls){
            msg.put(b.radius);
        }
        session.getBasicRemote().sendText("init " + msg.toString());
    }

    @OnClose
    public void onClose(Session session) throws InterruptedException {
        peers.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        if(message.equals("disable")){
            scene.setGravity(new Vector(0, 0, 0));
            session.getBasicRemote().sendText("callback");
        }
        if(message.equals("gravity")){
            scene.setGravity(scene.gravity.negate_this());
            session.getBasicRemote().sendText("callback");
        }
    }
}
