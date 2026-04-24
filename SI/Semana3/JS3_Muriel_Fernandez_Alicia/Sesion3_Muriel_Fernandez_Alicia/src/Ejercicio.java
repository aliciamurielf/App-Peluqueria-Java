import javax.swing.*;
import java.awt.*;

public class Ejercicio {
    public static void main(String[] args) {
        
        // 1. LOOK AND FEEL
        try {
            JFrame.setDefaultLookAndFeelDecorated(true); 
            JDialog.setDefaultLookAndFeelDecorated(true); 
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch(Exception e) {
            System.out.println("An error ocurred! Using default look and feel.");
            JFrame.setDefaultLookAndFeelDecorated(false);
            JDialog.setDefaultLookAndFeelDecorated(false);
        }

        // Ventana principal
        JFrame jf = new JFrame("Ejercicio");
        
        // 2. PANEL PRINCIPAL
        JPanel main_panel = new JPanel(new BorderLayout());

        // 3. CABECERA 
        JPanel headerPanel = new JPanel(new FlowLayout());
        ImageIcon icon = new ImageIcon("Logo_UCO.png"); 
        Image newImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); 
        icon.setImage(newImage); 
        
        headerPanel.add(new JLabel("Cabecera Principal"));
        headerPanel.add(new JLabel(icon)); 
        main_panel.add(headerPanel, BorderLayout.NORTH); 

        //CREACIÓN DE LAS 3 VISTA
        
        // Vista 1
        JPanel vista1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        vista1.add(new JLabel("Vista 1"));
        vista1.add(new JTextField(10)); 

        // Vista 2
        JPanel vista2 = new JPanel(new GridLayout(2, 2, 10, 10)); 
        vista2.add(new JLabel("Vista 2 - Cuadrícula", SwingConstants.CENTER));
        ImageIcon iconVista2 = new ImageIcon("emblema-ing-informtica.png");
        Image img2 = iconVista2.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        iconVista2.setImage(img2);
        vista2.add(new JLabel(iconVista2)); 
        JButton btnIcono = new JButton("Botón con Imagen", iconVista2); 
        vista2.add(btnIcono);
        vista2.add(new JButton("Botón Normal"));

        // Vista 3
        JPanel vista3 = new JPanel(new BorderLayout());
        vista3.add(new JLabel("Vista 3", SwingConstants.CENTER), BorderLayout.NORTH);
        JButton btnDialog = new JButton("Abrir JDialog");
        
        btnDialog.addActionListener(e -> {
            JDialog dialog = new JDialog(jf, "Ventana secundaria");
            JPanel panelDialogo = new JPanel(new FlowLayout());
            panelDialogo.add(new JLabel("Esta ventana es secundaria."));
            panelDialogo.add(new JButton("OK"));
            dialog.setContentPane(panelDialogo);
            dialog.setSize(400, 150);
            dialog.setVisible(true);
        });
        vista3.add(btnDialog, BorderLayout.CENTER);

        // ESTADO INICIAL 
        final JPanel[] panelActual = {vista1}; 
        vista1.setVisible(true); 
        main_panel.add(vista1, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout());
        JButton btnIrVista1 = new JButton("Ir a Vista 1"); 
        JButton btnIrVista2 = new JButton("Ir a Vista 2");
        JButton btnIrVista3 = new JButton("Ir a Vista 3");

        btnIrVista1.addActionListener(e -> {
            panelActual[0].setVisible(false); 
            main_panel.remove(panelActual[0]); 
            vista1.setVisible(true);
            main_panel.add(vista1, BorderLayout.CENTER); 
            panelActual[0] = vista1;
            jf.revalidate(); 
            jf.repaint(); 
        });

        btnIrVista2.addActionListener(e -> {
            panelActual[0].setVisible(false);
            main_panel.remove(panelActual[0]); 
            vista2.setVisible(true);
            main_panel.add(vista2, BorderLayout.CENTER);
            panelActual[0] = vista2;
            jf.revalidate(); 
            jf.repaint();
        });

        btnIrVista3.addActionListener(e -> {
            panelActual[0].setVisible(false); 
            main_panel.remove(panelActual[0]);
            vista3.setVisible(true);
            main_panel.add(vista3, BorderLayout.CENTER);
            panelActual[0] = vista3;
            jf.revalidate(); 
            jf.repaint();
        });

        footerPanel.add(btnIrVista1);
        footerPanel.add(btnIrVista2);
        footerPanel.add(btnIrVista3);
        
        main_panel.add(footerPanel, BorderLayout.SOUTH);

        jf.setContentPane(main_panel); 
        jf.setSize(500, 350);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}