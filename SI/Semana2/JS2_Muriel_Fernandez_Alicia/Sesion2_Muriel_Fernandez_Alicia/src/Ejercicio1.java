import javax.swing.*;
import java.awt.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Ejercicio1");
    
        jf.setLayout(new FlowLayout());
        
        JLabel etiqueta = new JLabel("Original");
        JTextField campoTexto = new JTextField(10);
        JButton botonEnviar = new JButton("Enviar");
        
        botonEnviar.addActionListener(new ModificarLabelListener(etiqueta, campoTexto));
        
        Container cp = jf.getContentPane();
        cp.add(etiqueta);
        cp.add(campoTexto);
        cp.add(botonEnviar);
        
        jf.setSize(400, 150);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
