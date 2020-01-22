package Events.KeyEvents;

import App.Model;
import Events.Action;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class is responsible for what happens when the C key is pressed.
 */

public class CKeyPress implements Action {
    public CKeyPress() {}

    @Override
    public void handle(Model model, Object event) {
        //set the clipping mode in model
        boolean clipping = model.isClippingOn();
        model.setClipping(!clipping);
        model.tick();
    }
}