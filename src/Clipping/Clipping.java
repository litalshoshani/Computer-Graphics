package Clipping;

import Math.Edge;
import Math.Vector;
import Math.Vertex;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class is responsible for clipping objects outside the clipping frame.
 */

public class Clipping {

    private ClippingFrame clippingFrame;

    public Clipping(Viewport viewport) {
        clippingFrame = new ClippingFrame(viewport);
    }

    public List<Edge> getInnerFrame(){
        List<Edge> l = clippingFrame.getFrame();
        return l;
    }

    /**
     * runs over the given edges, and for each one checks if the edge is inside
     * the frame or not.
     * @param edges
     */
    public List<Edge> clip(List<Edge> edges) {
        List<Edge> clippedEdges = new ArrayList<Edge>();
        for (Edge e: edges){
            Edge newEdge = clipLine(e);
            if(newEdge != null){
                clippedEdges.add(newEdge);
            }
        }
        return clippedEdges;
    }

    /**
     * check if a line is inside the frame's boundaries.
     * uses cyrus beck line clipping algorithm.
     * @param line
     * @return a new edge with vertices fitting the boundaries,
     * null if the line is outside the frame.
     */
    private Edge clipLine(Edge line){

        //check case of a point
        if(line.isAPoint()){
            return line;
        }

        //get the normals of the frame
        List<Vector> normals = clippingFrame.getNormals();

        double t_entry = 0;
        double t_leaving = 1;

        //distance between start and end points
        Vector p0 = new Vector(line.getStart().getX(),
                line.getStart().getY(),
                line.getStart().getZ(),
                0);
        Vector p1 = new Vector(line.getEnd().getX(),
                line.getEnd().getY(),
                line.getEnd().getZ(),
                0);

        Vector d = p1.minusAll(p0);

        List<Edge> frames = getInnerFrame();
        //for every edge of the frame calculate delta and t in order to find intersections.
        for (Edge e: frames){
            //get the edge's normal
            Vector edge_normal = normals.get(frames.indexOf(e));

            double delta = edge_normal.dotProduct(d);

            if (delta == 0) {
                continue;
            }
                //choose center point of the edge
            Vertex edgeCenter = e.getCenterEdgePoint();

            Vector p0MinusCenter = p0.minusAll(edgeCenter.toVector4D());

            double t = edge_normal.dotProduct(p0MinusCenter)/(-1*delta);

            if(t < 0 || t > 1) {
                continue;
            }
            //potentially entering intersection
            if(delta < 0){
                t_entry = Math.max(t_entry, t);
            }
            //potentially leaving intersection
            else if (delta > 0){
                t_leaving = Math.min(t_leaving, t);
            }
        }
        
        if(t_entry > t_leaving){
            return null;
        }

        /*
        there was no change in the t entry and t leaving, check if the line is outside the frame
         */
        if(t_entry == 0 && t_leaving == 1){
            if(clippingFrame.inBetweenFrame(line.getStart().getX(),line.getStart().getY())) {
                return null;
            }
        }

        

        //find the entry and leaving point by using t_entry and t_leaving values.
        Vertex p_tEntry = line.getStart().toVector4D().plus(d.scalar(t_entry)).toVertex();
        Vertex p_tLeaving = line.getStart().toVector4D().plus(d.scalar(t_leaving)).toVertex();

        if(clippingFrame.inBetweenFrame(p_tEntry.getX(),p_tEntry.getY()) ||
                clippingFrame.inBetweenFrame(p_tLeaving.getX(),p_tLeaving.getY())) {
            return null;
        }

        Edge newLine = new Edge(p_tEntry,p_tLeaving);

        return newLine;
    }

}