import java.awt.*;
import java.awt.event.*;

public class Controller implements KeyListener, MouseMotionListener {
    private final View view;
    private boolean draw;
    //private boolean mouseInView;

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
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) view.crtlZ();
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
}
