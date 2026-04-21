import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRecuperar extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelRecuperar(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        setLayout(new BorderLayout()); //[cite: 2]
        setBackground(new Color(168, 222, 206)); // Fondo verde menta

        // ---------------------------------------------------------
        // 1. CABECERA AZUL OSCURO (Idéntica al Login por consistencia)
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
        // 2. CONTENEDOR CENTRAL Y TARJETA BLANCA
        // ---------------------------------------------------------
        JPanel pnlCentro = new JPanel(new GridBagLayout()); //[cite: 2]
        pnlCentro.setOpaque(false); 

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        GridBagConstraints gbc = new GridBagConstraints(); //[cite: 2]
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // --- COMPONENTES DE LA TARJETA ---
        JLabel lblTitulo = new JLabel("Recuperar contraseña", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(10, 0, 60)); 
        
        // Uso de HTML para el salto de línea en el texto explicativo
        JLabel lblExplicacion = new JLabel("<html><div style='text-align: center; color: gray;'>Ingresa el número de teléfono<br>asociado a tu cuenta y te<br>enviaremos un enlace para<br>restablecer tu contraseña</div></html>", SwingConstants.CENTER);
        lblExplicacion.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JTextField txtTelefono = new JTextField(15); //[cite: 2]
        // *Nota: Java Swing no tiene "placeholders" nativos de forma sencilla, 
        // así que lo dejamos vacío o le ponemos un ToolTipText.
        txtTelefono.setToolTipText("Introduce tu número de teléfono");
        
        JButton btnRecuperar = new JButton("Recuperar contraseña"); //[cite: 2]
        btnRecuperar.setBackground(new Color(10, 0, 60)); 
        btnRecuperar.setForeground(Color.WHITE);
        
        JButton btnVolver = new JButton("Volver a iniciar sesión");
        btnVolver.setBackground(new Color(30, 20, 80)); 
        btnVolver.setForeground(Color.WHITE);

        // --- AÑADIR A LA TARJETA ---
        gbc.gridy = 0; tarjetaBlanca.add(lblTitulo, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(15, 0, 20, 0); tarjetaBlanca.add(lblExplicacion, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 25, 0); tarjetaBlanca.add(txtTelefono, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(btnRecuperar, gbc);
        gbc.gridy = 4; gbc.insets = new Insets(0, 0, 0, 0); tarjetaBlanca.add(btnVolver, gbc);

        pnlCentro.add(tarjetaBlanca);
        add(pnlCentro, BorderLayout.CENTER); //[cite: 2]

        // ---------------------------------------------------------
        // 3. EVENTOS DE LOS BOTONES
        // ---------------------------------------------------------
        
        btnRecuperar.addActionListener(new ActionListener() { //[cite: 3]
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtTelefono.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(PanelRecuperar.this, "Por favor, introduce un teléfono válido.", "Aviso", JOptionPane.WARNING_MESSAGE); //[cite: 3]
                } else {
                    JOptionPane.showMessageDialog(PanelRecuperar.this, "Enlace de recuperación enviado al " + txtTelefono.getText(), "Éxito", JOptionPane.INFORMATION_MESSAGE); //[cite: 3]
                    // Automáticamente devolvemos al usuario al login tras el éxito
                    ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)); //[cite: 4]
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Manejo de vistas: Volvemos al panel de Login[cite: 4]
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)); //[cite: 4]
            }
        });
    }
}