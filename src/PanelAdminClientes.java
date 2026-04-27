import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelAdminClientes extends JPanel {

    private PanelAdminPrincipal pnlPrincipalAdmin;

    public PanelAdminClientes(PanelAdminPrincipal pnlPrincipalAdmin) {
        this.pnlPrincipalAdmin = pnlPrincipalAdmin;
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel tarjetaBlanca = new JPanel(new BorderLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("clientes.titulo"), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60)); 
        lblTitulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        JPanel gridClientes = new JPanel(new GridLayout(0, 3, 15, 15)); 
        gridClientes.setBackground(Color.WHITE);

        String[] nombres = {"María G.", "Laura Pérez", "Ana T.", "Lucía Z.", "Elena R.", "Antonio V.", "Clara F.", "Sofía L."};

        for (String nombre : nombres) {
            gridClientes.add(crearAvatarCliente(nombre));
        }

        JScrollPane scroll = new JScrollPane(gridClientes);
        scroll.setBorder(null); 
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        tarjetaBlanca.add(scroll, BorderLayout.CENTER);

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

    private JPanel crearAvatarCliente(String nombre) {
        JPanel pnlWrapper = new JPanel(new BorderLayout());
        pnlWrapper.setBackground(Color.WHITE);
        pnlWrapper.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        JButton btnAvatar = new JButton();
        btnAvatar.setLayout(new BorderLayout());
        btnAvatar.setContentAreaFilled(false);
        btnAvatar.setFocusPainted(false);
        btnAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        JLabel lblImg = new JLabel("", SwingConstants.CENTER); 
        lblImg.setFont(new Font("Arial", Font.PLAIN, 40));
        
        
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 10));
        lblNombre.setForeground(Color.BLACK);
        lblNombre.setBorder(new EmptyBorder(5, 0, 5, 0)); 

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

        
        btnAvatar.addActionListener(e -> {
            if (pnlPrincipalAdmin != null) {
                pnlPrincipalAdmin.cambiarVistaInterna(new PanelAdminFichaCliente(nombre, pnlPrincipalAdmin));
            }
        });

        pnlWrapper.add(btnAvatar, BorderLayout.CENTER);
        return pnlWrapper;
    }
}
