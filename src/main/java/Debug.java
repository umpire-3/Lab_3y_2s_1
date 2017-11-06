import Physics.Scene;
import Physics.Vector;

/**
 * Created by Umpire on 27.03.2016.
 */
public class Debug {
    public static void main(String args[]){

        Vector velocity = new Vector(124, 12, -63),
            u1 = new Vector(-34, 0, 5),
            v1 = new Vector(82, -18, 6);
        velocity = velocity.subtracted(u1).added(v1);
        velocity.multiply(3f/4f);
        System.out.println(velocity);
    }
}
