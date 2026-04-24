import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelAdminFichaCliente extends JPanel {

    public PanelAdminFichaCliente(String nombreCliente, JPanel pnlPrincipalAdmin) {
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.gridx = 0;

        // TÍTULO
        JLabel lblTitulo = new JLabel("FICHA DEL CLIENTE", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(new Color(10, 0, 60)); 
        gbc.gridy = 0; tarjetaBlanca.add(lblTitulo, gbc);

        // HEADER CLIENTE (Icono + Nombre)
        JPanel pnlHeaderCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlHeaderCliente.setBackground(Color.WHITE);
        
        JLabel lblImg = new JLabel("👤");
        lblImg.setFont(new Font("Arial", Font.PLAIN, 24));
        try {
            // Buscamos si hay un avatar genérico en carpeta images
            ImageIcon iconImg = new ImageIcon("src/images/avatar_chica.png");
            if (iconImg.getIconWidth() > 0) {
                Image imgResized = iconImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                lblImg.setIcon(new ImageIcon(imgResized));
                lblImg.setText(""); 
            }
        } catch (Exception e) {}

        JLabel lblNombre = new JLabel(nombreCliente);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        pnlHeaderCliente.add(lblImg);
        pnlHeaderCliente.add(lblNombre);
        gbc.gridy = 1; tarjetaBlanca.add(pnlHeaderCliente, gbc);

        // AREAS DE TEXTO (Alergias, Historial, Contacto)
        JPanel pnlAlergias = crearCajaEdicion("Alergias");
        JPanel pnlHistorial = crearCajaEdicion("Historial de tintes");
        JPanel pnlContacto = crearCajaEdicion("Datos de contacto");
        
        gbc.gridy = 2; tarjetaBlanca.add(pnlAlergias, gbc);
        gbc.gridy = 3; tarjetaBlanca.add(pnlHistorial, gbc);
        gbc.gridy = 4; tarjetaBlanca.add(pnlContacto, gbc);

        // BOTONES
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotones.setBackground(Color.WHITE);
        pnlBotones.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 128, 0));
        btnGuardar.setForeground(Color.WHITE);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 230, 100)); // Amarillo
        btnCancelar.setForeground(Color.BLACK);

        btnGuardar.addActionListener(e -> {
            // GURDADO REAL EN ARCHIVO .txt (como vimos en GestorUsuarios)
            try {
                java.io.FileWriter fw = new java.io.FileWriter("fichas_clientes.txt", true);
                java.io.PrintWriter out = new java.io.PrintWriter(fw);
                
                // Extraemos el texto de los JTextArea que están dentro del panel
                JTextArea txtAlergias = (JTextArea) pnlAlergias.getComponent(1);
                JTextArea txtHistorial = (JTextArea) pnlHistorial.getComponent(1);
                JTextArea txtContacto = (JTextArea) pnlContacto.getComponent(1);
                
                // Formato: Nombre;Alergias;Historial;Contacto
                out.println(nombreCliente + ";" + txtAlergias.getText().replace("\n", " ") + ";" + 
                            txtHistorial.getText().replace("\n", " ") + ";" + txtContacto.getText().replace("\n", " "));
                out.close();
                
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
                ((PanelAdminPrincipal)pnlPrincipalAdmin).cambiarVistaInterna(new PanelAdminInicio()); 
                
            } catch (Exception ex) {
                mostrarErrorSimulado(tarjetaBlanca); // Si algo de verdad falla, mostramos tu error amarillo Figma
            }
        });

        btnCancelar.addActionListener(e -> {
             // Volver a inicio (Como panel contenedor de "agenda" / vacío) o clientes
             ((PanelAdminPrincipal)pnlPrincipalAdmin).cambiarVistaInterna(new PanelAdminInicio()); 
        });

        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnCancelar);
        gbc.gridy = 5; gbc.insets = new Insets(20, 0, 0, 0); tarjetaBlanca.add(pnlBotones, gbc);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.add(tarjetaBlanca);
        add(wrapper, BorderLayout.CENTER);
    }

    private JPanel crearCajaEdicion(String titulo) {
        JPanel p = new JPanel(new BorderLayout(0, 5));
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Arial", Font.PLAIN, 10));
        JTextArea txtArea = new JTextArea(3, 20);
        txtArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        p.add(lbl, BorderLayout.NORTH);
        p.add(txtArea, BorderLayout.CENTER);
        return p;
    }

    private void mostrarErrorSimulado(Component parent) {
        // Simulando el error amarillo personalizado (JOptionPane) visto en "Error Modificación Figma"
        UIManager.put("OptionPane.background", new Color(255, 230, 100));
        UIManager.put("Panel.background", new Color(255, 230, 100));
        JOptionPane.showMessageDialog(parent, "Error en la modificación.\n\nNo se han podido guardar los cambios.\nInténtelo de nuevo más tarde.", "Error", JOptionPane.WARNING_MESSAGE);
        // Reset colors
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
    }
}
