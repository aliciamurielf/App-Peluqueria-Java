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

        // --- TARJETA BLANCA ---
        JPanel tarjetaBlanca = new JPanel(new BorderLayout(0, 15));
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjetaBlanca.setPreferredSize(new Dimension(310, 450));

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("AGENDA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        // --- ZONA CENTRAL (Lista dinámica) ---
        JPanel pnlCentro = new JPanel(new BorderLayout(0, 10));
        pnlCentro.setBackground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Todas las reservas:");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 12));
        pnlCentro.add(lblSubtitulo, BorderLayout.NORTH);

        // CONFIGURACIÓN DE LA LISTA DINÁMICA
        modeloLista = new DefaultListModel<>();
        actualizarListaDesdeArchivo(); // Cargamos los datos del .txt al arrancar
        
        listaReservas = new JList<>(modeloLista);
        listaReservas.setFont(new Font("Arial", Font.PLAIN, 12));
        listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(listaReservas);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        pnlCentro.add(scrollPane, BorderLayout.CENTER);
        tarjetaBlanca.add(pnlCentro, BorderLayout.CENTER);

        // --- BOTONES INFERIORES ---
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0)); 
        pnlBotones.setBackground(Color.WHITE);
        
        JButton btnAnadir = new JButton("+ Añadir cita");
        btnAnadir.setBackground(new Color(10, 0, 60));
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.setFocusPainted(false);
        
        JButton btnAnular = new JButton("- Anular cita");
        btnAnular.setBackground(Color.WHITE);
        btnAnular.setForeground(Color.RED);
        btnAnular.setBorder(BorderFactory.createLineBorder(Color.RED));
        btnAnular.setFocusPainted(false);

        // --- LÓGICA DE FUNCIONAMIENTO ---

        // Acción de AÑADIR
        btnAnadir.addActionListener(e -> {
            String hora = JOptionPane.showInputDialog(this, "Introduce la hora (Ej: 17:00):", "Nueva Cita", JOptionPane.QUESTION_MESSAGE);
            if (hora != null && !hora.trim().isEmpty()) {
                String nombre = JOptionPane.showInputDialog(this, "Nombre del cliente:", "Nueva Cita", JOptionPane.QUESTION_MESSAGE);
                if (nombre != null && !nombre.trim().isEmpty()) {
                    gestor.guardarCita(hora, nombre); // Guarda en citas.txt
                    actualizarListaDesdeArchivo();    // Refresca la interfaz
                }
            }
        });

        // Acción de ANULAR
        btnAnular.addActionListener(e -> {
            String seleccionado = listaReservas.getSelectedValue();
            if (seleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de que quieres anular la cita:\n" + seleccionado + "?", 
                    "Confirmar anulación", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    gestor.eliminarCita(seleccionado); // Borra del txt
                    actualizarListaDesdeArchivo();     // Refresca la interfaz
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona una cita de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        pnlBotones.add(btnAnadir);
        pnlBotones.add(btnAnular);
        tarjetaBlanca.add(pnlBotones, BorderLayout.SOUTH);

        add(tarjetaBlanca);
    }

    // Método para refrescar la lista visual con lo que haya en el archivo
    private void actualizarListaDesdeArchivo() {
        modeloLista.clear();
        List<String> citas = gestor.leerCitas();
        for (String cita : citas) {
            modeloLista.addElement(cita);
        }
    }
}