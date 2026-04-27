import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRecuperar extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelRecuperar(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); // Fondo verde menta

        // ---------------------------------------------------------
        // 1. CABECERA AZUL OSCURO (Idéntica al Login por consistencia)
        // ---------------------------------------------------------
        JPanel pnlCabecera = new JPanel(new GridBagLayout());
        pnlCabecera.setBackground(new Color(10, 0, 60)); // Azul oscuro
        pnlCabecera.setPreferredSize(new Dimension(350, 140));

        GridBagConstraints gbcCabecera = new GridBagConstraints();

        // -- COLUMNA IZQUIERDA (Icono de idioma MODO BOTÓN) --
        gbcCabecera.gridx = 0; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 2; // Ocupa las dos filas de altura
        gbcCabecera.weightx = 0.33; // Ocupa un tercio del espacio horizontal
        gbcCabecera.anchor = GridBagConstraints.NORTHWEST; // Pegado arriba a la izquierda
        gbcCabecera.insets = new Insets(15, 15, 0, 0); // Margen
        
        JButton btnIconoIdioma = new JButton("🌐"); // Emoji por si no hay imagen
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
                btnIconoIdioma.setText(""); // Borramos el emoji si carga la imagen
            }
        } catch (Exception e) {}

        // Evento que acciona I18n
        btnIconoIdioma.addActionListener(e -> {
            GestorIdiomas.cambiarIdiomaBase();
            ventanaPrincipal.cambiarVista(new PanelRecuperar(ventanaPrincipal));
        });

        pnlCabecera.add(btnIconoIdioma, gbcCabecera);

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
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
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
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false); 

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // --- COMPONENTES DE LA TARJETA (Internacionalizados) ---
        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("recuperar.titulo"), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(10, 0, 60)); 
        
        // Uso de HTML para el salto de línea en el texto explicativo
        JLabel lblExplicacion = new JLabel("<html><div style='text-align: center; color: gray;'>" + GestorIdiomas.getTexto("recuperar.explicacion") + "</div></html>", SwingConstants.CENTER);
        lblExplicacion.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JTextField txtTelefono = new JTextField(15);
        txtTelefono.setToolTipText(GestorIdiomas.getTexto("recuperar.intro_telefono"));
        
        JButton btnRecuperar = new JButton(GestorIdiomas.getTexto("recuperar.boton"));
        btnRecuperar.setBackground(new Color(10, 0, 60)); 
        btnRecuperar.setForeground(Color.WHITE);
        
        JButton btnVolver = new JButton(GestorIdiomas.getTexto("recuperar.volver"));
        btnVolver.setBackground(new Color(30, 20, 80)); 
        btnVolver.setForeground(Color.WHITE);

        // --- AÑADIR A LA TARJETA ---
        gbc.gridy = 0; tarjetaBlanca.add(lblTitulo, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(15, 0, 20, 0); tarjetaBlanca.add(lblExplicacion, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 25, 0); tarjetaBlanca.add(txtTelefono, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(btnRecuperar, gbc);
        gbc.gridy = 4; gbc.insets = new Insets(0, 0, 0, 0); tarjetaBlanca.add(btnVolver, gbc);

        pnlCentro.add(tarjetaBlanca);
        add(pnlCentro, BorderLayout.CENTER);

        // ---------------------------------------------------------
        // 3. EVENTOS DE LOS BOTONES
        // ---------------------------------------------------------
        
        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tel = txtTelefono.getText().trim();
                
                if(tel.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelRecuperar.this, GestorIdiomas.getTexto("recuperar.intro_telefono"), GestorIdiomas.getTexto("recuperar.aviso"), JOptionPane.WARNING_MESSAGE);
                    return;
                }

                GestorUsuarios gestor = new GestorUsuarios();
                
                if (gestor.existeUsuario(tel)) {
                    // FLUJO DE ÉXITO (Cartel Verde en Figma)
                    Object[] opcionesExito = {GestorIdiomas.getTexto("recuperar.exito_boton")};
                    JOptionPane.showOptionDialog(PanelRecuperar.this,
                            GestorIdiomas.getTexto("recuperar.exito"),
                            GestorIdiomas.getTexto("recuperar.exito_titulo"),
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null, opcionesExito, opcionesExito[0]);
                    
                    // Al darle a aceptar, le mandamos al Login
                    ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
                    
                } else {
                    // FLUJO DE ERROR (Cartel Amarillo en Figma)
                    Object[] opcionesError = {GestorIdiomas.getTexto("recuperar.reintentar"), GestorIdiomas.getTexto("recuperar.crear_cuenta")};
                    int seleccion = JOptionPane.showOptionDialog(PanelRecuperar.this,
                            GestorIdiomas.getTexto("recuperar.error"),
                            GestorIdiomas.getTexto("recuperar.error_titulo"),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null, opcionesError, opcionesError[0]);
                    
                    if (seleccion == 1) { 
                        // Si elige la opción 1 ("Crear cuenta"), le mandamos al registro
                        ventanaPrincipal.cambiarVista(new PanelRegistro(ventanaPrincipal));
                    }
                    // Si elige 0 ("Reintentar") o cierra la ventana, se queda en la misma pantalla
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Manejo de vistas: Volvemos al panel de Login
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });
    }
}