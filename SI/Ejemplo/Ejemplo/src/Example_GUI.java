import javax.swing.*;

/**
 * Primera GUI en Java Swing
 */
public class Example_GUI {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Título de ventana");
        
        jf.setSize(400, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}