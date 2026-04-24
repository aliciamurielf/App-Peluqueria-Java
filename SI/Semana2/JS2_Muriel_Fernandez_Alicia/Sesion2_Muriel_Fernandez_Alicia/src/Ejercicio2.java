import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio2 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("Ejercicio2");
        
        GridLayout gl = new GridLayout(3, 1);
        gl.setVgap(10);
        
        JPanel jp = new JPanel();
        jp.setLayout(gl);    
        
        JButton btnConfirmar = new JButton("1. Confirmar Acción");
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(jf, 
                        "¿Estás seguro?", 
                        "Salir", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
            }
        });
        jp.add(btnConfirmar);

        JButton btnMensaje = new JButton("2. Mensaje JOptionPane");
        btnMensaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jf, 
                        "Ha ocurrido un error", 
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        jp.add(btnMensaje);

        JButton btnLibre = new JButton("3. JDialog Libre");
        btnLibre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(jf, "Formulario Personalizado");
                
                JPanel panelDialog = new JPanel(new GridLayout(3, 1)); 
                
                panelDialog.add(new JLabel("Por favor, introduce tu nombre:")); 
                panelDialog.add(new JTextField(15));                          
                
                JButton btnCerrar = new JButton("Guardar y Cerrar");           
                
                btnCerrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false); 
                    }
                });
                panelDialog.add(btnCerrar);
                
                dialog.setContentPane(panelDialog);
                dialog.setSize(300, 150);
                dialog.setVisible(true);
            }
        });
        jp.add(btnLibre);

        Container cp = jf.getContentPane();
        cp.add(jp);
        
        jf.setSize(400, 250);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}