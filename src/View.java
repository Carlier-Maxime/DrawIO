import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Controller controller;
    private ArrayList<Point> pointsToPaint;
    private Point lastPoint;
    private Color color;
    private final Color startColor;
    private final Color endColor;
    private static final int nbPas = 1000;

    public View() {
        controller = new Controller(this);
        pointsToPaint = new ArrayList<>();
        lastPoint = null;
        startColor = new Color(69, 255, 0);
        color = startColor;
        endColor = new Color(255,0,0);

        addKeyListener(controller);
        addMouseMotionListener(controller);
        requestFocus();
    }

    public void paintPoint(Point point){
        pointsToPaint.add(point);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (Point point : pointsToPaint){
            g.setColor(color);
            if (lastPoint==null) g.drawOval((int) point.getX(), (int) point.getY(), 1, 1);
            else g.drawLine(lastPoint.x, lastPoint.y, point.x, point.y);
            lastPoint = point;
            incrementColor();
        }
        pointsToPaint = new ArrayList<>();
    }

    private void incrementColor(){
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int incR = (startColor.getRed()-endColor.getRed())/nbPas;
        int incG = (startColor.getGreen()-endColor.getGreen())/nbPas;
        int incB = (startColor.getBlue()-endColor.getBlue())/nbPas;
        // ...
        color = new Color(r,g,b);
    }

    public void resetDraw(){

    }
}
