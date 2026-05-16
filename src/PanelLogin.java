import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {

    private VentanaPrincipal ventanaPrincipal;
    private GestorUsuarios gestorUsuarios = new GestorUsuarios();

    public PanelLogin(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206));

        // ── CABECERA ──────────────────────────────────────────────────────────
        JPanel pnlCabecera = new JPanel(new BorderLayout());
        pnlCabecera.setBackground(new Color(10, 0, 60));
        pnlCabecera.setPreferredSize(new Dimension(450, 140));

        // --- Icono idioma (izquierda) ---
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
            }
        } catch (Exception e) {}

        btnIconoIdioma.addActionListener(e -> {
            GestorIdiomas.cambiarIdiomaBase();
            ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
        });

        // Panel izquierdo con margen para el icono
        JPanel pnlIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlIzquierda.setOpaque(false);
        pnlIzquierda.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        pnlIzquierda.add(btnIconoIdioma);

        // --- Logo + Título centrados ---
        JPanel pnlCentroHeader = new JPanel();
        pnlCentroHeader.setLayout(new BoxLayout(pnlCentroHeader, BoxLayout.Y_AXIS));
        pnlCentroHeader.setOpaque(false);

        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgLogo));
            }
        } catch (Exception e) {}

        JLabel lblLogoTexto = new JLabel("Laura Estilistas");
        lblLogoTexto.setForeground(Color.WHITE);
        lblLogoTexto.setFont(new Font("Arial", Font.BOLD, 22));
        lblLogoTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlCentroHeader.add(Box.createVerticalGlue());
        pnlCentroHeader.add(lblLogo);
        pnlCentroHeader.add(Box.createVerticalStrut(5));
        pnlCentroHeader.add(lblLogoTexto);
        pnlCentroHeader.add(Box.createVerticalGlue());

        // Panel derecho vacío del mismo tamaño que el izquierdo (para compensar)
        JPanel pnlDerecha = new JPanel();
        pnlDerecha.setOpaque(false);
        pnlDerecha.setPreferredSize(pnlIzquierda.getPreferredSize());

        pnlCabecera.add(pnlIzquierda, BorderLayout.WEST);
        pnlCabecera.add(pnlCentroHeader, BorderLayout.CENTER);
        pnlCabecera.add(pnlDerecha, BorderLayout.EAST);

        add(pnlCabecera, BorderLayout.NORTH);

        // ── CENTRO (formulario) ───────────────────────────────────────────────
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false);

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblTitulo = new JLabel("<html><center>" + GestorIdiomas.getTexto("login.titulo") + "</center></html>", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);

        JLabel lblSubtitulo = new JLabel(GestorIdiomas.getTexto("login.subtitulo"), SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);

        JLabel lblUsuario = new JLabel(GestorIdiomas.getTexto("login.usuario"));
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField txtUsuario = new JTextField(15);

        JLabel lblContrasena = new JLabel(GestorIdiomas.getTexto("login.contrasena"));
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 12));
        JPasswordField txtContrasena = new JPasswordField(15);

        JCheckBox chkGuardar = new JCheckBox(GestorIdiomas.getTexto("login.recordar"));
        chkGuardar.setBackground(Color.WHITE);
        chkGuardar.setFont(new Font("Arial", Font.PLAIN, 10));

        JButton btnEntrar = new JButton(GestorIdiomas.getTexto("login.entrar"));
        btnEntrar.setBackground(new Color(10, 0, 60));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);

        JButton btnRecuperar = new JButton(GestorIdiomas.getTexto("login.recuperar"));
        btnRecuperar.setFont(new Font("Arial", Font.PLAIN, 10));
        btnRecuperar.setForeground(Color.GRAY);
        btnRecuperar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRecuperar.setBorderPainted(false);
        btnRecuperar.setContentAreaFilled(false);
        btnRecuperar.setFocusPainted(false);

        JButton btnRegistro = new JButton(GestorIdiomas.getTexto("login.registro"));
        btnRegistro.setFont(new Font("Arial", Font.PLAIN, 10));
        btnRegistro.setForeground(Color.GRAY);
        btnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistro.setBorderPainted(false);
        btnRegistro.setContentAreaFilled(false);
        btnRegistro.setFocusPainted(false);

        JButton btnRegistroAdmin = new JButton(GestorIdiomas.getTexto("login.admin"));
        btnRegistroAdmin.setFont(new Font("Arial", Font.PLAIN, 10));
        btnRegistroAdmin.setForeground(Color.GRAY);
        btnRegistroAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistroAdmin.setBorderPainted(false);
        btnRegistroAdmin.setContentAreaFilled(false);
        btnRegistroAdmin.setFocusPainted(false);

        // ── LISTENERS ─────────────────────────────────────────────────────────
        btnRegistroAdmin.addActionListener(e -> {
            ventanaPrincipal.cambiarVista(new PanelSolicitudAdmin(ventanaPrincipal));
        });

        btnRegistro.addActionListener(e -> {
            ventanaPrincipal.cambiarVista(new PanelRegistro(ventanaPrincipal));
        });

        btnEntrar.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtContrasena.getPassword());

            String rol = gestorUsuarios.obtenerRol(user, pass);

            if (rol != null) {
                if (rol.equalsIgnoreCase("admin")) {
                    ventanaPrincipal.cambiarVista(new PanelAdminPrincipal(ventanaPrincipal));
                } else if (rol.equalsIgnoreCase("cliente")) {
                    JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("login.bienvenida_cliente"));
                    ventanaPrincipal.setUsuarioLogueado(user);
                    ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal));
                }
            } else {
                new DialogoErrorLogin(ventanaPrincipal).setVisible(true);
            }
        });

        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.cambiarVista(new PanelRecuperar(ventanaPrincipal));
            }
        });

        // ── AÑADIR COMPONENTES A LA TARJETA ───────────────────────────────────
        gbc.gridy = 0;
        tarjetaBlanca.add(lblTitulo, gbc);
        gbc.gridy = 1;
        tarjetaBlanca.add(lblSubtitulo, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 0, 2, 0);
        tarjetaBlanca.add(lblUsuario, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        tarjetaBlanca.add(txtUsuario, gbc);
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 2, 0);
        tarjetaBlanca.add(lblContrasena, gbc);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 5, 0);
        tarjetaBlanca.add(txtContrasena, gbc);
        gbc.gridy = 6;
        tarjetaBlanca.add(chkGuardar, gbc);
        gbc.gridy = 7;
        gbc.insets = new Insets(15, 0, 10, 0);
        tarjetaBlanca.add(btnEntrar, gbc);
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 15, 0);
        tarjetaBlanca.add(btnRecuperar, gbc);
        gbc.gridy = 9;
        tarjetaBlanca.add(btnRegistro, gbc);
        gbc.gridy = 10;
        gbc.insets = new Insets(10, 0, 0, 0);
        tarjetaBlanca.add(btnRegistroAdmin, gbc);

        pnlCentro.add(tarjetaBlanca);
        add(pnlCentro, BorderLayout.CENTER);
    }
}