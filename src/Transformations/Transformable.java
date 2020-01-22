package Transformations;

import Math.Matrix;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The Transformable interface handles 
 * the situation when a class can be transformed
 * to another value by a Matrix
 */
public interface Transformable {
    /**
     * The transform method gets a transformation
     * matrix T and applies it to some vertices
     * @param T a transformation matrix
     */
    void transform(Matrix T);
}