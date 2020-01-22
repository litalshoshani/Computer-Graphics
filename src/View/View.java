package View;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class is responsible for parsing the scn file
 * class contains the viw file objects, and responsible for handling
 * the objects, the logic of the view (such as the matrices)
 */

import Math.Matrix;
import Math.Point;
import Math.Vector;
import PubSub.Message;
import PubSub.ViewportMessage;
import Transformations.Scale;
import Transformations.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

public class View implements Syncable, Publisher<Message> {

    private Point position;
    private Point lookAt;
    private Point up;

    private Viewport viewport;
    private int vpWidth;
    private int vpHeight;

    private double left;
    private double right;
    private double bottom;
    private double top;

    private Matrix MV1;
    private Matrix P;
    private Matrix TL;
    private Matrix minusTL;

    private boolean ready;

    private List<Subscriber<? super Message>> subscribers;

    public View() {
        top = 0;
        right = 0;
        left = 0;
        bottom = 0;

        MV1 = P = TL = minusTL = Matrix.I(4);

        position = null;
        lookAt = null;
        up = null;

        viewport = new Viewport(800, 800);

        ready = false;

        subscribers = new ArrayList<>();
    }

    /********************************
     * GETTERS *
     ********************************/

    /**
     * @return the position
     */
    public Vector getPosition() {
        double x = position.getArgs()[0];
        double y = position.getArgs()[1];
        double z = position.getArgs()[2];
        return new Vector(x, y, z);
    }

    /**
     * @return the lookat
     */
    public Vector getLookAt() {
        double x = lookAt.getArgs()[0];
        double y = lookAt.getArgs()[1];
        double z = lookAt.getArgs()[2];
        return new Vector(x, y, z);
    }

    /**
     * @return the up
     */
    public Vector getUp() {
        double x = up.getArgs()[0];
        double y = up.getArgs()[1];
        double z = up.getArgs()[2];
        return new Vector(x, y, z);
    }

    /**
     * @return the viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * @return the left
     */
    public double getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public double getRight() {
        return right;
    }

    /**
     * @return the bottom
     */
    public double getBottom() {
        return bottom;
    }

    /**
     * @return the top
     */
    public double getTop() {
        return top;
    }

    /**
     * @return the mV1
     */
    public Matrix getMV1() {
        return MV1;
    }

    /**
     * @return the mV2
     */
    public Matrix getMV2() {
        return initMV2();
    }

    /**
     * @return the p
     */
    public Matrix getP() {
        return P;
    }

    /**
     * @return the tL
     */
    public Matrix getTL() {
        return TL;
    }

    /**
     * @return the minusTL
     */
    public Matrix getMinusTL() {
        return minusTL;
    }

    /********************************
     * SETTERS *
     ********************************/

    /**
     * @param vpHeight the vpHeight to set
     * @param vpWidth the vpWidth to set
     */
    public void setViewportOriginalSize(int vpWidth, int vpHeight) {
        this.vpHeight = vpHeight;
        this.vpWidth = vpWidth;
    }

    /**
     * @param ready the ready to set
     */
    public void setReady(boolean ready) {
        this.ready = ready;
    }


    public void setPosition(Point position) {
        this.position = position;
    }

    public void setLookAt(Point lookAt) {
        this.lookAt = lookAt;
    }

    public void setUp(Point up) {
        this.up = up;
    }

    /**
     * @param viewport the viewport to set
     */
    public void setViewport(Viewport vp) {
        viewport.setWidth(vp.getWidth());
        viewport.setHeight(vp.getHeight());

        publish();
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public void setTop(double top) {
        this.top = top;
    }

    /********************************
     * BUSINESS LOGIC *
     ********************************/

    public void reset() {
        setViewport(new Viewport(vpWidth, vpHeight));

        MV1 = initMV1();
        P = initP();
        TL = initTL();
        minusTL = initMinusTL();
    }

    private Matrix initMV2() {
        double L = getLeft();
        double R = getRight();
        double B = getBottom();
        double T = getTop();

        Matrix S = new Scale((double) viewport.getWidth() / (R - L), -(double) viewport.getHeight() / (T - B), 1);

        Matrix T1 = new Translate(-(L + ((R - L) / 2)), -(B + ((T - B) / 2)), 0);

        Matrix T2 = new Translate((double) viewport.getWidth() / 2, (double)viewport.getHeight() / 2, 0);

        return T2.mult(S).mult(T1);
    }

    private Matrix initMV1() {
        Vector P = getPosition();
        Vector V = getUp();

        Vector PminusL = calcPminusL();
        Vector Zv = PminusL.scalar(1 / PminusL.length());

        Vector VcrossZv = V.cross(Zv);
        Vector Xv = VcrossZv.scalar(1 / VcrossZv.length());

        Vector Yv = Zv.cross(Xv);

        Matrix R = new Matrix(new double[][] { { Xv.getX(), Xv.getY(), Xv.getZ(), 0 },
                { Yv.getX(), Yv.getY(), Yv.getZ(), 0 }, { Zv.getX(), Zv.getY(), Zv.getZ(), 0 }, { 0, 0, 0, 1 } });

        Matrix T = new Translate(-P.getX(), -P.getY(), -P.getZ());

        return R.mult(T);
    }

    private Matrix initP() {
        return new Matrix(new double[][] { 
            { 1, 0, 0, 0 }, 
            { 0, 1, 0, 0 }, 
            { 0, 0, 0, 0 }, 
            { 0, 0, 0, 1 } 
        });
    }

    private Matrix initTL() {
        Vector PminusL = calcPminusL();
        double d = PminusL.length();

        return new Matrix(new double[][] { 
            { 1, 0, 0, 0 }, 
            { 0, 1, 0, 0 }, 
            { 0, 0, 1, d }, 
            { 0, 0, 0, 1 } 
        });
    }

    private Matrix initMinusTL() {
        Vector PminusL = calcPminusL();
        double d = PminusL.length();

        return new Matrix(new double[][] { 
            { 1, 0, 0, 0 }, 
            { 0, 1, 0, 0 }, 
            { 0, 0, 1, -d }, 
            { 0, 0, 0, 1 } 
        });
    }

    private Vector calcPminusL() {
        Vector P = getPosition();
        Vector L = getLookAt();

        return P.minus(L);
    }

    public boolean ready() {
        return ready;
    }


    @Override
    public void subscribe(Subscriber<? super Message> subscriber) {
        subscribers.add(subscriber);
    }

    public void publish() {
        for (Subscriber<? super Message> subscriber : subscribers) {
            subscriber.onNext(new ViewportMessage(viewport));
        }
    }

    /********************************
    *     UNIMPLEMENTED EVENTS      *
    ********************************/


}
