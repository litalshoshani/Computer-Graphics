package Transformations;

import Math.Matrix;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class represents the Scale transformation
 */
public class Scale extends Matrix {
    public Scale(double x, double y, double z) {
        super(new double[][] {
            { x, 0, 0, 0 },
            { 0, y, 0, 0 },
            { 0, 0, z, 0 },
            { 0, 0, 0, 1 }
        });
    }
}
