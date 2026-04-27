import javax.swing.*;
import java.awt.*;

public class PanelClienteMenu extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public PanelClienteMenu(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 

        
        JPanel pnlCabecera = new JPanel(new GridBagLayout());
        pnlCabecera.setBackground(new Color(10, 0, 60)); 
        pnlCabecera.setPreferredSize(new Dimension(350, 140));

        GridBagConstraints gbcCabecera = new GridBagConstraints();

        gbcCabecera.gridx = 0; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 2;
        gbcCabecera.weightx = 0.33;
        gbcCabecera.anchor = GridBagConstraints.NORTHWEST;
        gbcCabecera.insets = new Insets(15, 15, 0, 0);
        
        JButton btnIconoIdioma = new JButton();
        btnIconoIdioma.setForeground(Color.WHITE);
        btnIconoIdioma.setBorderPainted(false);
        btnIconoIdioma.setContentAreaFilled(false);
        btnIconoIdioma.setFocusPainted(false);
        btnIconoIdioma.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
            ImageIcon iconIdioma = new ImageIcon("src/images/icono_idioma.png");
            if (iconIdioma.getIconWidth() > 0) {
                Image imgIdioma = iconIdioma.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                btnIconoIdioma.setIcon(new ImageIcon(imgIdioma));
                btnIconoIdioma.setText("");
            }
        } catch (Exception e) {}
        btnIconoIdioma.addActionListener(e -> {
            GestorIdiomas.cambiarIdiomaBase();
            ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal));
        });
        pnlCabecera.add(btnIconoIdioma, gbcCabecera);

        gbcCabecera.gridx = 1; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 1; 
        gbcCabecera.weightx = 0.33;
        gbcCabecera.anchor = GridBagConstraints.CENTER;
        gbcCabecera.insets = new Insets(15, 0, 5, 0);
        
        JLabel lblLogo = new JLabel();
        lblLogo.setForeground(Color.WHITE);
        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgLogo));
                lblLogo.setText(""); 
            }
        } catch (Exception e) {}
        pnlCabecera.add(lblLogo, gbcCabecera);

        gbcCabecera.gridy = 1;
        gbcCabecera.insets = new Insets(0, 0, 15, 0);
        JLabel lblLogoTexto = new JLabel("Laura Estilistas");
        lblLogoTexto.setForeground(Color.WHITE);
        lblLogoTexto.setFont(new Font("Arial", Font.BOLD, 22));
        pnlCabecera.add(lblLogoTexto, gbcCabecera);

        gbcCabecera.gridx = 2; 
        gbcCabecera.gridy = 0;
        gbcCabecera.weightx = 0.33;
        pnlCabecera.add(new JLabel(" "), gbcCabecera);

        add(pnlCabecera, BorderLayout.NORTH);

        JPanel pnlBotones = new JPanel(new GridBagLayout());
        pnlBotones.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JButton btnPedir = new JButton(GestorIdiomas.getTexto("cliente.pedir"));
        btnPedir.setBackground(new Color(10, 0, 60)); 
        btnPedir.setForeground(Color.WHITE);
        btnPedir.setFont(new Font("Arial", Font.BOLD, 20));
        btnPedir.setPreferredSize(new Dimension(200, 150));

        JButton btnAnular = new JButton(GestorIdiomas.getTexto("cliente.anular"));
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
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(Color.WHITE);
        JButton btnSalir = new JButton(GestorIdiomas.getTexto("cliente.cerrar"));
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));
        panel.add(btnSalir);
        return panel;
    }
}