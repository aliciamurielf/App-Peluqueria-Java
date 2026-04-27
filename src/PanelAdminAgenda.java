import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelAdminAgenda extends JPanel {

    private DefaultListModel<String> modeloLista;
    private JList<String> listaReservas;
    private GestorCitas gestor = new GestorCitas();

    public PanelAdminAgenda() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        JPanel tarjetaBlanca = new JPanel(new BorderLayout(0, 15));
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjetaBlanca.setPreferredSize(new Dimension(310, 450));

        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("agenda.titulo"), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel(new BorderLayout(0, 10));
        pnlCentro.setBackground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel(GestorIdiomas.getTexto("agenda.subtitulo"));
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 12));
        pnlCentro.add(lblSubtitulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        actualizarListaDesdeArchivo(); 
        
        listaReservas = new JList<>(modeloLista);
        listaReservas.setFont(new Font("Arial", Font.PLAIN, 12));
        listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(listaReservas);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        pnlCentro.add(scrollPane, BorderLayout.CENTER);
        tarjetaBlanca.add(pnlCentro, BorderLayout.CENTER);

        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0)); 
        pnlBotones.setBackground(Color.WHITE);
        
        JButton btnAnadir = new JButton(GestorIdiomas.getTexto("agenda.anadir"));
        btnAnadir.setBackground(new Color(10, 0, 60));
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.setFocusPainted(false);
        
        JButton btnAnular = new JButton(GestorIdiomas.getTexto("agenda.anular"));
        btnAnular.setBackground(Color.WHITE);
        btnAnular.setForeground(Color.RED);
        btnAnular.setBorder(BorderFactory.createLineBorder(Color.RED));
        btnAnular.setFocusPainted(false);

        btnAnadir.addActionListener(e -> {
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            DialogoAnadirCita dialogo = new DialogoAnadirCita(parentWindow);
            dialogo.setVisible(true); 
            
            if (dialogo.isConfirmado()) {
                gestor.guardarCita(dialogo.getHoraCita(), dialogo.getNombreCliente());
                actualizarListaDesdeArchivo();
            }
        });

        btnAnular.addActionListener(e -> {
            String seleccionado = listaReservas.getSelectedValue();
            if (seleccionado != null) {
                String mensajeConfirmar = GestorIdiomas.getTexto("agenda.confirmar_anular").replace("{0}", seleccionado);
                int confirm = JOptionPane.showConfirmDialog(this, 
                    mensajeConfirmar, 
                    GestorIdiomas.getTexto("agenda.confirmar_titulo"), JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    gestor.eliminarCita(seleccionado);    
                    actualizarListaDesdeArchivo();    
                }
            } else {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("agenda.seleccionar"), GestorIdiomas.getTexto("agenda.aviso"), JOptionPane.WARNING_MESSAGE);
            }
        });

        pnlBotones.add(btnAnadir);
        pnlBotones.add(btnAnular);
        tarjetaBlanca.add(pnlBotones, BorderLayout.SOUTH);

        add(tarjetaBlanca);
    }

    private void actualizarListaDesdeArchivo() {
        modeloLista.clear();
        List<String> citas = gestor.leerCitas();
        for (String cita : citas) {
            modeloLista.addElement(cita);
        }
    }
}