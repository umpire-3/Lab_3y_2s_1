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

    private void WallsCollision(Ball ball){
        if(ball.position.x < -CellSize - Edge + ball.radius){
            ball.position.x = -CellSize + ball.radius;
            ball.velocity.x = -ball.velocity.x;
            ball.velocity.multiply(0.8f);
        }
        if(ball.position.x > CellSize + Edge - ball.radius){
            ball.position.x = CellSize - ball.radius;
            ball.velocity.x = -ball.velocity.x;
            ball.velocity.multiply(0.8f);
        }
        if(ball.position.y < -CellSize - Edge + ball.radius){
            ball.position.y = -CellSize + ball.radius;
            ball.velocity.y = -ball.velocity.y;
            ball.velocity.multiply(0.8f);
        }
        if(ball.position.y > CellSize + Edge - ball.radius){
            ball.position.y = CellSize - ball.radius;
            ball.velocity.y = -ball.velocity.y;
            ball.velocity.multiply(0.8f);
        }
        if(ball.position.z < -CellSize - Edge + ball.radius){
            ball.position.z = -CellSize + ball.radius;
            ball.velocity.z = -ball.velocity.z;
            ball.velocity.multiply(0.8f);
        }
        if(ball.position.z > CellSize + Edge - ball.radius){
            ball.position.z = CellSize - ball.radius;
            ball.velocity.z = -ball.velocity.z;
            ball.velocity.multiply(0.8f);
        }
    }

    private boolean SolveCollision(Ball b1, Ball b2){
        Vector Axis = b2.position.subtracted(b1.position);
        float dist = b1.radius + b2.radius + Edge;
        if(Axis.Abs() < dist) {
            Vector p = new Vector(Axis);
            p.setAbs(dist - Axis.Abs());
            b1.position.subtract(p.multiplied(b1.velocity.Abs() / (b1.velocity.Abs() + b2.velocity.Abs())));
            b2.position.add(p.multiplied(b2.velocity.Abs() / (b1.velocity.Abs() + b2.velocity.Abs())));

            Axis.normalize();

            Vector u1 = Axis.multiplied(Axis.dot(b1.velocity));
            Axis.negate();
            Vector u2 = Axis.multiplied(Axis.dot(b2.velocity));

            Vector v1 = u1.multiplied(b1.mass).added(u2.multiplied(b2.mass))
                    .subtracted(u1.subtracted(u2).multiplied(b2.mass))
                    .divided(b1.mass + b2.mass),
                   v2 = u1.multiplied(b1.mass).added(u2.multiplied(b2.mass))
                    .subtracted(u2.subtracted(u1).multiplied(b1.mass))
                    .divided(b1.mass + b2.mass);

            b1.velocity.subtract(u1).add(v1).multiply(0.8f);
            b2.velocity.subtract(u2).add(v2).multiply(0.8f);
            return true;
        }

        return false;
    }

    public void Update(float dt){
        for (Ball ball : balls) {
            WallsCollision(ball);
            ball.move(dt);
        }
        for(int i = 0; i < balls.size() - 1; i++){
            for(int j = i + 1; j < balls.size(); j++){
                SolveCollision(balls.get(i), balls.get(j));
            }
        }
    }
}
