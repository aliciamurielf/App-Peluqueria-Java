import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelSolicitudAdmin extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelSolicitudAdmin(VentanaPrincipal ventana) {
        this.ventanaPrincipal = ventana;
        
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 
 
        // Cabecera refinada: logo centrado arriba y título centrado debajo (estilo SI)
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(10, 0, 60));
        header.setPreferredSize(new Dimension(0, 120));

        JPanel center = new JPanel(new GridLayout(2, 1));
        center.setOpaque(false);
        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                JLabel lblLogo = new JLabel(new ImageIcon(imgLogo), SwingConstants.CENTER);
                lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
                center.add(lblLogo);
            }
        } catch (Exception e) {}

        JLabel lblTituloCab = new JLabel("Laura Estilistas", SwingConstants.CENTER);
        lblTituloCab.setForeground(Color.WHITE);
        lblTituloCab.setFont(new Font("Arial", Font.BOLD, 22));
        center.add(lblTituloCab);

        header.add(center, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false); 

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblTitulo = new JLabel("<html><center>" + GestorIdiomas.getTexto("solicitud.titulo") + "</center></html>", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.BLACK);
        
        JLabel lblSubtitulo = new JLabel("<html><center>" + GestorIdiomas.getTexto("solicitud.subtitulo") + "</center></html>", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 10));
        lblSubtitulo.setForeground(Color.GRAY);

        JLabel lblNombre = new JLabel(GestorIdiomas.getTexto("solicitud.nombre")); lblNombre.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtNombre = crearCajaDeTexto(GestorIdiomas.getTexto("solicitud.nombre"));
        
        JLabel lblDNI = new JLabel(GestorIdiomas.getTexto("solicitud.dni")); lblDNI.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtDNI = crearCajaDeTexto(GestorIdiomas.getTexto("solicitud.dni"));
        
        JLabel lblCorreo = new JLabel(GestorIdiomas.getTexto("solicitud.correo")); lblCorreo.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtCorreo = crearCajaDeTexto(GestorIdiomas.getTexto("solicitud.correo"));
        
        JLabel lblTelefono = new JLabel(GestorIdiomas.getTexto("solicitud.telefono")); lblTelefono.setFont(new Font("Arial", Font.BOLD, 10));
        JTextField txtTelefono = crearCajaDeTexto(GestorIdiomas.getTexto("solicitud.telefono"));

        JButton btnEnviar = new JButton(GestorIdiomas.getTexto("solicitud.enviar"));
        btnEnviar.setBackground(new Color(10, 0, 60));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFocusPainted(false);

        JButton btnVolver = new JButton(GestorIdiomas.getTexto("solicitud.volver"));
        btnVolver.setBackground(new Color(40, 40, 80)); 
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
    
        btnEnviar.addActionListener(e -> {
            boolean vacio = txtNombre.getText().trim().isEmpty() || txtDNI.getText().trim().isEmpty() ||
                            txtCorreo.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() ||
                            txtNombre.getText().equals(GestorIdiomas.getTexto("solicitud.nombre"));
            
            if (vacio) {
                
                UIManager.put("OptionPane.background", new Color(255, 230, 100));
                UIManager.put("Panel.background", new Color(255, 230, 100));
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("solicitud.error_campos"), GestorIdiomas.getTexto("solicitud.error_titulo"), JOptionPane.WARNING_MESSAGE);
                UIManager.put("OptionPane.background", null);
                UIManager.put("Panel.background", null);
            } else {
                mostrarPopUpExito();
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });

        btnVolver.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));

        
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
        JDialog dialog = new JDialog(ventanaPrincipal, GestorIdiomas.getTexto("solicitud.exito_titulo"), true);

        JPanel panelDialog = new JPanel(new GridLayout(3, 1));
        panelDialog.setBackground(new Color(30, 130, 76));

        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("solicitud.exito_titulo"), SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblMensaje = new JLabel(GestorIdiomas.getTexto("solicitud.exito"), SwingConstants.CENTER);
        lblMensaje.setForeground(Color.WHITE);

        JButton btnCerrar = new JButton(GestorIdiomas.getTexto("solicitud.volver"));
        btnCerrar.addActionListener(e -> dialog.setVisible(false));

        panelDialog.add(lblTitulo);
        panelDialog.add(lblMensaje);
        panelDialog.add(btnCerrar);

        dialog.setContentPane(panelDialog);
        dialog.setSize(360, 170);
        dialog.setLocationRelativeTo(ventanaPrincipal);
        dialog.setVisible(true);
    }
}
