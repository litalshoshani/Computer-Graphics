package Math;

import UI.Drawable;

import java.awt.*;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class represents the edge object.
 */

public class Edge implements Drawable {

    private Vertex start;
    private Vertex end;

    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * @return the start
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public Vertex getEnd() {
        return end;
    }

    /********************************
    *            EXTERNAL           *
    ********************************/

    @Override
    public void draw(Graphics g) {
        g.drawLine((int)start.getX(),
                (int)start.getY(), 
                (int)end.getX(), 
                (int)end.getY());
    }

    /********************************
     *            Logic             *
     ********************************/

    /**
     * checks whether the edge is actually a point
     * @return
     */
    public boolean isAPoint(){
        return start.isTheSameVertex(end);
    }

    /**
     * returns the center point of the edge
     * @return
     */
    public Vertex getCenterEdgePoint(){
        double centerX = (start.getX() + end.getX())/2;
        double centerY = (start.getY() + end.getY())/2;
        double centerZ = (start.getZ() + end.getZ())/2;
        return new Vertex(centerX,centerY,centerZ);
    }
}
