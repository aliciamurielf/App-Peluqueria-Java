import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelAdmin extends JPanel {
    private VentanaPrincipal ventana;

    public PanelAdmin(VentanaPrincipal ventana) {
        this.ventana = ventana;
        setLayout(new BorderLayout());

        // --- CABECERA ---
        JPanel pnlCabecera = new JPanel();
        pnlCabecera.setBackground(new Color(10, 0, 60)); // Azul oscuro del logo
        JLabel lblTitulo = new JLabel("Panel de Administración");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        pnlCabecera.add(lblTitulo);
        add(pnlCabecera, BorderLayout.NORTH);

        // --- TABLA DE CITAS (Centro) --- [cite: 71]
        String[] columnas = {"Cliente", "Servicio", "Fecha", "Hora"};
        Object[][] datos = {
            {"Ana García", "Corte y Tinte", "15/04/2026", "10:00"},
            {"Juan Pérez", "Barba", "15/04/2026", "11:30"}
        };
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        JTable tablaCitas = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        add(scrollPane, BorderLayout.CENTER);

        // --- BOTONES DE ACCIÓN (Sur) --- [cite: 190]
        JPanel pnlBotones = new JPanel(new FlowLayout());
        JButton btnNuevaCita = new JButton("Nueva Cita");
        JButton btnEliminar = new JButton("Cancelar Seleccionada");
        JButton btnSalir = new JButton("Cerrar Sesión");

        pnlBotones.add(btnNuevaCita);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnSalir);
        add(pnlBotones, BorderLayout.SOUTH);

        // Evento para volver al login [cite: 584-588]
        btnSalir.addActionListener(e -> ventana.cambiarVista(new PanelLogin(ventana)));
    }
}