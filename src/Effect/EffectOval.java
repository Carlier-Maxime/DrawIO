package Effect;

import Utils.MyPoint;
import View.View;

import java.awt.*;

public class EffectOval extends Effect{
    public EffectOval(View view, MyPoint point) {
        super(view, point);
    }

    @Override
    public void apply(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        Stroke stroke = g2d.getStroke();
        if (!ctrlZ) {
            g2d.setStroke(new BasicStroke(view.getBrushSize()));
            brushSize = view.getBrushSize();
        }
        else g2d.setStroke(new BasicStroke(brushSize));
        g2d.drawOval(point.x, point.y, brushSize*10,brushSize*10);
        g2d.setStroke(stroke);
    }
}
