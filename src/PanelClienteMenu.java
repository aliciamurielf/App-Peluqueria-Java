import javax.swing.*;
import java.awt.*;

public class PanelClienteMenu extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public PanelClienteMenu(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 

        
        // Cabecera refinada (estilo SI): logo centrado arriba y título debajo
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(10, 0, 60));
        header.setPreferredSize(new Dimension(0, 120));

        JPanel center = new JPanel(new GridLayout(2, 1));
        center.setOpaque(false);
        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                JLabel lblLogo = new JLabel(new ImageIcon(imgLogo), SwingConstants.CENTER);
                lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
                center.add(lblLogo);
            }
        } catch (Exception e) {}
        JLabel lblTituloCab = new JLabel("Laura Estilistas", SwingConstants.CENTER);
        lblTituloCab.setForeground(Color.WHITE);
        lblTituloCab.setFont(new Font("Arial", Font.BOLD, 22));
        center.add(lblTituloCab);
        header.add(center, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel pnlBotones = new JPanel(new GridBagLayout());
        pnlBotones.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JButton btnPedir = new JButton("<html><center>" + GestorIdiomas.getTexto("cliente.pedir") + "</center></html>");
        btnPedir.setBackground(new Color(10, 0, 60)); 
        btnPedir.setForeground(Color.WHITE);
        btnPedir.setFont(new Font("Arial", Font.BOLD, 20));
        btnPedir.setPreferredSize(new Dimension(200, 150));

        JButton btnAnular = new JButton("<html><center>" + GestorIdiomas.getTexto("cliente.anular") + "</center></html>");
        btnAnular.setBackground(new Color(255, 230, 150)); 
        btnAnular.setForeground(Color.BLACK);
        btnAnular.setFont(new Font("Arial", Font.BOLD, 18));
        btnAnular.setPreferredSize(new Dimension(200, 80));

        gbc.gridy = 0; pnlBotones.add(btnPedir, gbc);
        gbc.gridy = 1; pnlBotones.add(btnAnular, gbc);
        add(pnlBotones, BorderLayout.CENTER);

        add(crearPieCerrarSesion(), BorderLayout.SOUTH);

        btnPedir.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelClienteDatos(ventanaPrincipal)));
        btnAnular.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelClienteAnular(ventanaPrincipal)));
    }

    private JPanel crearPieCerrarSesion() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 75));

        // Botón que ocupa toda la franja
        JButton btnSalir = new JButton(GestorIdiomas.getTexto("cliente.cerrar"));
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setForeground(new Color(10, 0, 60));
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setFocusPainted(false);
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));

        // Icono nav_salir en la esquina derecha
        JLabel lblIconoSalir = new JLabel();
        lblIconoSalir.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        java.io.File archivo = new java.io.File("src/images/nav_salir.png");
        if (archivo.exists()) {
            ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
            lblIconoSalir.setIcon(new ImageIcon(img));
        }

        panel.add(btnSalir, BorderLayout.CENTER);
        panel.add(lblIconoSalir, BorderLayout.EAST);

        return panel;
    }
}