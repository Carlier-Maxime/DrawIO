package Effect;

import Utils.MyPoint;
import View.View;

import java.awt.*;

public class EffectBubble extends Effect{
    private MyPoint[] bubbles;
    private int ecartMax;
    private int nbBubble;

    public EffectBubble(View view, MyPoint point) {
        super(view, point);
        ecartMax = view.getBrushSize()*100;
        nbBubble = view.getBrushSize();
        bubbles = new MyPoint[nbBubble];
    }

    @Override
    public void apply(Graphics graphics) {
        int r = point.color.getRed();
        int g = point.color.getGreen();
        int b = point.color.getBlue();
        if (!ctrlZ) {
            ecartMax = view.getBrushSize()*100;
            nbBubble = view.getBrushSize();
            bubbles = new MyPoint[nbBubble];
        }
        for (int i=0; i<nbBubble; i++){
            if (!ctrlZ) {
                int ecartX = rng.nextInt((ecartMax - ecartMax*-1) + 1) + ecartMax*-1;
                int ecartY = rng.nextInt((ecartMax - ecartMax*-1) + 1) + ecartMax*-1;
                int brillance = rng.nextInt(200);
                Color color = new Color((r*brillance)/255, (g*brillance)/255,(b*brillance)/255);
                graphics.setColor(color);
                bubbles[i] = new MyPoint(point.x+ecartX,point.y+ecartY,color);
                bubbles[i].setEffect(new EffectOval(view, bubbles[i]));
            }
            if (bubbles[i]==null) break;
            if (!ctrlZ) bubbles[i].effect.apply(graphics);
            else bubbles[i].effect.ctrlZ(graphics);
        }
    }
}
