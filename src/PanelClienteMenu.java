import javax.swing.*;
import java.awt.*;

public class PanelClienteMenu extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public PanelClienteMenu(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); // Verde menta del prototipo

        // --- Cabecera ---
        add(crearCabecera(), BorderLayout.NORTH);

        // --- Cuerpo (Botones Grandes) ---
        JPanel pnlBotones = new JPanel(new GridBagLayout());
        pnlBotones.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JButton btnPedir = new JButton("PEDIR CITA");
        btnPedir.setBackground(new Color(10, 0, 60)); // Azul oscuro
        btnPedir.setForeground(Color.WHITE);
        btnPedir.setFont(new Font("Arial", Font.BOLD, 20));
        btnPedir.setPreferredSize(new Dimension(200, 150));

        JButton btnAnular = new JButton("ANULAR CITA");
        btnAnular.setBackground(new Color(255, 230, 150)); // Amarillo suave
        btnAnular.setForeground(Color.BLACK);
        btnAnular.setFont(new Font("Arial", Font.BOLD, 18));
        btnAnular.setPreferredSize(new Dimension(200, 80));

        gbc.gridy = 0; pnlBotones.add(btnPedir, gbc);
        gbc.gridy = 1; pnlBotones.add(btnAnular, gbc);
        add(pnlBotones, BorderLayout.CENTER);

        // --- Pie de página (Cerrar sesión) ---
        add(crearPieCerrarSesion(), BorderLayout.SOUTH);

        // Eventos
        btnPedir.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelClienteDatos(ventanaPrincipal)));
        // btnAnular.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelClienteAnular(ventanaPrincipal)));
    }

    private JPanel crearCabecera() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(10, 0, 60));
        panel.add(new JLabel("Laura Estilistas") {{ setForeground(Color.WHITE); }});
        return panel;
    }

    private JPanel crearPieCerrarSesion() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(Color.WHITE);
        JButton btnSalir = new JButton("CERRAR SESIÓN");
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));
        panel.add(btnSalir);
        return panel;
    }
}