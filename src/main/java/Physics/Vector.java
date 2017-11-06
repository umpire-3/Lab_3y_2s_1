package Physics;

/**
 * Created by Umpire on 20.03.2016.
 */
public class Vector {

    public float x;
    public float y;
    public float z;

    public static Vector Null(){
        return new Vector();
    }

    public Vector(){
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector other){
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public void set(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vector other){
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public Vector negate(){
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Vector add(Vector other){
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector subtract(Vector other){
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public float dot(Vector other){
        return this.x*other.x + this.y*other.y + this.z*other.z;
    }

    public Vector multiply(Vector other){
        this.set(this.y*other.z - this.z*other.y,
                this.z*other.x - this.x*other.z,
                this.x*other.y - this.y*other.x);
        return this;
    }

    public Vector multiply(float scalar){
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    public Vector divide(float scalar){
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    public Vector negated(){
        return new Vector(this).negate();
    }

    public Vector added(Vector other){
        return new Vector(this).add(other);
    }

    public Vector subtracted(Vector other){
        return new Vector(this).subtract(other);
    }

    public Vector multiplied(Vector other){
        return new Vector(this).multiply(other);
    }

    public Vector multiplied(float scalar){
        return new Vector(this).multiply(scalar);
    }

    public Vector divided(float scalar){
        return new Vector(this).divide(scalar);
    }

    public float Abs(){
        return (float) Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
    }

    public Vector setAbs(float scalar){
        float _scalar = this.Abs();
        if(_scalar != 0){
            scalar /= _scalar;
            this.x *= scalar;
            this.y *= scalar;
            this.z *= scalar;
        }
        return this;
    }

    public Vector normalize() {
        return this.setAbs(1.0f);
    }

    public Vector normilized() {
        return new Vector(this).normalize();
    }

    public String toString() {
        return "Vector { x: " + x + ", y: " + y + ", z: " + z + " }";
    }
}
