package PubSub;

import java.util.List;
import Math.Edge;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class handles the DrawMessage message
 */
public class DrawMessage implements Message {

    private String type;
    private List<Edge> data;

    public DrawMessage(List<Edge> edges) {
        type = "draw";
        data = edges;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<Edge> getData() {
        return data;
    }

}