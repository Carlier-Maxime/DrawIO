import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class View extends JPanel {
    private Controller controller;
    private ArrayList<Point> pointsToPaint;
    private Point[] history;
    private boolean newRoute;
    private ArrayList<Color> colors;
    private Color color;
    private int indexColor;
    private static final int nbPas = 512;
    private int pas;
    private double incR;
    private double incG;
    private double incB;
    private boolean reset;
    private static final int sizeHistory = 1000;

    public View() {
        controller = new Controller(this);
        pointsToPaint = new ArrayList<>();
        history = new Point[sizeHistory];
        colors = new ArrayList<>(Arrays.asList(
                new Color(255, 0,0),
                new Color(255, 119,0),
                new Color(255, 242,0),
                new Color(53, 246,0),
                new Color(0, 13, 255),
                new Color(149, 0, 255),
                new Color(255,255,255)
        ));
        indexColor = 0;
        color = colors.get(indexColor);
        incR = (colors.get(indexColor+1).getRed()-colors.get(indexColor).getRed())*1.0/nbPas;
        incG = (colors.get(indexColor+1).getGreen()-colors.get(indexColor).getGreen())*1.0/nbPas;
        incB = (colors.get(indexColor+1).getBlue()-colors.get(indexColor).getBlue())*1.0/nbPas;
        pas = 0;
        reset = false;
        newRoute = true;

        addKeyListener(controller);
        addMouseMotionListener(controller);
        requestFocus();
    }

    public void paintPoint(Point point){
        pointsToPaint.add(point);
        paintComponent(getGraphics());
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
        if (pointsToPaint==null) return;
        for (Point point : pointsToPaint){
            g.setColor(color);
            if (getLastPoint()==null || newRoute) {g.drawOval((int) point.getX(), (int) point.getY(), 1, 1); newRoute = false;}
            else g.drawLine(getLastPoint().x, getLastPoint().y, point.x, point.y);
            setLastPoint(point);
            incrementColor();
        }
        pointsToPaint = new ArrayList<>();
    }

    private void setLastPoint(Point point){
        boolean noSet = true;
        for (int i=0; i<history.length; i++){
            if (history[i]==null){
                history[i] = point;
                noSet = false;
            }
        }
        if (noSet){
            if (history.length - 1 >= 0) System.arraycopy(history, 1, history, 0, history.length - 1);
            history[history.length-1] = point;
        }
    }

    private Point getLastPoint(){
        Point point = null;
        for (Point p : history){
            if (p==null) break;
            point = p;
        }
        return point;
    }

    private void incrementColor(){
        if (pas>=nbPas) {
            indexColor++;
            if (indexColor>=colors.size()) indexColor=0;
            int endIndex = indexColor+1;
            if (endIndex>=colors.size()) endIndex=0;
            color = colors.get(indexColor);
            pas = 0;
            incR = (colors.get(endIndex).getRed()-colors.get(indexColor).getRed())*1.0/nbPas;
            incG = (colors.get(endIndex).getGreen()-colors.get(indexColor).getGreen())*1.0/nbPas;
            incB = (colors.get(endIndex).getBlue()-colors.get(indexColor).getBlue())*1.0/nbPas;
        }
        else color = new Color((int) (colors.get(indexColor).getRed()+incR*pas), (int) (colors.get(indexColor).getGreen()+incG*pas), (int) (colors.get(indexColor).getBlue()+incB*pas));
        pas++;
    }

    public void resetDraw(){
        reset = true;
        resetLastPoint();
        repaint();
        revalidate();
    }

    public void resetLastPoint(){newRoute = true;}

    public void crtlZ(){
        getGraphics().setColor(Color.BLACK);
        Point point = getLastPoint();
        if (point==null) return;
        removeLastPoint();
        if (getLastPoint()==null) getGraphics().drawOval(point.x, point.y, 1, 1);
        else {
            getGraphics().drawLine(getLastPoint().x, getLastPoint().y, point.x, point.y);
            getGraphics().drawOval(point.x, point.y, 1, 1);
        }
        newRoute = true;
    }

    private void removeLastPoint(){
        boolean removed = false;
        for (int i=0; i<history.length; i++){
            if (history[i]==null){
                if (i==0) return;
                history[i-1] = null;
                removed = true;
            }
        }
        if (!removed) history[history.length-1] = null;
    }
}
