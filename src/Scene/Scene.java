package Scene;

import Math.Matrix;
import Math.Polygon;
import Transformations.Transformable;
import View.Syncable;

import java.util.ArrayList;
import java.util.List;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class represents the Scene entity.
 * Contains the scn file objects, and responsible for handling
 * the objects.
 */

public class Scene implements Transformable, Syncable {

    private List<Polygon> polygons;
    private List<Polygon> clippedPolygons;
    private boolean isClipped;
    private boolean ready;

    public Scene() {
        polygons = new ArrayList<>();
        clippedPolygons = new ArrayList<Polygon>();
        ready = false;
        isClipped = false;
    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * @return the polygons list
     */
    public List<Polygon> getPolygons() {
        if(isClipped){
            return clippedPolygons;
        }
        return polygons;
    }

    /********************************
    *            SETTERS            *
    ********************************/

    /**
     * @param ready the ready to set
     */
    public void setReady(boolean ready) {
        this.ready = ready;
    }


    public void setPolygons(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    /********************************
    *        BUSINESS LOGIC         *
    ********************************/

    /**
     * The method resets the polygons
     */
    public void reset() {
        for (Polygon polygon : polygons) {
            polygon.reset();
        }
    }

    /**
     * checks if the scene is ready to start
     * @return
     */
    public boolean ready() {
        return ready;
    }

    /********************************
    *           EXTERNAL            *
    ********************************/

    @Override
    public void transform(Matrix T) {
        for (Polygon polygon : polygons) {
            polygon.transform(T);
        }
    }
}
