package Events.MouseEvents;

import App.Model;
import Events.Action;
import Math.Matrix;

public class MouseRelease implements Action {

    @Override
    public void handle(Model model, Object event) {
        Matrix CT = model.getCT();
        Matrix AT = model.getAT();

        model.setAT(CT.mult(AT));
        model.setCT(Matrix.I(4));
    }
}