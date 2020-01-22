package Math;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The Vertex class handles the vertex
 * representation in the project
 */
public class Vertex {

    private double x;
    private double y;
    private double z;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    /**
     * A copy constructor
     * @param v
     */
    public Vertex(Vertex v) {
        this(v.getX(), v.getY(), v.getZ());
    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /********************************
    *        BUSINESS LOGIC         *
    ********************************/

    /**
     * Converts a vertex to a vector
     * @return
     */
    public Vector toVector() {
        return new Vector(
            this.getX(),
            this.getY(),
            this.getZ()
        );
    }

    /**
     * Converts a vertex to a vector4D
     * @return
     */
    public Vector toVector4D() {
        return new Vector(
                this.getX(),
                this.getY(),
                this.getZ(),
                0
        );
    }

    /**
     * Change the values of the current vertex
     * according to another one
     * @param v
     */
    public void copy(Vertex v) {
        x = v.getX();
        y = v.getY();
        z = v.getZ();
    }

    /**
     *
     * @param v
     * @return true if v is the same
     */
    public boolean isTheSameVertex(Vertex v){
        return (x == v.getX() && y == v.getY() && z == v.getZ());
    }
}
