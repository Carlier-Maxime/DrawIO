package Effect;

import Utils.MyPoint;
import View.View;

import java.awt.*;
import java.util.Random;

public abstract class Effect {
    protected static final Random rng = new Random();
    protected View view;
    protected boolean ctrlZ;
    protected MyPoint point;

    public Effect(View view, MyPoint point) {
        this.view = view;
        this.point = point;
        ctrlZ = false;
    }

    public abstract void apply(Graphics graphics);
    public void ctrlZ(Graphics graphics){
        ctrlZ = true;
        apply(graphics);
        ctrlZ = false;
    }
}
