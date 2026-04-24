import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelSolicitudAdmin extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelSolicitudAdmin(VentanaPrincipal ventana) {
        this.ventanaPrincipal = ventana;
        
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); // Fondo verde agua
        
        // --- CABECERA AZUL OSCURO (Igual que en PanelLogin) ---
        JPanel pnlCabecera = new JPanel(new GridBagLayout());
        pnlCabecera.setBackground(new Color(10, 0, 60)); 
        pnlCabecera.setPreferredSize(new Dimension(350, 140));

        GridBagConstraints gbcC = new GridBagConstraints();
        gbcC.gridx = 0; gbcC.gridy = 0; gbcC.insets = new Insets(15, 0, 5, 0);
        
        JLabel lblLogo = new JLabel("✂️"); 
        lblLogo.setForeground(Color.WHITE);
        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.png");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgLogo));
                lblLogo.setText(""); 
            }
        } catch (Exception e) {}
        pnlCabecera.add(lblLogo, gbcC);

        gbcC.gridy = 1; gbcC.insets = new Insets(0, 0, 15, 0);
        JLabel lblLogoTexto = new JLabel("Laura Estilistas");
        lblLogoTexto.setForeground(Color.WHITE);
        lblLogoTexto.setFont(new Font("Arial", Font.BOLD, 22));
        pnlCabecera.add(lblLogoTexto, gbcC);

        add(pnlCabecera, BorderLayout.NORTH);

        // --- TARJETA BLANCA CENTRAL ---
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false); 

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Títulos y textos
        JLabel lblTitulo = new JLabel("<html><center>Solicitud de acceso<br>de administrador</center></html>", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.BLACK);
        
        JLabel lblSubtitulo = new JLabel("<html><center>Completa el siguiente formulario<br>para solicitar una cuenta de<br>administrador o recuperar tu<br>contraseña. El acceso será<br>validado por el sistema.</center></html>", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 10));
        lblSubtitulo.setForeground(Color.GRAY);

        // Componentes
        JLabel lblNombre = new JLabel("Nombre completo"); lblNombre.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtNombre = crearCajaDeTexto("Nombre completo");
        
        JLabel lblDNI = new JLabel("DNI"); lblDNI.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtDNI = crearCajaDeTexto("DNI");
        
        JLabel lblCorreo = new JLabel("Correo electrónico"); lblCorreo.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtCorreo = crearCajaDeTexto("Correo electrónico");
        
        JLabel lblTelefono = new JLabel("Número de teléfono"); lblTelefono.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtTelefono = crearCajaDeTexto("Número de teléfono");

        JButton btnEnviar = new JButton("Enviar solicitud");
        btnEnviar.setBackground(new Color(10, 0, 60));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFocusPainted(false);

        JButton btnVolver = new JButton("Volver a iniciar sesión");
        btnVolver.setBackground(new Color(40, 40, 80)); 
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);

        // EVENTOS
        btnEnviar.addActionListener(e -> {
            boolean vacio = txtNombre.getText().trim().isEmpty() || txtDNI.getText().trim().isEmpty() ||
                            txtCorreo.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() ||
                            txtNombre.getText().equals("Nombre completo");
            
            if (vacio) {
                // Tu alert genérico amarillo de la app (lo estilizamos rapidillo simulando figma)
                UIManager.put("OptionPane.background", new Color(255, 230, 100));
                UIManager.put("Panel.background", new Color(255, 230, 100));
                JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos del formulario.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
                UIManager.put("OptionPane.background", null);
                UIManager.put("Panel.background", null);
            } else {
                mostrarPopUpExito();
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });

        btnVolver.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));

        // Montaje en tarjeta (Añadimos con espaciados)
        gbc.gridy = 0; tarjetaBlanca.add(lblTitulo, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(10, 0, 15, 0); tarjetaBlanca.add(lblSubtitulo, gbc);
        
        gbc.insets = new Insets(0, 0, 3, 0);
        gbc.gridy = 2; tarjetaBlanca.add(lblNombre, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(txtNombre, gbc);
        
        gbc.insets = new Insets(0, 0, 3, 0);
        gbc.gridy = 4; tarjetaBlanca.add(lblDNI, gbc);
        gbc.gridy = 5; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(txtDNI, gbc);
        
        gbc.insets = new Insets(0, 0, 3, 0);
        gbc.gridy = 6; tarjetaBlanca.add(lblCorreo, gbc);
        gbc.gridy = 7; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(txtCorreo, gbc);
        
        gbc.insets = new Insets(0, 0, 3, 0);
        gbc.gridy = 8; tarjetaBlanca.add(lblTelefono, gbc);
        gbc.gridy = 9; gbc.insets = new Insets(0, 0, 15, 0); tarjetaBlanca.add(txtTelefono, gbc);
        
        gbc.gridy = 10; gbc.insets = new Insets(10, 0, 5, 0); tarjetaBlanca.add(btnEnviar, gbc);
        gbc.gridy = 11; tarjetaBlanca.add(btnVolver, gbc);

        pnlCentro.add(tarjetaBlanca);
        add(pnlCentro, BorderLayout.CENTER);
    }

    private JTextField crearCajaDeTexto(String placeholder) {
        JTextField txt = new JTextField(placeholder);
        txt.setForeground(Color.GRAY);
        txt.setFont(new Font("Arial", Font.PLAIN, 12));
        txt.setPreferredSize(new Dimension(220, 30));
        
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txt.getText().equals(placeholder)) {
                    txt.setText("");
                    txt.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txt.getText().isEmpty()) {
                    txt.setForeground(Color.GRAY);
                    txt.setText(placeholder);
                }
            }
        });
        return txt;
    }

    private void mostrarPopUpExito() {
        // Reproducir el Pop-up Verde de "Solicitud Realizada" del Figma
        UIManager.put("OptionPane.background", new Color(30, 130, 76)); // Verde fuerte
        UIManager.put("Panel.background", new Color(30, 130, 76));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        
        JOptionPane.showMessageDialog(
            this, 
            "<html><div style='text-align: center; color: white;'><b>Solicitud realizada</b><br><br>Hemos recibido tu solicitud. Te<br>contactaremos en breve.</div></html>", 
            "Éxito", 
            JOptionPane.PLAIN_MESSAGE
        );
        
        // Limpiamos la configuración gráfica para no ensuciar el resto de la aplicación
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageForeground", null);
    }
}
