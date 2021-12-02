import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class View extends JPanel {
    private Controller controller;
    private ArrayList<Point> pointsToPaint;
    private MyPoint[] history;
    private boolean newRoute;
    private ArrayList<Color> colors;
    private Color color;
    private int indexColor;
    private static final int nbPas = 255;
    private int pas;
    private double incR;
    private double incG;
    private double incB;
    private boolean reset;
    private static final int sizeHistory = 1000;
    private int effect;
    private final int nbEffect = 1;
    private boolean ctrlZ;

    public View() {
        controller = new Controller(this);
        pointsToPaint = new ArrayList<>();
        history = new MyPoint[sizeHistory];
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
        effect = 0;
        ctrlZ = false;

        addKeyListener(controller);
        addMouseMotionListener(controller);
        addMouseListener(controller);
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
        if (pointsToPaint!=null){
            for (Point point : pointsToPaint){
                // paint le point et la ligne si nécessaire
                g.setColor(color);
                if (getLastPoint()==null || newRoute) {g.drawOval((int) point.getX(), (int) point.getY(), 1, 1); newRoute = false;}
                else g.drawLine(getLastPoint().x, getLastPoint().y, point.x, point.y);
                applyEffect(g, new MyPoint(point.x, point.y, effect, color));
                setLastPoint(point);
                incrementColor();
            }
            pointsToPaint = new ArrayList<>();
        }
        if (ctrlZ) {
            g.setColor(Color.BLACK);
            MyPoint point = getLastPoint();
            if (point==null) return;
            removeLastPoint();
            g.drawOval(point.x, point.y, 1, 1);
            if (getLastPoint()!=null) {
                g.drawLine(getLastPoint().x, getLastPoint().y, point.x, point.y);
            }
            applyEffect(g, point);
            newRoute = true;
            ctrlZ = false;
        }
    }

    private void setLastPoint(Point point){
        boolean noSet = true;
        for (int i=0; i<history.length; i++){
            if (history[i]==null){
                history[i] = new MyPoint(point.x, point.y, effect, color);
                noSet = false;
            }
        }
        if (noSet){
            if (history.length - 1 >= 0) System.arraycopy(history, 1, history, 0, history.length - 1);
            history[history.length-1] = new MyPoint(point.x, point.y, effect, color);
        }
    }

    private MyPoint getLastPoint(){
        MyPoint point = null;
        for (MyPoint p : history){
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

    public void ctrlZ(){
        ctrlZ = true;
        repaint();
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

    public void effect(){
        effect++;
        if (effect>nbEffect) effect = 0;
    }

    public void applyEffect(Graphics graphics, MyPoint point){
        MyPoint last = getLastPoint();
        if (point.effect==0) return;
        switch (point.effect){
            case 1:
                if (last==null) return;
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
            default:
        }
    }
}
