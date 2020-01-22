package Transformations;

import Math.Matrix;
import Math.Point;
import Utils.Utils;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The TransformationFactory class creates a 
 * specific Transformation according to the current
 * state
 */
public class TransformationFactory {
    private TransformationCase mode;
    private RotateOrientation orientation;
    private Point window;
    private Point viewport;
    private Matrix before;
    private Matrix after;

    public TransformationFactory(TransformationCase mode, 
                                 RotateOrientation orientation,
                                 Point window,
                                 Point viewport,
                                 Matrix before,
                                 Matrix after) {
        this.mode = mode;
        this.orientation = orientation;
        this.window = window;
        this.viewport = viewport;
        this.before = before;
        this.after = after;
    }

    public TransformationFactory(TransformationCase mode, 
                                 RotateOrientation orientation,
                                 Point window,
                                 Point viewport) {
        this.mode = mode;
        this.orientation = orientation;
        this.window = window;
        this.viewport = viewport;
        this.before = Matrix.I(4);
        this.after = Matrix.I(4);
    }

    /**
     * The method returns a new transformation according 
     * to a specific situation
     * @param source source point of the mouse
     * @param dest destination point of the mouse
     * @param center center of the object
     * @return
     */
    public Matrix create(Point source, Point dest, Point center) {
        Matrix CT = Matrix.I(4);

        switch (mode) {
            case SCALE:
                double coef = dest.distance(center) / source.distance(center);
                /**
                 * Calc distance between:
                 * Vs = S - C
                 * Vd = D - C
                 * mekadem = |Vd| / |Vs| 
                 */
                CT = before.mult(new Scale(coef, coef, coef)).mult(after);
                break;
            case ROTATE:
                Point dMinusc = dest.minus(center);
                Point sMinusc = source.minus(center);

                double theta = Utils.angle(dMinusc) - Utils.angle(sMinusc);
                /**
                 * Calc theta:
                 * Theta = Theta,d-c - Theta,s-c
                 */
                CT = before.mult(new Rotate(theta, orientation)).mult(after);
                break;
            case TRANSLATE: 
                double[] sourceArgs = source.getArgs();
                double[] destArgs = dest.getArgs();
                double[] vpSize = viewport.getArgs();
                double[] wnSize = window.getArgs();

                double deltaX = (destArgs[0] - sourceArgs[0]) 
                                * ((double)wnSize[0] / (double)vpSize[0]);

                double deltaY = (destArgs[1] - sourceArgs[1]) 
                                * -((double)wnSize[1] / (double)vpSize[1]);
                                
                double deltaZ = 0;
                
                /**
                 * Calc distance between:
                 * DeltaX = Dx - Sx
                 * DeltaY = Dy - Sy
                 */
                CT = new Translate(deltaX, deltaY, deltaZ);
                break;
            default:
                CT = Matrix.I(4);
                break;
        }

        return CT;
    }
}