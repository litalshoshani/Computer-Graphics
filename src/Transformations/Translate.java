package Transformations;

import Math.Matrix;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class represents the Translate transformation
 */
public class Translate extends Matrix {
    public Translate(double x, double y, double z) {
        super(new double[][] {
            { 1, 0, 0, x },
            { 0, 1, 0, y },
            { 0, 0, 1, z },
            { 0, 0, 0, 1 }
        });
    }
}
