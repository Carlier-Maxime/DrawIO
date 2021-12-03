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
        Graphics2D g2d = (Graphics2D) graphics;
        Stroke stroke = g2d.getStroke();
        if (!ctrlZ) g2d.setStroke(new BasicStroke(view.getBrushSize()));
        else g2d.setStroke(new BasicStroke(brushSize));
        g2d.drawLine(last.x, last.y, point.x, point.y);
        g2d.setStroke(stroke);
    }
}
