package Math;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class handles a Point
 * representation
 */
public class Point {
    private double[] args;

    public Point(double ...args) {
        this.args = args;
    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * @return the args
     */
    public double[] getArgs() {
        return args;
    }

    /********************************
    *            SETTERS            *
    ********************************/

    /**
     * @param args the args to set
     */
    public void setArgs(double ...args) {
        this.args = args;
    }

    /********************************
    *        BUSINESS LOGIC         *
    ********************************/

    /**
     * The method calculates the distance between
     * the current point and another one
     * @param p
     * @return
     */
    public double distance(Point p) {
        double[] coefs1 = p.getArgs();
        double[] coefs2 = getArgs();

        if (coefs1.length != coefs2.length) {
            return -1;
        }

        double sum = 0;
        for (int i = 0; i < coefs1.length; i++) {
            sum += Math.pow(coefs1[i] - coefs2[i], 2);
        }

        return Math.sqrt(sum);
    }

    /**
     * The method calcs the diff
     * between the current point and another one
     * @param p
     * @return
     */
    public Point minus(Point p) {
        double[] otherArgs = p.getArgs();
        double[] thisArgs = getArgs();

        if (otherArgs.length != thisArgs.length) {
            return null;
        }

        double[] args = new double[thisArgs.length];
        for (int i = 0; i < thisArgs.length; i++) {
            args[i] = thisArgs[i] - otherArgs[i];
        }

        return new Point(args);
    }

}