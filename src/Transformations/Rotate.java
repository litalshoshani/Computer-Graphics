package Transformations;

import Math.Matrix;
import Transformations.RotateOrientation;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class represents the Rotate transformation
 */
public class Rotate extends Matrix {
    public Rotate(double theta, RotateOrientation orientation) {
        super(new double[][] {});

        switch (orientation) {
            case X:
                setMatrix(new double[][] {
                    { 1,               0,                0, 0 },
                    { 0, Math.cos(theta), -Math.sin(theta), 0 },
                    { 0, Math.sin(theta),  Math.cos(theta), 0 },
                    { 0,               0,                0, 1 }
                });
                break;
            case Y:
                setMatrix(new double[][] {
                    { Math.cos(theta), 0, -Math.sin(theta), 0 },
                    { 0              , 1,                0, 0 },
                    { Math.sin(theta), 0,  Math.cos(theta), 0 },
                    { 0,               0,                0, 1 }
                });
                break;
            case Z:
                setMatrix(new double[][] {
                    { Math.cos(theta) , Math.sin(theta), 0, 0 },
                    { -Math.sin(theta), Math.cos(theta), 0, 0 },
                    { 0               , 0              , 1, 0 },
                    { 0               , 0              , 0, 1 }
                });
                break;
        }
    }
}
