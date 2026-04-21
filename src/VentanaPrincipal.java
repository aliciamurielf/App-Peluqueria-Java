import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        super("Laura Estilistas - App"); // Título de la ventana [cite: 131]
        
        // Ajustamos el tamaño simulando una pantalla de móvil [cite: 132]
        setSize(350, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Para no deformar el diseño [cite: 678-679]
        setLocationRelativeTo(null); // Centrar en la pantalla

        // Iniciamos cargando el panel de Login
        PanelLogin panelLogin = new PanelLogin(this);
        setContentPane(panelLogin);
    }

    // Método que utilizaremos para cambiar de vistas (Manejo de Vistas) 
    public void cambiarVista(JPanel nuevoPanel) {
        setContentPane(nuevoPanel); 
        revalidate(); // Volver a pintar la ventana [cite: 588]
    }

    public static void main(String[] args) {
        // ACTIVAR NIMBUS LOOK AND FEEL PARA UN DISEÑO MÁS MODERNO
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si falla, usará el de por defecto
        }

        // Ejecutar la interfaz
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}