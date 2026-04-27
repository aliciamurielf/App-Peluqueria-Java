import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelClienteAnular extends JPanel {
    private VentanaPrincipal vp;
    private DefaultListModel<String> modelo;
    private JList<String> lista;
    private GestorCitas gestor = new GestorCitas();

    public PanelClienteAnular(VentanaPrincipal vp) {
        this.vp = vp;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 

        JPanel pnlCabecera = new JPanel(new GridBagLayout());
        pnlCabecera.setBackground(new Color(10, 0, 60)); 
        pnlCabecera.setPreferredSize(new Dimension(350, 140));

        GridBagConstraints gbcCabecera = new GridBagConstraints();

        gbcCabecera.gridx = 0; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 2;
        gbcCabecera.weightx = 0.33;
        gbcCabecera.anchor = GridBagConstraints.NORTHWEST;
        gbcCabecera.insets = new Insets(15, 15, 0, 0);
        
        JButton btnIconoIdioma = new JButton();
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
                btnIconoIdioma.setText("");
            }
        } catch (Exception e) {}
        btnIconoIdioma.addActionListener(e -> {
            GestorIdiomas.cambiarIdiomaBase();
            vp.cambiarVista(new PanelClienteAnular(vp));
        });
        pnlCabecera.add(btnIconoIdioma, gbcCabecera);

        gbcCabecera.gridx = 1; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 1; 
        gbcCabecera.weightx = 0.33;
        gbcCabecera.anchor = GridBagConstraints.CENTER;
        gbcCabecera.insets = new Insets(15, 0, 5, 0);
        
        JLabel lblLogo = new JLabel();
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

        gbcCabecera.gridy = 1;
        gbcCabecera.insets = new Insets(0, 0, 15, 0);
        JLabel lblLogoTexto = new JLabel("Laura Estilistas");
        lblLogoTexto.setForeground(Color.WHITE);
        lblLogoTexto.setFont(new Font("Arial", Font.BOLD, 22));
        pnlCabecera.add(lblLogoTexto, gbcCabecera);

        gbcCabecera.gridx = 2; 
        gbcCabecera.gridy = 0;
        gbcCabecera.weightx = 0.33;
        pnlCabecera.add(new JLabel(" "), gbcCabecera);

        add(pnlCabecera, BorderLayout.NORTH);

        modelo = new DefaultListModel<>();
        actualizarListaFiltrada();
        
        lista = new JList<>(modelo);
        lista.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder(GestorIdiomas.getTexto("anular.titulo")));
        
        add(scroll, BorderLayout.CENTER);

        JPanel pnlBotones = new JPanel(new FlowLayout());
        pnlBotones.setOpaque(false);

        JButton btnAnular = new JButton(GestorIdiomas.getTexto("anular.boton"));
        btnAnular.setBackground(Color.RED);
        btnAnular.setForeground(Color.WHITE);

        JButton btnVolver = new JButton(GestorIdiomas.getTexto("anular.volver"));

        pnlBotones.add(btnAnular);
        pnlBotones.add(btnVolver);
        add(pnlBotones, BorderLayout.SOUTH);

        
        btnAnular.addActionListener(e -> {
            String seleccion = lista.getSelectedValue();
            if (seleccion != null) {
                int confirm = JOptionPane.showConfirmDialog(this, GestorIdiomas.getTexto("anular.confirmar"));
                if (confirm == JOptionPane.YES_OPTION) {
                    gestor.eliminarCita(seleccion);
                    actualizarListaFiltrada(); 
                }
            } else {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("anular.seleccionar"));
            }
        });

        btnVolver.addActionListener(e -> vp.cambiarVista(new PanelClienteMenu(vp)));
    }

    private void actualizarListaFiltrada() {
        modelo.clear();
        String usuarioActual = vp.getUsuarioLogueado();       
        List<String> todas = gestor.leerCitas();
        for (String cita : todas) {
            if (cita.contains(usuarioActual)) {
                modelo.addElement(cita);
            }
        }
    }
}