package Utils;

import Math.Point;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class has some convenient
 * methods to help among the app
 */
public class Utils {
    public static double angle(Point p) {
        double[] args = p.getArgs();
        return Math.atan2(args[1], args[0]);
    }
}