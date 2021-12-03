package Effect;

import Utils.MyPoint;
import View.View;

import java.awt.*;

public class EffectLine extends Effect {
    private final MyPoint last;

    public EffectLine(View view, MyPoint point, MyPoint last) {
        super(view, point);
        this.last = last;
    }

    @Override
    public void apply(Graphics graphics) {
        if (last==null || (view.isNewRoute() && !ctrlZ)) return;
        graphics.drawLine(last.x, last.y, point.x, point.y);
    }
}
