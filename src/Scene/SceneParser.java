package Scene;

import FileHandlers.FileParser;
import Math.Edge;
import Math.Polygon;
import Math.Vertex;
import View.Syncable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class is responsible for parsing the scn file
 */

public class SceneParser implements FileParser {

    private List<Polygon> Polygons;

    public SceneParser() {}

    /**
     * create a poligon's vertices list
     * @param numOfVertices
     * @param buff
     * @return
     * @throws Exception
     */
    private List<Vertex> createVerticesList(int numOfVertices, BufferedReader buff) throws Exception{
        List<Vertex> vertices = new ArrayList<>();
        String str = "";
        String [] splitStr = null;
        Vertex v;
        for(int i = 0; i < numOfVertices; i++){
            str = buff.readLine();
            splitStr = str.split(" ");
            if(splitStr.length == 2){
                v = new Vertex(Double.parseDouble(splitStr[0]), Double.parseDouble(splitStr[1]));
            }
            else{
                v = new Vertex(Double.parseDouble(splitStr[0]), Double.parseDouble(splitStr[1]), Double.parseDouble(splitStr[2]));
            }

            vertices.add(v);
        }
        return vertices;
    }

    /**
     * create a poligon's edge list
     * @param numOfEdges
     * @param vertices
     * @param buff
     * @return
     * @throws Exception
     */
    private List<Edge> createEdgesList(int numOfEdges, List<Vertex> vertices, BufferedReader buff) throws Exception{
        List<Edge> edges = new ArrayList<>();
        String str = "";
        String [] splitStr = null;
        for(int i = 0; i < numOfEdges; i++){
            str = buff.readLine();
            splitStr = str.split(" ");
            int start = Integer.parseInt(splitStr[0]);
            int end = Integer.parseInt(splitStr[1]);
            Edge e = new Edge(vertices.get(start), vertices.get(end));
            edges.add(e);
        }
        return edges;
    }

    /**
     * parse over a scn file, and creates polygons accordingly.
     * @throws Exception
     */
    @Override
    public void parseFile(File file) {
        List<Polygon> polygons = new ArrayList<>();
        try {
            BufferedReader buff = new BufferedReader(new FileReader(file));

            String str;
            boolean isVertexNum = true;
            int numOfVertices = 0;
            int numOfEdges = 0;
            Polygon p = null;
            List<Vertex> vertices = null;
            List<Edge> edges = null;
            String splitStr[];

            while((str = buff.readLine()) != null){
                //check str length
                splitStr = str.split(" ");
                if(splitStr.length == 1){
                    //check isVertexNum flag. if it is true then the number is the vertices number.
                    if(isVertexNum){
                        numOfVertices = Integer.parseInt(str);
                        vertices = createVerticesList(numOfVertices, buff);
                        isVertexNum = false;
                    }
                    else{
                        numOfEdges = Integer.parseInt(str);
                        edges = createEdgesList(numOfEdges, vertices, buff);
                        p = new Polygon(vertices, edges);
                        polygons.add(p);
                        isVertexNum = true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error in file reading");
        }

        Polygons = polygons;
    }

    /**
     * sync a syncable object with the created objects of the scn file
     * @param syncable
     */
    @Override
    public void sync(Syncable syncable) {
        Scene scene = (Scene)syncable;

        scene.setPolygons(Polygons);

        scene.setReady(true);
    }
}
