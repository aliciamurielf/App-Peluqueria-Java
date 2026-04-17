import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelLogin(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        // Usamos GridBagLayout para estructurar el formulario central [cite: 341]
        setLayout(new GridBagLayout());
        setBackground(new Color(168, 222, 206)); // Color de fondo verde menta
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre elementos [cite: 307-308]
        gbc.fill = GridBagConstraints.HORIZONTAL; // Que ocupen el ancho [cite: 301-303]

        // --- COMPONENTES --- [cite: 144-156]
        JLabel lblTitulo = new JLabel("Bienvenid@", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        
        JLabel lblSubtitulo = new JLabel("Inicia sesión para continuar", SwingConstants.CENTER);
        
        JLabel lblUsuario = new JLabel("Usuario");
        JTextField txtUsuario = new JTextField(15); 
        
        JLabel lblContrasena = new JLabel("Contraseña");
        JPasswordField txtContrasena = new JPasswordField(15); 
        
        JCheckBox chkGuardar = new JCheckBox("Guardar contraseña"); 
        chkGuardar.setOpaque(false); // Fondo transparente
        
        JButton btnEntrar = new JButton("ENTRAR"); 
        btnEntrar.setBackground(new Color(10, 0, 60)); // Azul oscuro
        btnEntrar.setForeground(Color.WHITE);
        
        JButton btnRecuperar = new JButton("He olvidado mi contraseña");
        
        JLabel lblRegistro = new JLabel("¿No tienes cuenta? Regístrate aquí", SwingConstants.CENTER);

        // --- COLOCACIÓN EN EL GRIDBAGLAYOUT --- [cite: 288-295]
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        add(lblTitulo, gbc);

        gbc.gridy = 1;
        add(lblSubtitulo, gbc);

        gbc.gridy = 2; gbc.insets = new Insets(20, 10, 2, 10);
        add(lblUsuario, gbc);

        gbc.gridy = 3; gbc.insets = new Insets(2, 10, 10, 10);
        add(txtUsuario, gbc);

        gbc.gridy = 4;
        add(lblContrasena, gbc);

        gbc.gridy = 5;
        add(txtContrasena, gbc);

        gbc.gridy = 6;
        add(chkGuardar, gbc);

        gbc.gridy = 7; gbc.insets = new Insets(20, 10, 5, 10);
        add(btnEntrar, gbc);

        gbc.gridy = 8; gbc.insets = new Insets(5, 10, 20, 10);
        add(btnRecuperar, gbc);

        gbc.gridy = 9;
        add(lblRegistro, gbc);

        // --- EVENTOS (Manejo de la lógica de roles) --- 
        // Dentro del constructor de PanelLogin, donde está btnEntrar...
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUsuario.getText();
                String pass = new String(txtContrasena.getPassword());

                // 1. Llamamos a la lógica del fichero
                GestorUsuarios gestor = new GestorUsuarios();
                String rol = gestor.validarUsuario(user, pass);

                // 2. Decidimos qué panel mostrar basándonos en el rol devuelto
                if (rol != null) {
                    if (rol.equalsIgnoreCase("admin")) {
                        // Cambiamos a la vista de Administrador [cite: 584-588]
                        ventanaPrincipal.cambiarVista(new PanelAdmin(ventanaPrincipal));
                    } else {
                        // Por ahora solo mostramos un mensaje para el cliente [cite: 501-508]
                        JOptionPane.showMessageDialog(null, "¡Bienvenido Cliente!");
                        // Cuando tengas el PanelCliente hecho, será: 
                        // ventanaPrincipal.cambiarVista(new PanelCliente(ventanaPrincipal));
                    }
                } else {
                    // Si el rol es null, las credenciales no existen en el .txt
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", 
                                                "Error", JOptionPane.ERROR_MESSAGE); 
                }
            }
        });
    }
}