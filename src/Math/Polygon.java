package Math;

import Transformations.Transformable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class represents the polygon object.
 */

public class Polygon implements Transformable {

    private List<Vertex> vertices = new ArrayList<>();
    private List<Vertex> original = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();    

    public Polygon(List<Vertex> v, List<Edge> e) {
        vertices = v;
        edges = e;
        
        for (Vertex vertex : vertices) {
            original.add(new Vertex(vertex));
        }
    }

    public Polygon(List<Edge> e) {
        edges = e;

        //add the vertices of the edges
        addVerticesFromEdges(e);

    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * @return the vertices
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * @return the edges
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /********************************
    *        BUSINESS LOGIC         *
    ********************************/
    
    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    private void addVerticesFromEdges(List<Edge> e){
        for (Edge edge : e) {
            Vertex end = edge.getEnd();
            Vertex start = edge.getStart();
            original.add(new Vertex(end));
            original.add(new Vertex(start));
            vertices.add(new Vertex(end));
            vertices.add(new Vertex(start));
        }
    }

    public void reset() {
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).copy(original.get(i));
        }
    }

    /********************************
    *            EXTERNAL           *
    ********************************/

    @Override
    public void transform(Matrix T) {
        for (int i = 0; i < original.size(); i++) {
            Vector v = original.get(i).toVector();
            Vector transformed = T.mult(v).toVector().normalize();
    
            vertices.get(i).copy(transformed.toVertex());
        }    
    }

}
