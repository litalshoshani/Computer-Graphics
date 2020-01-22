package Events.KeyEvents;

import App.Model;
import Events.Action;
import Transformations.RotateOrientation;

public class ZKeyPress implements Action {
    public ZKeyPress() {}

    @Override
    public void handle(Model model, Object event) {
        model.setOrientation(RotateOrientation.Z);
    }
}