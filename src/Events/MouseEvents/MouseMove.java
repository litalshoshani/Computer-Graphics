package Events.MouseEvents;

import java.awt.event.MouseEvent;

import App.Model;
import Events.Action;
import Math.Matrix;
import Math.Point;

public class MouseMove implements Action {

    @Override
    public void handle(Model model, Object event) {
        MouseEvent e = (MouseEvent)(event);
        int x = e.getX();
        int y = e.getY();

        Point source = model.getSource();
        Point destination = new Point(x, y);
        Point center = model.getCenter();

        Matrix CT = model
            .getTransformationFactory()
            .create(source, destination, center);

        model.setCT(CT);
        model.tick();
    }
}