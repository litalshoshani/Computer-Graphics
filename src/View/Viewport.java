package View;

/**
 * Lital Shoshani
 * Uriah Ahrak
 */

public class Viewport {
    private int width;
    private int height;

    public Viewport(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }
}