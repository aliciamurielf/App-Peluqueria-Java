import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Ejemplo de JDialog. EL JFrame principal se esconde mientras que el JDialog 
 * está activo.
 */
public class Example_JDialog2 {
    private JFrame jf;
    private JDialog jd;

    public static void main(String[] args) {
        new Example_JDialog2();
    }
	
    public Example_JDialog2()
    {
        // Construcción de ventana principal
        jf = new JFrame("Ventana Principal");
        jf.setLayout(new FlowLayout());
        JButton boton = new JButton("Abrir JDialog");
        jf.getContentPane().add(boton);
        jf.setSize(250,100);
	
        // Construcción de ventana secundaria
        jd = new JDialog(jf,"Ventana Secundaria");
        JLabel etiqueta = new JLabel("Mi JDialog");
        jd.getContentPane().add(etiqueta);
        jd.setSize(jf.getSize());

        // Hacer que el botón abra la ventana secundaria y cierre la principal
        // Idealmente esto iría en otra clase, pero esto es un ejemplo
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                jd.setVisible(true);
            }
        });
		
        // Hacer que al cerrarse la secundaria con la x de arriba a la
        // derecha, se muestre la primaria
        jd.addWindowListener(new FocusFrameListener(jf, jd));
	
        // Mostrar la ventana principal
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}