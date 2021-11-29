import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(){
        super("DrawIO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize());
        setBackground(Color.BLACK);
        getContentPane().setBackground(Color.BLACK);
        add(new View());
        setVisible(true);
        getContentPane().getComponent(0).requestFocus();
    }
}
