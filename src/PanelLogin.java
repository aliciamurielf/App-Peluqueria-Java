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
        setBackground(new Color(168, 222, 206)); // Fondo verde

        // ---------------------------------------------------------
        // 1. CABECERA AZUL OSCURO (Con imágenes)
        // ---------------------------------------------------------
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
        } catch (Exception e) {
        }
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
        } catch (Exception e) {
        }
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

        // ---------------------------------------------------------
        // 2. CONTENEDOR CENTRAL Y TARJETA BLANCA
        // ---------------------------------------------------------
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false);

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Componentes
        JLabel lblTitulo = new JLabel("Bienvenid@", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(10, 0, 60)); // Azul oscuro

        JLabel lblSubtitulo = new JLabel("Inicia sesión para continuar", SwingConstants.CENTER);
        lblSubtitulo.setForeground(Color.GRAY);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField txtUsuario = new JTextField(15);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 12));
        JPasswordField txtContrasena = new JPasswordField(15);

        JCheckBox chkGuardar = new JCheckBox("Guardar contraseña");
        chkGuardar.setBackground(Color.WHITE);
        chkGuardar.setFont(new Font("Arial", Font.PLAIN, 11));

        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setBackground(new Color(10, 0, 60));
        btnEntrar.setForeground(Color.WHITE);

        JButton btnRecuperar = new JButton("He olvidado mi contraseña");
        btnRecuperar.setBackground(new Color(30, 20, 80)); // Azul ligeramente distinto como en tu diseño
        btnRecuperar.setForeground(Color.WHITE);

        // En lugar de JLabel usamos un JButton
        JButton btnRegistro = new JButton("¿No tienes cuenta? Regístrate aquí");
        btnRegistro.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRegistro.setForeground(Color.BLACK); // O el color que prefieras
        btnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Esto sí lo habéis dado (AppPeluqueriaFinal.java)

        // Lo "camuflamos" para que parezca puramente texto
        btnRegistro.setBorderPainted(false);
        btnRegistro.setContentAreaFilled(false);
        btnRegistro.setFocusPainted(false);

        // Y usamos el ActionListener estándar que SÍ habéis dado
        btnRegistro.addActionListener(e -> {
            ventanaPrincipal.cambiarVista(new PanelRegistro(ventanaPrincipal));
        });

        // TAREA 6: Botón para el Registro de Administrador
        JButton btnRegistroAdmin = new JButton("Solicitar cuenta Admin");
        btnRegistroAdmin.setFont(new Font("Arial", Font.PLAIN, 10));
        btnRegistroAdmin.setForeground(Color.GRAY);
        btnRegistroAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistroAdmin.setBorderPainted(false);
        btnRegistroAdmin.setContentAreaFilled(false);
        btnRegistroAdmin.setFocusPainted(false);
        
        btnRegistroAdmin.addActionListener(e -> {
            ventanaPrincipal.cambiarVista(new PanelSolicitudAdmin(ventanaPrincipal));
        });

        // Añadir a la tarjeta
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

        // ---------------------------------------------------------
        // EVENTO DEL BOTÓN
        // ---------------------------------------------------------
        // Dentro de PanelLogin.java, en el evento del botón entrar:

        btnEntrar.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtContrasena.getPassword());

            // Aquí llamas a tu método de validar (ej: gestor.validarUsuario(user, pass))
            String rol = gestorUsuarios.obtenerRol(user, pass);

            if (rol != null) {
                if (rol.equalsIgnoreCase("admin")) {
                    ventanaPrincipal.cambiarVista(new PanelAdminPrincipal(ventanaPrincipal));
                } else if (rol.equalsIgnoreCase("cliente")) {
                    JOptionPane.showMessageDialog(this, "¡Bienvenido Cliente!");

                    // --- AQUÍ PONES LAS LÍNEAS NUEVAS ---
                    ventanaPrincipal.setUsuarioLogueado(user); // Guardamos quién entró
                    ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal)); // Saltamos al menú
                }
            } else {
                // Aquí es donde saltaría tu diálogo de "Credenciales incorrectas"
                new DialogoErrorLogin(ventanaPrincipal).setVisible(true);
            }
        });

        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Usamos el método de la ventana principal para cargar la nueva vista
                ventanaPrincipal.cambiarVista(new PanelRecuperar(ventanaPrincipal));
            }
        });
    }
}