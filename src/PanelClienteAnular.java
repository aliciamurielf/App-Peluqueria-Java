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

        // Cabecera refinada (estilo SI): logo centrado arriba y título debajo
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