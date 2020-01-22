package Transformations;

import java.util.function.Function;

import Math.Point;
import View.View;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * TransformationStrategy class handles the way
 * we know which transformation case are we at
 * according to where we clicked @ the screen
 */
public class TransformationStrategy implements Function<Point, TransformationCase> {

    private View view;
    public TransformationStrategy(View view) {
        this.view = view;
    }
    
    @Override
    public TransformationCase apply(Point t) {
        double x = t.getArgs()[0];
        double y = t.getArgs()[1];

        double width = view.getViewport().getWidth();
        double height = view.getViewport().getHeight();

        if ((x < width / 3 && (y >= height / 3 && y < 2 * height / 3))
            || ((x >= width / 3 && x < 2 * width / 3) && y < height / 3)
            || ((x >= width / 3 && x < 2 * width / 3) && y >= 2 * height / 3)
            || (x >= 2 * width / 3 && (y >= height / 3 && y < 2 * height / 3))) {

            return TransformationCase.SCALE;              
        }

        if ((x < width / 3 && y >= 2 * height / 3)
            || (x < width / 3 && y < height / 3)
            || (x >= 2 * width / 3 && y < height / 3)
            || (x >= 2 * width / 3 && y >= 2 * height / 3)) {
                
            return TransformationCase.ROTATE;              
        }

        return TransformationCase.TRANSLATE;              
    }

}