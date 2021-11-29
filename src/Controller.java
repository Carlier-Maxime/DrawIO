import java.awt.*;
import java.awt.event.*;

public class Controller implements KeyListener, MouseMotionListener {
    private final View view;
    //private boolean mouseInView;

    public Controller(View view) {
        this.view = view;
        //mouseInView = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) view.resetDraw();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Thread t = new Thread(() -> {
            Point point = e.getLocationOnScreen();
            // ...
            view.paintPoint(point);
        });
        t.start();
    }
}
