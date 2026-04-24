import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelAdminClientes extends JPanel {

    private PanelAdminPrincipal pnlPrincipalAdmin;

    public PanelAdminClientes(PanelAdminPrincipal pnlPrincipalAdmin) {
        this.pnlPrincipalAdmin = pnlPrincipalAdmin;
        setOpaque(false);
        setLayout(new BorderLayout());

        // --- TARJETA BLANCA (FONDO RECTANGULAR) ---
        JPanel tarjetaBlanca = new JPanel(new BorderLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        // Dejamos un margen exterior para que el fondo verde se vea alrededor
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("CLIENTES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60)); // Azul oscuro
        lblTitulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        // --- CUADRÍCULA DE CLIENTES (GRIDLAYOUT) ---
        // Similamos los 8-9 avatares que tienes en el diseño
        JPanel gridClientes = new JPanel(new GridLayout(0, 3, 15, 15)); // Filas variables, 3 columnas. Separación 15px
        gridClientes.setBackground(Color.WHITE);

        // Nombres aleatorios de ejemplo
        String[] nombres = {"María G.", "Laura Pérez", "Ana T.", "Lucía Z.", "Elena R.", "Antonio V.", "Clara F.", "Sofía L."};

        for (String nombre : nombres) {
            gridClientes.add(crearAvatarCliente(nombre));
        }

        // Lo envolvemos en un Scroll por si hay muchos clientes
        JScrollPane scroll = new JScrollPane(gridClientes);
        scroll.setBorder(null); // Quitar el borde del scroll para limpieza visual
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        tarjetaBlanca.add(scroll, BorderLayout.CENTER);

        // --- CENTRAR LA TARJETA BLANCA EN LA PANTALLA ---
        JPanel wrapperCentro = new JPanel(new GridBagLayout());
        wrapperCentro.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 15, 10, 15);
        
        wrapperCentro.add(tarjetaBlanca, gbc);
        add(wrapperCentro, BorderLayout.CENTER);
    }

    // --- SUBRUTINAS PARA CREAR CADA TARJETA DE CLIENTE ---
    private JPanel crearAvatarCliente(String nombre) {
        // En lugar de ser solo un JPanel, lo hacemos usando concepto de "Botón gigante" transparente
        // Esto evita tener que usar MouseAdapter (que no dieron en clase) y sigue usando ActionListener.
        JPanel pnlWrapper = new JPanel(new BorderLayout());
        pnlWrapper.setBackground(Color.WHITE);
        pnlWrapper.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        JButton btnAvatar = new JButton();
        btnAvatar.setLayout(new BorderLayout());
        btnAvatar.setContentAreaFilled(false);
        btnAvatar.setFocusPainted(false);
        btnAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Imagen de la persona (icono de chica genérico o fallback a emoji)
        JLabel lblImg = new JLabel("👤", SwingConstants.CENTER); // Emoji genérico grandote
        lblImg.setFont(new Font("Arial", Font.PLAIN, 40));
        
        // Texto
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 10));
        lblNombre.setForeground(Color.BLACK);
        lblNombre.setBorder(new EmptyBorder(5, 0, 5, 0)); // Espaciado arriba y abajo

        // Intentar sobreescribir el emoji si hay imágenes locales disponibles
        try {
            ImageIcon iconImg = new ImageIcon("src/images/avatar_chica.png");
            if (iconImg.getIconWidth() > 0) {
                Image imgResized = iconImg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                lblImg.setIcon(new ImageIcon(imgResized));
                lblImg.setText(""); 
            }
        } catch (Exception e) {}

        btnAvatar.add(lblImg, BorderLayout.CENTER);
        btnAvatar.add(lblNombre, BorderLayout.SOUTH);

        // EVENTO CLICK: Ir a la ficha del cliente (TAREA 4)
        btnAvatar.addActionListener(e -> {
            if (pnlPrincipalAdmin != null) {
                pnlPrincipalAdmin.cambiarVistaInterna(new PanelAdminFichaCliente(nombre, pnlPrincipalAdmin));
            }
        });

        pnlWrapper.add(btnAvatar, BorderLayout.CENTER);
        return pnlWrapper;
    }
}
