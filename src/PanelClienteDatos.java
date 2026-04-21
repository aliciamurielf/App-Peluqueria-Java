import javax.swing.*;
import java.awt.*;

public class PanelClienteDatos extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    private String fechaSeleccionada = "";
    private String horaSeleccionada = "";

    public PanelClienteDatos(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206));

        add(crearCabecera(), BorderLayout.NORTH);

        // --- Tarjeta de Formulario ---
        JPanel pnlForm = new JPanel(new GridLayout(5, 1, 10, 10));
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField("Nombre y Apellido");
        JComboBox<String> cbServicios = new JComboBox<>(new String[]{"Corte", "Peinado", "Tinte"});
        JButton btnFecha = new JButton("Seleccionar Fecha");
        JButton btnHora = new JButton("Seleccionar Hora");

        JPanel pnlAcciones = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton btnOk = new JButton("OK");
        btnOk.setBackground(new Color(0, 128, 0)); // Verde
        btnOk.setForeground(Color.WHITE);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 230, 150));

        pnlAcciones.add(btnOk);
        pnlAcciones.add(btnCancelar);

        pnlForm.add(txtNombre);
        pnlForm.add(cbServicios);
        pnlForm.add(btnFecha);
        pnlForm.add(btnHora);
        pnlForm.add(pnlAcciones);

        JPanel contenedorCentral = new JPanel(new GridBagLayout());
        contenedorCentral.setOpaque(false);
        contenedorCentral.add(pnlForm);
        add(contenedorCentral, BorderLayout.CENTER);

        // Acción Guardar
        btnOk.addActionListener(e -> {
            GestorCitas gestor = new GestorCitas();
            // Por ahora simplificamos la fecha/hora para la prueba
            gestor.guardarCita("10:30", txtNombre.getText());
            JOptionPane.showMessageDialog(this, "¡Cita reservada con éxito!");
            ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal));
        });

        // --- BOTÓN HORA ---
        btnHora.addActionListener(e -> {
            String[] horasDisponibles = {"09:00", "10:00", "11:00", "12:00", "16:00", "17:00", "18:00"};
            
            String seleccion = (String) JOptionPane.showInputDialog(
                this, 
                "Selecciona una hora:", 
                "Selector de Hora",
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                horasDisponibles, 
                horasDisponibles[0]
            );

            if (seleccion != null) {
                horaSeleccionada = seleccion;
                btnHora.setText("Hora: " + horaSeleccionada); // Para que el cliente vea que se ha guardado
            }
        });

        // --- BOTÓN FECHA ---
        btnFecha.addActionListener(e -> {
            String[] dias = {"Lunes 20", "Martes 21", "Miércoles 22", "Jueves 23", "Viernes 24"};
            
            String seleccion = (String) JOptionPane.showInputDialog(
                this, 
                "Selecciona un día:", 
                "Selector de Fecha",
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                dias, 
                dias[0]
            );

            if (seleccion != null) {
                fechaSeleccionada = seleccion;
                btnFecha.setText("Día: " + fechaSeleccionada);
            }
        });

        btnOk.addActionListener(e -> {
            if (fechaSeleccionada.isEmpty() || horaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, elige fecha y hora.");
                return;
            }

            GestorCitas gestor = new GestorCitas();
            String datosCita = fechaSeleccionada + " a las " + horaSeleccionada;
            
            // Guardamos en el archivo citas.txt
            gestor.guardarCita(datosCita, txtNombre.getText());
            
            JOptionPane.showMessageDialog(this, "¡Cita reservada para el " + datosCita + "!");
            ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal));
        });

        btnCancelar.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal)));
    }

    private JPanel crearCabecera() { /* Igual que el anterior */ return new JPanel(); }
}