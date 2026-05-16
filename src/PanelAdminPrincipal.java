import javax.swing.*;
import java.awt.*;

public class PanelAdminPrincipal extends JPanel {

    private VentanaPrincipal ventanaPrincipal;
    private JPanel pnlContenidoCentral;

    public PanelAdminPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206));

        // CABECERA 
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(10, 0, 60));
        header.setPreferredSize(new Dimension(0, 120));

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);

        JLabel lblLogo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon icon = (ImageIcon) getIcon();
                if (icon != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 55, 55));
                    g2.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                    g2.dispose();
                }
            }
        };
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLogo.setPreferredSize(new Dimension(60, 60));
        lblLogo.setMaximumSize(new Dimension(60, 60));

        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgLogo));
            }
        } catch (Exception e) {}

        JLabel lblTituloCab = new JLabel("Laura Estilistas", SwingConstants.CENTER);
        lblTituloCab.setForeground(Color.WHITE);
        lblTituloCab.setFont(new Font("Arial", Font.BOLD, 22));
        lblTituloCab.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalGlue());
        center.add(lblLogo);
        center.add(Box.createVerticalStrut(5));
        center.add(lblTituloCab);
        center.add(Box.createVerticalGlue());

        header.add(center, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // CONTENIDO CENTRAL 
        pnlContenidoCentral = new JPanel(new BorderLayout());
        pnlContenidoCentral.setOpaque(false);
        add(pnlContenidoCentral, BorderLayout.CENTER);

        JPanel pnlNavegacion = crearBarraNavegacion();
        add(pnlNavegacion, BorderLayout.SOUTH);
        cambiarVistaInterna(new PanelAdminInicio());
    }

    public void cambiarVistaInterna(JPanel nuevaVista) {
        pnlContenidoCentral.removeAll();
        pnlContenidoCentral.add(nuevaVista, BorderLayout.CENTER);
        pnlContenidoCentral.revalidate();
        pnlContenidoCentral.repaint();
    }

    private JPanel crearBarraNavegacion() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(350, 75));

        JButton btnInicio = new JButton();
        JButton btnAgenda = new JButton();
        JButton btnClientes = new JButton();
        JButton btnInventario = new JButton();
        JButton btnSalir = new JButton();

        JButton[] botones = {btnInicio, btnAgenda, btnClientes, btnInventario, btnSalir};
        String[] nombresArchivos = {"nav_inicio.png", "nav_agenda.png", "nav_clientes.png", "nav_inventario.png", "nav_salir.png"};
        String[] textos = {"nav.inicio", "nav.agenda", "nav.clientes", "nav.inventario", "nav.salir"};

        for (int i = 0; i < botones.length; i++) {
            botones[i].setOpaque(false);
            botones[i].setContentAreaFilled(false);
            botones[i].setFocusPainted(false);
            botones[i].setBorderPainted(false);
            botones[i].setText("");
            botones[i].setToolTipText(GestorIdiomas.getTexto(textos[i]));
            java.io.File archivo = new java.io.File("src/images/" + nombresArchivos[i]);
            if (archivo.exists()) {
                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                botones[i].setIcon(new ImageIcon(img));
            } else {
                botones[i].setText("?");
            }
            panel.add(botones[i]);
        }

        btnInicio.addActionListener(e -> cambiarVistaInterna(new PanelAdminInicio()));

        btnAgenda.addActionListener(e -> {
            try {
                cambiarVistaInterna(new PanelAdminAgenda());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnClientes.addActionListener(e -> cambiarVistaInterna(new PanelAdminClientes(this)));

        btnInventario.addActionListener(e -> cambiarVistaInterna(new PanelAdminInventario(ventanaPrincipal)));

        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    GestorIdiomas.getTexto("nav.confirmar_salir"),
                    GestorIdiomas.getTexto("nav.salir"),
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });

        return panel;
    }
}