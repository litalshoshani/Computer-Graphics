package Events.MouseEvents;

import java.awt.event.MouseEvent;

import App.Model;
import Events.Action;
import Math.Matrix;
import Math.Point;
import Transformations.TransformationFactory;
import View.View;

public class MousePress implements Action {
    

    @Override
    public void handle(Model model, Object event) {
        MouseEvent e = (MouseEvent)(event);
        int x = e.getX();
        int y = e.getY();

        View view = model.getView();
        
        Matrix before = view.getMinusTL();
        Matrix after = view.getTL();

        model.setTransformationFactory(
            new TransformationFactory(
                model.getStrategy().apply(new Point(x, y)),
                model.getOrientation(),
                new Point(view.getRight() - view.getLeft(), view.getTop() - view.getBottom()),
                new Point(view.getViewport().getWidth(), view.getViewport().getHeight()),
                before,
                after
            )
        );

        model.setSource(new Point(x, y));
    }
}