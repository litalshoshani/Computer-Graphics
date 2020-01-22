package Events.KeyEvents;

import App.Model;
import Events.Action;
import Transformations.RotateOrientation;

public class YKeyPress implements Action {
    public YKeyPress() {}

    @Override
    public void handle(Model model, Object event) {
        model.setOrientation(RotateOrientation.Y);
    }
}