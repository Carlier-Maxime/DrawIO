package Effect;

import Utils.MyPoint;
import View.View;

import java.awt.*;

public class EffectLaser extends Effect{
    private final EffectLine effectLine;
    private final MyPoint last;

    public EffectLaser(View view, MyPoint point, MyPoint last) {
        super(view, point);
        this.last = last;
        this.effectLine = new EffectLine(view,point,last);
    }

    @Override
    public void apply(Graphics graphics) {
        if (last==null || (view.isNewRoute() && !ctrlZ)) return;
        if (!ctrlZ) effectLine.apply(graphics);
        else effectLine.ctrlZ(graphics);
        double[] d = new double[2];
        d[0] = (last.x-point.x);
        d[1] = (last.y-point.y)-1;
        while (d[0]>1 || d[1]>1) {d[0]/=2; d[1]/=2;}
        int r = point.color.getRed();
        int g = point.color.getGreen();
        int b = point.color.getBlue();
        Point p1 = new Point();
        Point p2 = new Point();
        p2.x = point.x;
        p2.y = point.y;
        int n=1;
        while (r>0 || g>0 || b>0){
            p1.x = (int) (point.x+d[0]*n);
            p1.y = (int) (point.y+d[1]*n);
            if (ctrlZ) graphics.setColor(Color.BLACK);
            else graphics.setColor(new Color(r,g,b));
            graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
            p2.x = p1.x;
            p2.y = p1.y;
            r--; g--; b--;
            if (r<0) r=0; if (g<0) g=0; if (b<0) b=0;
            n++;
        }
    }
}
