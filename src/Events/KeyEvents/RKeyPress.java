package Events.KeyEvents;

import App.Model;
import Events.Action;

public class RKeyPress implements Action {
    public RKeyPress() {}

    @Override
    public void handle(Model model, Object event) {
        model.reset();
    }
}