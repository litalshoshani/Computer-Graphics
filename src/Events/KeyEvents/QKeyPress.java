package Events.KeyEvents;

import App.Model;
import Events.Action;

public class QKeyPress implements Action {
    public QKeyPress() {}

    @Override
    public void handle(Model model, Object event) {
        System.exit(0);
    }

}