import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Controller controller;
    private ArrayList<Point> pointsToPaint;
    private Point lastPoint;
    private Color color;
    private Color startColor;
    private Color endColor;
    private static final int nbPas = 1000;
    private int pas;
    private double incR;
    private double incG;
    private double incB;
    private boolean reset;

    public View() {
        controller = new Controller(this);
        pointsToPaint = new ArrayList<>();
        lastPoint = null;
        startColor = new Color(69, 255, 0);
        color = startColor;
        endColor = new Color(255, 0, 0);
        incR = (endColor.getRed()-startColor.getRed())*1.0/nbPas;
        incG = (endColor.getGreen()-startColor.getGreen())*1.0/nbPas;
        incB = (endColor.getBlue()-startColor.getBlue())*1.0/nbPas;
        pas = 0;
        reset = false;

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
        if (reset){
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());
            reset=false;
            return;
        }
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
        if (pas>=nbPas) {
            color = endColor;
            pas = 0;
            Color tmp = startColor;
            startColor = endColor;
            endColor = tmp;
            incR*=-1;
            incG*=-1;
            incB*=-1;
        }
        else color = new Color((int) (startColor.getRed()+incR*pas), (int) (startColor.getGreen()+incG*pas), (int) (startColor.getBlue()+incB*pas));
        pas++;
    }

    public void resetDraw(){
        reset = true;
        repaint();
        revalidate();
    }

    public void resetLastPoint(){lastPoint = null;}
}
