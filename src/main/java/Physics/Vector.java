package Physics;

/**
 * Created by Umpire on 20.03.2016.
 */
public class Vector {

    public float x;
    public float y;
    public float z;

    public static Vector Null(){
        return new Vector(0.0f, 0.0f, 0.0f);
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

    public Vector negate_this(){
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Vector add_this(Vector other){
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector sub_this(Vector other){
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public float ScalarMult(Vector other){
        return this.x*other.x + this.y*other.y + this.z*other.z;
    }

    public Vector mult_this(Vector other){
        this.set(this.y*other.z - this.z*other.y,
                this.z*other.x - this.x*other.z,
                this.x*other.y - this.y*other.x);
        return this;
    }

    public Vector mult_this(float scalar){
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    public Vector div_this(float scalar){
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    public Vector negate(){
        Vector temp = new Vector(this);
        temp.negate_this();
        return temp;
    }

    public Vector add(Vector other){
        Vector temp = new Vector(this);
        temp.add_this(other);
        return temp;
    }

    public Vector sub(Vector other){
        Vector temp = new Vector(this);
        temp.sub_this(other);
        return temp;
    }

    public Vector mult(Vector other){
        Vector temp = new Vector(this);
        temp.mult_this(other);
        return temp;
    }

    public Vector mult(float scalar){
        Vector temp = new Vector(this);
        temp.mult_this(scalar);
        return temp;
    }

    public Vector div(float scalar){
        Vector temp = new Vector(this);
        temp.div_this(scalar);
        return temp;
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
}
