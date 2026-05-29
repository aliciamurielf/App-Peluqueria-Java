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

        // Cabecera
        CabeceraPanel cabecera = new CabeceraPanel();
        add(cabecera, BorderLayout.NORTH);

        //lista de citas
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