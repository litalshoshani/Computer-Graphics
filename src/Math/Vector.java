package Math;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The vector class handles a representation
 * of a vector, which extends a specific
 * case in a Matrix representation
 */
public class Vector extends Matrix {
    /**
     * The method constructs a new 4D vector
     * normalized
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(new double[][] {
            { x },
            { y },
            { z },
            { 1 }
        });
    }

    /**
     * The method constructs a new 4D vector
     * with a specific W
     * @param x
     * @param y
     * @param z
     * @param w
     */
    public Vector(double x, double y, double z, double w) {
        super(new double[][] {
            { x },
            { y },
            { z },
            { w }
        });
    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * @return the x
     */
    public double getX() {
        return matrix[0][0];
    }

    /**
     * @return the y
     */
    public double getY() {
        return matrix[1][0];
    }

    /**
     * @return the z
     */
    public double getZ() {
        return matrix[2][0];
    }

    /**
     * @return the w
     */
    public double getW() {
        return matrix[3][0];
    }

    /********************************
    *         BUSINESS LOGIC        *
    ********************************/

    /**
     * Cross product
     * @param v
     * @return
     */
    public Vector cross(Vector v) {
        return new Vector(
            (getY() * v.getZ()) - (getZ() * v.getY()),
            (getZ() * v.getX()) - (getX() * v.getZ()),
            (getX() * v.getY()) - (getY() * v.getX())
        );
    }

    /**
     * Multiply by a scalar
     * @param scalar
     * @return
     */
    public Vector scalar(double scalar) {
        return new Vector(
            scalar * getX(),
            scalar * getY(),
            scalar * getZ()
        );
    }

    /**
     * Diff between vectorss
     * @param v
     * @return
     */
    public Vector minus(Vector v) {
        return new Vector(
            getX() - v.getX(),
            getY() - v.getY(),
            getZ() - v.getZ()
        );
    }

    /**
     * Calcs the length of the vector
     * @return
     */
    public double length() {
        return Math.sqrt(
            (Math.pow(getX(), 2)) +
            (Math.pow(getY(), 2)) +
            (Math.pow(getZ(), 2))
        );
    }

    /**
     * Normalize the vector
     * @return
     */
    public Vector normalize() {
        return new Vector(
            getX() / getW(),
            getY() / getW(),
            getZ() / getW(),
            1
        );
    }
    
    /**
     * Convert to a vertex
     * @return
     */
    public Vertex toVertex() {
        return new Vertex(
            getX(),
            getY(),
            getZ()
        );
    }

    /**
     * Dot product
     * @param v
     * @return
     */
    public double dotProduct(Vector v){
        return (getX() * v.getX()) +
                (getY() * v.getY()) +
                (getZ() * v.getZ());
    }

    /**
     * Addition of vectors
     * @param v
     * @return
     */
    public Vector plus(Vector v) {
        return new Vector(
                getX() + v.getX(),
                getY() + v.getY(),
                getZ() + v.getZ()
        );
    }

    /**
     * Minus vectors but includes the
     * fourth element
     * @param v
     * @return
     */
    public Vector minusAll(Vector v) {
        return new Vector(
                getX() - v.getX(),
                getY() - v.getY(),
                getZ() - v.getZ(),
                0
        );
    }
}