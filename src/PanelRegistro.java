import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelRegistro extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelRegistro(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 

        add(new CabeceraPanel(), BorderLayout.NORTH);
        
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false); 

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.weightx = 1.0;
 
        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("registro.titulo"), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(10, 0, 60));

        Font fontLabels = new Font("Arial", Font.BOLD, 11);
        Color colorAzulOscuro = new Color(10, 0, 60);

        JLabel lblNombre = new JLabel(GestorIdiomas.getTexto("registro.nombre")); lblNombre.setFont(fontLabels); lblNombre.setForeground(colorAzulOscuro);
        JTextField txtNombre = new JTextField(15);
        
        JLabel lblApellidos = new JLabel(GestorIdiomas.getTexto("registro.apellidos")); lblApellidos.setFont(fontLabels); lblApellidos.setForeground(colorAzulOscuro);
        JTextField txtApellidos = new JTextField(15);
        
        JLabel lblTelefono = new JLabel(GestorIdiomas.getTexto("registro.telefono")); lblTelefono.setFont(fontLabels); lblTelefono.setForeground(colorAzulOscuro);
        JTextField txtTelefono = new JTextField(15);
        
        JLabel lblPass = new JLabel(GestorIdiomas.getTexto("registro.contrasena")); lblPass.setFont(fontLabels); lblPass.setForeground(colorAzulOscuro);
        JPasswordField txtPass = new JPasswordField(15);
        JLabel lblSubPass = new JLabel(GestorIdiomas.getTexto("registro.sub_contrasena")); lblSubPass.setFont(new Font("Arial", Font.PLAIN, 9)); lblSubPass.setForeground(Color.GRAY);
        
        JLabel lblConfPass = new JLabel(GestorIdiomas.getTexto("registro.confirmar")); lblConfPass.setFont(fontLabels); lblConfPass.setForeground(colorAzulOscuro);
        JPasswordField txtConfPass = new JPasswordField(15);

        JButton btnRegistrar = new JButton(GestorIdiomas.getTexto("registro.boton"));
        btnRegistrar.setBackground(new Color(10, 0, 60)); btnRegistrar.setForeground(Color.WHITE);

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

        JButton btnVolver = new JButton(GestorIdiomas.getTexto("registro.volver"));
        btnVolver.setBackground(Color.WHITE); btnVolver.setForeground(new Color(10, 0, 60));
        JPanel pnlSur = new JPanel(); pnlSur.setOpaque(false); pnlSur.add(btnVolver);
        add(pnlSur, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal)));

        btnRegistrar.addActionListener(e -> {
            
            txtTelefono.setBorder(UIManager.getBorder("TextField.border"));
            txtPass.setBorder(UIManager.getBorder("TextField.border"));
            txtConfPass.setBorder(UIManager.getBorder("TextField.border"));

            String tel = txtTelefono.getText().trim();
            String p1 = new String(txtPass.getPassword());
            String p2 = new String(txtConfPass.getPassword());

            if(txtNombre.getText().isEmpty() || txtApellidos.getText().isEmpty() || tel.isEmpty() || p1.isEmpty()) {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("registro.error_campos"), GestorIdiomas.getTexto("registro.error_campos_titulo"), JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(!tel.matches("\\d{9}")) {
                txtTelefono.setBorder(new LineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("registro.error_telefono"), GestorIdiomas.getTexto("registro.error"), JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(p1.length() < 8) {
                txtPass.setBorder(new LineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("registro.error_corta"), GestorIdiomas.getTexto("registro.error"), JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(!p1.equals(p2)) {
                txtConfPass.setBorder(new LineBorder(Color.RED, 1));
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("registro.error_coincide"), GestorIdiomas.getTexto("registro.error"), JOptionPane.ERROR_MESSAGE);
                return;
            }

            GestorUsuarios gestor = new GestorUsuarios();
            boolean exito = gestor.registrarUsuario(tel, p1, txtNombre.getText().trim(), txtApellidos.getText().trim());

            if(exito) {
                
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("registro.exito"), GestorIdiomas.getTexto("registro.exito_titulo"), JOptionPane.INFORMATION_MESSAGE);
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            } else {
                
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("registro.existe"), GestorIdiomas.getTexto("registro.existe_titulo"), JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}