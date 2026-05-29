import javax.swing.*;
import java.awt.*;

public class PanelClienteMenu extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public PanelClienteMenu(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 

        
        // Cabecera 
        CabeceraPanel cabecera = new CabeceraPanel();
        add(cabecera, BorderLayout.NORTH);


        //Panel central
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

        JLabel lblSalir = new JLabel(GestorIdiomas.getTexto("cliente.cerrar"), SwingConstants.CENTER);
        lblSalir.setFont(new Font("Arial", Font.BOLD, 14));
        lblSalir.setForeground(new Color(10, 0, 60));

        JButton btnIconoSalir = new JButton();
        btnIconoSalir.setBorderPainted(false);
        btnIconoSalir.setContentAreaFilled(false);
        btnIconoSalir.setFocusPainted(false);
        btnIconoSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIconoSalir.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

        java.io.File archivo = new java.io.File("src/images/nav_salir.png");
        if (archivo.exists()) {
            ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
            btnIconoSalir.setIcon(new ImageIcon(img));
        } else {
            btnIconoSalir.setText("X");
            btnIconoSalir.setFont(new Font("Arial", Font.BOLD, 18));
            btnIconoSalir.setForeground(new Color(10, 0, 60));
        }

        btnIconoSalir.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));

        panel.add(lblSalir, BorderLayout.CENTER);
        panel.add(btnIconoSalir, BorderLayout.EAST);

        return panel;
    }
}