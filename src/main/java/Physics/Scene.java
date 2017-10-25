package Physics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Umpire on 21.03.2016.
 */
public class Scene {

    public ArrayList<Ball> balls = new ArrayList<Ball>();
    public Vector gravity = new Vector(0, -98f, 0);
    private float Edge = 0.1f;
    private float CellSize = 200.0f;

    public Scene(){
        Random rand = new Random();
        for(int i = 0; i < 50; i++){
            float radius = rand.nextInt(29) + 1;
            float mass = radius*radius*radius*(float)Math.PI*(4.0f/3.0f);
            Vector p = new Vector(rand.nextInt(350) - 200, rand.nextInt(350) - 200, rand.nextInt(350) - 200);
            Vector v = new Vector((rand.nextInt(100) - 50), (rand.nextInt(100) - 50), (rand.nextInt(100) - 50));
            v.setAbs(rand.nextInt(200)/4.0f);
            Vector a = Vector.Null();
            balls.add(new Ball(mass, radius, p, v, a));
        }
    }

    public void setGravity(Vector gravity) {
        for(Ball ball : balls){
            ball.acceleration.set(gravity);
        }
    }

    public void WallsCollision(Ball ball){
        if(ball.position.x < -CellSize - Edge + ball.radius){
            ball.position.x = -CellSize + ball.radius;
            ball.velocity.x = -ball.velocity.x;
            ball.velocity.setAbs(ball.velocity.Abs()*3f/4f);
        }
        if(ball.position.x > CellSize + Edge - ball.radius){
            ball.position.x = CellSize - ball.radius;
            ball.velocity.x = -ball.velocity.x;
            ball.velocity.setAbs(ball.velocity.Abs()*3f/4f);
        }
        if(ball.position.y < -CellSize - Edge + ball.radius){
            ball.position.y = -CellSize + ball.radius;
            ball.velocity.y = -ball.velocity.y;
            ball.velocity.setAbs(ball.velocity.Abs()*3f/4f);
        }
        if(ball.position.y > CellSize + Edge - ball.radius){
            ball.position.y = CellSize - ball.radius;
            ball.velocity.y = -ball.velocity.y;
            ball.velocity.setAbs(ball.velocity.Abs()*3f/4f);
        }
        if(ball.position.z < -CellSize - Edge + ball.radius){
            ball.position.z = -CellSize + ball.radius;
            ball.velocity.z = -ball.velocity.z;
            ball.velocity.setAbs(ball.velocity.Abs()*3f/4f);
        }
        if(ball.position.z > CellSize + Edge - ball.radius){
            ball.position.z = CellSize - ball.radius;
            ball.velocity.z = -ball.velocity.z;
            ball.velocity.setAbs(ball.velocity.Abs()*3f/4f);
        }
    }

    public boolean SolveCollision(Ball b1, Ball b2){
        Vector Axis = b2.position.sub(b1.position);
        float dist = b1.radius + b2.radius + Edge;
        if(Axis.Abs() < dist) {
            Vector p = new Vector(Axis);
            p.setAbs(dist - Axis.Abs());
            b1.position.sub_this(p.mult(b1.velocity.Abs() / (b1.velocity.Abs() + b2.velocity.Abs())));
            b2.position.add_this(p.mult(b2.velocity.Abs() / (b1.velocity.Abs() + b2.velocity.Abs())));

            Axis.setAbs(1.0f);

            Vector u1 = Axis.mult(Axis.ScalarMult(b1.velocity));
            Axis.negate_this();
            Vector u2 = Axis.mult(Axis.ScalarMult(b2.velocity));
            /*float m1 = b1.mass - b2.mass,
                  m2 = b1.mass + b2.mass;*/
            Vector v1 = u1.mult(b1.mass).add(u2.mult(b2.mass)).sub(u1.sub(u2).mult(b2.mass)).mult(1.0f/(b1.mass + b2.mass)),
                   v2 = u1.mult(b1.mass).add(u2.mult(b2.mass)).sub(u2.sub(u1).mult(b1.mass)).mult(1.0f/(b1.mass + b2.mass));
            //v1 = u1.mult(m1).add(u2.mult(b2.mass*2.0f)).mult(1.0f/m2);
            b1.velocity = b1.velocity.sub(u1).add(v1);
            b2.velocity = b2.velocity.sub(u2).add(v2);
            b1.velocity.setAbs(b1.velocity.Abs()*3f/4f);
            b2.velocity.setAbs(b2.velocity.Abs()*3f/4f);
            return true;
        }

        return false;
    }

    public void Update(float dt){
        for(int i = 0; i < balls.size(); i++){
            WallsCollision(balls.get(i));
            balls.get(i).move(dt);
        }
        for(int i = 0; i < balls.size() - 1; i++){
            for(int j = i + 1; j < balls.size(); j++){
                SolveCollision(balls.get(i), balls.get(j));
            }
        }
    }
}
