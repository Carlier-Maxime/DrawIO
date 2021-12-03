package Effect;

import Utils.MyPoint;
import View.View;

import java.awt.*;
import java.util.Arrays;

public class EffectStar extends Effect{
    private static final int ecartMax = 32;
    private static final int nbEtoile = 32;

    private Point[] stars;

    public EffectStar(View view, MyPoint point) {
        super(view, point);
        stars = new Point[nbEtoile];
    }

    @Override
    public void apply(Graphics graphics) {
        int r = point.color.getRed();
        int g = point.color.getGreen();
        int b = point.color.getBlue();
        for (int i=0; i<nbEtoile; i++){
            if (!ctrlZ) {
                int ecartX = rng.nextInt((ecartMax - ecartMax*-1) + 1) + ecartMax*-1;
                int ecartY = rng.nextInt((ecartMax - ecartMax*-1) + 1) + ecartMax*-1;
                int brillance = rng.nextInt(200);
                graphics.setColor(new Color((r*brillance)/255, (g*brillance)/255,(b*brillance)/255));
                stars[i] = new Point(point.x+ecartX,point.y+ecartY);
            }
            if (stars[i]==null) break;
            graphics.drawOval(stars[i].x,stars[i].y,1,1);
        }
        System.out.println(Arrays.toString(stars));
    }
}
