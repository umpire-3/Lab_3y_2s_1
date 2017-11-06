package Physics;

/**
 * Created by Umpire on 21.03.2016.
 */
public class Ball {
    public float mass;
    public float radius;
    public Vector position;
    public Vector velocity;
    public Vector acceleration;

    public Ball(){
        this.mass = 0.0f;
        this.radius = 0.0f;
        this.position = Vector.Null();
        this.velocity = Vector.Null();
        this.acceleration = Vector.Null();
    }

    public Ball(float mass, float radius, Vector position, Vector velocity, Vector acceleration){
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void move(float dt){
        position.add(velocity.multiplied(dt));
        velocity.add(acceleration.multiplied(dt));
    }

    public void addForce(Vector force){
        acceleration.add(force.divided(mass));
    }
}