package Utils;

import Effect.Effect;

import java.awt.*;

public class MyPoint {
    public int x;
    public int y;
    public Effect effect;
    public Color color;

    public MyPoint(int x, int y, Effect effect, Color color) {
        this.x = x;
        this.y = y;
        this.effect = effect;
        this.color = color;
    }

    public MyPoint(int x, int y, Color color) {
        this(x,y,null,color);
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
