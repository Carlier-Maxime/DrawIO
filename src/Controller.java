import java.awt.*;
import java.awt.event.*;

public class Controller implements KeyListener, MouseMotionListener, MouseListener {
    private final View view;
    private boolean draw;
    private boolean line;

    public Controller(View view) {
        this.view = view;
        draw = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {draw = !draw; view.resetLastPoint();}
        if (e.getKeyCode() == KeyEvent.VK_R) view.resetDraw();
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) view.ctrlZ();
        if (e.getKeyCode() == KeyEvent.VK_L) {
            line = !line;
            draw = !line;
            view.resetLastPoint();
        }
        if (e.getKeyCode() == KeyEvent.VK_E) view.effect();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!draw) return;
        Thread t = new Thread(() -> {
            Point point = e.getLocationOnScreen();
            point.x-=8;
            point.y-=32;
            view.paintPoint(point);
        });
        t.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (line){
            if (e.getButton()==1){
                Point point = e.getLocationOnScreen();
                point.x-=8;
                point.y-=32;
                view.paintPoint(point);
            } else if (e.getButton()==3){
                view.resetLastPoint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
