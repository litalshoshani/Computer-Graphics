package Clipping;

import Math.*;
import UI.Drawable;
import View.Viewport;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class is responsible for creating and managing the inner frame.
 */

public class ClippingFrame implements Drawable {

    private List<Edge> frame;
    private Viewport viewport;
    private int distanceFromWindow;
    private double margin;
    private List<Vector> normals;

    public ClippingFrame(Viewport viewport) {
        this.viewport = viewport;
        frame = new ArrayList<>();
        distanceFromWindow = 20;
        createFrame();
        margin = 1;
        normals = createNormalsOFEdges();
    }


    /**
     * creates the normals of the frame's edges, in the next order:
     * 0: the normal of the upper edge
     * 1: the normal of the right edge
     * 2: the normal of the bottom edge
     * 3: the normal of the left edge
     * @return
     */
    private List<Vector> createNormalsOFEdges(){
        List<Vector> norms = new ArrayList<Vector>();
        // the normal of the upper edge
        norms.add(new Vector(0,-1,0,0));
        // the normal of the right edge
        norms.add(new Vector(1,0,0,0));
        // the normal of the bottom edge
        norms.add(new Vector(0,1,0,0));
        // the normal of the left edge
        norms.add(new Vector(-1,0,0,0));

        return norms;
    }

    /**
     * get the frame's edges normals.
     * @return
     */
    public List<Vector> getNormals() {
        return normals;
    }

    /**
     * creates 4 edges of the inner frame, according to the given height
     * and width.
     */
    private void createFrame() {
        frame.clear();

        int width = viewport.getWidth();
        int height = viewport.getHeight();

        Vertex upperLeft = new Vertex(distanceFromWindow,distanceFromWindow); //(20,20)
        Vertex upperRight = new Vertex(width-distanceFromWindow, distanceFromWindow); //(820,20)
        Vertex bottomLeft = new Vertex(distanceFromWindow, height - distanceFromWindow); //(20,820)
        Vertex bottomRight = new Vertex(width-distanceFromWindow, height - distanceFromWindow); //(820,820)

        Edge eTop = new Edge(upperLeft,upperRight);
        Edge eRight = new Edge(upperRight, bottomRight);
        Edge eBottom = new Edge(bottomRight, bottomLeft);
        Edge eLeft = new Edge(bottomLeft,upperLeft);

        frame.add(eTop);
        frame.add(eRight);
        frame.add(eBottom);
        frame.add(eLeft);
    }

    /**
     * return the frame
     * @return
     */
    public List<Edge> getFrame() {
        createFrame();
        return frame;
    }

    /**
     * checks if a given x and y values are inside the frame's boundaries.
     * @param x
     * @param y
     * @return
     */
    public boolean inBetweenFrame(double x, double y){
        return ((inBetweenFrameX(x)) || (inBetweenFrameY(y)));
    }

    /**
     * check if a given value is inside the width boundaries
     * @param x
     * @return
     */
    private boolean inBetweenFrameX(double x){
        int width = viewport.getWidth();

        if((x > (double)(width - distanceFromWindow)  + margin) || (x <(double)(distanceFromWindow) - margin)){
            return true;
        }
        return false;
    }

    /**
     * check if a given value is inside the height boundaries
     * @param y
     * @return
     */
    private boolean inBetweenFrameY(double y){
        int height = viewport.getHeight();

        if((y > (double)(height - distanceFromWindow)+ margin) || (y <(double)(distanceFromWindow) - margin)){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        for (Edge e: frame){
            e.draw(g);
        }
    }
}
