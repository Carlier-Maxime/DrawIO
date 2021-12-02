import java.awt.*;

public class MyPoint {
    public int x;
    public int y;
    public int effect;
    public Color color;

    public MyPoint(int x, int y, int effect, Color color) {
        this.x = x;
        this.y = y;
        this.effect = effect;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
