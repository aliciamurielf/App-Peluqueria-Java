import javax.swing.*;
import java.awt.*;

public class PanelClienteMenu extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public PanelClienteMenu(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); // Verde menta del prototipo

        // --- Cabecera ---
        JPanel pnlCabecera = new JPanel(new GridBagLayout());
        pnlCabecera.setBackground(new Color(10, 0, 60)); // Azul oscuro
        pnlCabecera.setPreferredSize(new Dimension(350, 140));

        GridBagConstraints gbcCabecera = new GridBagConstraints();

        // -- COLUMNA IZQUIERDA (Icono de idioma) --
        gbcCabecera.gridx = 0; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 2; // Ocupa las dos filas de altura
        gbcCabecera.weightx = 0.33; // Ocupa un tercio del espacio horizontal
        gbcCabecera.anchor = GridBagConstraints.NORTHWEST; // Pegado arriba a la izquierda
        gbcCabecera.insets = new Insets(15, 15, 0, 0); // Margen
        
        JLabel lblIconoIdioma = new JLabel("🌐"); // Emoji por si no hay imagen
        lblIconoIdioma.setForeground(Color.WHITE);
        try {
            ImageIcon iconIdioma = new ImageIcon("images/icono_idioma.png");
            if (iconIdioma.getIconWidth() > 0) {
                Image imgIdioma = iconIdioma.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                lblIconoIdioma.setIcon(new ImageIcon(imgIdioma));
                lblIconoIdioma.setText(""); // Borramos el emoji si carga la imagen
            }
        } catch (Exception e) {}
        pnlCabecera.add(lblIconoIdioma, gbcCabecera);

        // -- COLUMNA CENTRAL (Logo y Texto) --
        gbcCabecera.gridx = 1; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 1; 
        gbcCabecera.weightx = 0.33; // Ocupa el tercio central
        gbcCabecera.anchor = GridBagConstraints.CENTER;
        gbcCabecera.insets = new Insets(15, 0, 5, 0);
        
        JLabel lblLogo = new JLabel("✂️"); // Emoji por si no hay logo
        lblLogo.setForeground(Color.WHITE);
        try {
            ImageIcon iconLogo = new ImageIcon("images/logo.png");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgLogo));
                lblLogo.setText(""); 
            }
        } catch (Exception e) {}
        pnlCabecera.add(lblLogo, gbcCabecera);

        gbcCabecera.gridy = 1; // Fila de abajo para el título
        gbcCabecera.insets = new Insets(0, 0, 15, 0);
        JLabel lblLogoTexto = new JLabel("Laura Estilistas");
        lblLogoTexto.setForeground(Color.WHITE);
        lblLogoTexto.setFont(new Font("Arial", Font.BOLD, 22));
        pnlCabecera.add(lblLogoTexto, gbcCabecera);

        // -- COLUMNA DERECHA (Fantasma para equilibrar el centro) --
        gbcCabecera.gridx = 2; 
        gbcCabecera.gridy = 0;
        gbcCabecera.weightx = 0.33; // El último tercio vacío
        pnlCabecera.add(new JLabel(" "), gbcCabecera);

        add(pnlCabecera, BorderLayout.NORTH);


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
        btnAnular.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelClienteAnular(ventanaPrincipal)));
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