import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelRegistro extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelRegistro(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); // Fondo verde menta

        // ---------------------------------------------------------
        // 1. CABECERA AZUL (Copiar la misma que usamos en Login)
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

        // ---------------------------------------------------------
        // 2. TARJETA BLANCA DE REGISTRO
        // ---------------------------------------------------------
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false); 

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.weightx = 1.0;

        // --- COMPONENTES ---
        JLabel lblTitulo = new JLabel("Crea tu cuenta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(10, 0, 60));
        
        // Estilo común para etiquetas
        Font fontLabels = new Font("Arial", Font.BOLD, 11);
        Color colorAzulOscuro = new Color(10, 0, 60);

        JLabel lblNombre = new JLabel("Nombre"); lblNombre.setFont(fontLabels); lblNombre.setForeground(colorAzulOscuro);
        JTextField txtNombre = new JTextField(15);
        
        JLabel lblApellidos = new JLabel("Apellidos"); lblApellidos.setFont(fontLabels); lblApellidos.setForeground(colorAzulOscuro);
        JTextField txtApellidos = new JTextField(15);
        
        JLabel lblTelefono = new JLabel("Número de teléfono"); lblTelefono.setFont(fontLabels); lblTelefono.setForeground(colorAzulOscuro);
        JTextField txtTelefono = new JTextField(15);
        
        JLabel lblPass = new JLabel("Contraseña"); lblPass.setFont(fontLabels); lblPass.setForeground(colorAzulOscuro);
        JPasswordField txtPass = new JPasswordField(15);
        JLabel lblSubPass = new JLabel("Al menos 8 caracteres"); lblSubPass.setFont(new Font("Arial", Font.PLAIN, 9)); lblSubPass.setForeground(Color.GRAY);
        
        JLabel lblConfPass = new JLabel("Confirmar Contraseña"); lblConfPass.setFont(fontLabels); lblConfPass.setForeground(colorAzulOscuro);
        JPasswordField txtConfPass = new JPasswordField(15);

        JButton btnRegistrar = new JButton("REGISTRARME");
        btnRegistrar.setBackground(new Color(10, 0, 60)); btnRegistrar.setForeground(Color.WHITE);

        // --- AÑADIR AL GRID ---
        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 15, 0); tarjetaBlanca.add(lblTitulo, gbc);
        
        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 2, 0); tarjetaBlanca.add(lblNombre, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(txtNombre, gbc);
        
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 2, 0); tarjetaBlanca.add(lblApellidos, gbc);
        gbc.gridy = 4; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(txtApellidos, gbc);
        
        gbc.gridy = 5; gbc.insets = new Insets(0, 0, 2, 0); tarjetaBlanca.add(lblTelefono, gbc);
        gbc.gridy = 6; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(txtTelefono, gbc);
        
        gbc.gridy = 7; gbc.insets = new Insets(0, 0, 2, 0); tarjetaBlanca.add(lblPass, gbc);
        gbc.gridy = 8; gbc.insets = new Insets(0, 0, 2, 0); tarjetaBlanca.add(txtPass, gbc);
        gbc.gridy = 9; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(lblSubPass, gbc);
        
        gbc.gridy = 10; gbc.insets = new Insets(0, 0, 2, 0); tarjetaBlanca.add(lblConfPass, gbc);
        gbc.gridy = 11; gbc.insets = new Insets(0, 0, 15, 0); tarjetaBlanca.add(txtConfPass, gbc);
        
        gbc.gridy = 12; gbc.insets = new Insets(5, 0, 5, 0); tarjetaBlanca.add(btnRegistrar, gbc);

        pnlCentro.add(tarjetaBlanca);
        add(pnlCentro, BorderLayout.CENTER);

        // Botón inferior flotante
        JButton btnVolver = new JButton("Volver a inicio");
        btnVolver.setBackground(Color.WHITE); btnVolver.setForeground(new Color(10, 0, 60));
        JPanel pnlSur = new JPanel(); pnlSur.setOpaque(false); pnlSur.add(btnVolver);
        add(pnlSur, BorderLayout.SOUTH);

        // ---------------------------------------------------------
        // 3. EVENTOS Y VALIDACIONES
        // ---------------------------------------------------------
        btnVolver.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));

        btnRegistrar.addActionListener(e -> {
            // Restaurar bordes por si había error anterior
            txtTelefono.setBorder(UIManager.getBorder("TextField.border"));
            txtPass.setBorder(UIManager.getBorder("TextField.border"));
            txtConfPass.setBorder(UIManager.getBorder("TextField.border"));

            String tel = txtTelefono.getText().trim();
            String p1 = new String(txtPass.getPassword());
            String p2 = new String(txtConfPass.getPassword());

            // Validación 1: Campos vacíos
            if(txtNombre.getText().isEmpty() || txtApellidos.getText().isEmpty() || tel.isEmpty() || p1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos obligatorios.", "Campos sin rellenar", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validación 2: Teléfono (asumimos que debe tener 9 dígitos como en España)
            if(!tel.matches("\\d{9}")) {
                txtTelefono.setBorder(new LineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(this, "Número de teléfono inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validación 3: Contraseña corta
            if(p1.length() < 8) {
                txtPass.setBorder(new LineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(this, "La contraseña es demasiado corta. Al menos 8 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validación 4: Coincidencia
            if(!p1.equals(p2)) {
                txtConfPass.setBorder(new LineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // --- SI TODO ESTÁ BIEN, REGISTRAMOS ---
            GestorUsuarios gestor = new GestorUsuarios();
            boolean exito = gestor.registrarUsuario(tel, p1);

            if(exito) {
                // Mensaje verde de éxito
                JOptionPane.showMessageDialog(this, "¡Listo! Cuenta creada con éxito.\nYa puedes iniciar sesión.", "Cuenta creada", JOptionPane.INFORMATION_MESSAGE);
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            } else {
                // Mensaje amarillo de usuario existente
                JOptionPane.showMessageDialog(this, "Este número de teléfono ya está asociado a una cuenta.", "Usuario registrado", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}