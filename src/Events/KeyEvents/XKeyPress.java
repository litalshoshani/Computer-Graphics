package Events.KeyEvents;

import App.Model;
import Events.Action;
import Transformations.RotateOrientation;

public class XKeyPress implements Action {
    public XKeyPress() {}

    @Override
    public void handle(Model model, Object event) {
        model.setOrientation(RotateOrientation.X);
    }
}