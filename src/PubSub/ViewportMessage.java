package PubSub;

import View.Viewport;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class handles the Viewport message
 */
public class ViewportMessage implements Message {

    private String type;
    private Viewport data;

    public ViewportMessage(Viewport viewport) {
        type = "viewport";
        data = viewport;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Viewport getData() {
        return data;
    }

}