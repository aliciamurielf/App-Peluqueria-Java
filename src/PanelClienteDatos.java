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

        add(new CabeceraPanel(), BorderLayout.NORTH);

        JPanel pnlForm = new JPanel(new GridLayout(5, 1, 10, 10));
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField(GestorIdiomas.getTexto("cita.nombre"));
        String[] servicios = {GestorIdiomas.getTexto("cita.corte"), GestorIdiomas.getTexto("cita.peinado"), GestorIdiomas.getTexto("cita.tinte")};
        JComboBox<String> cbServicios = new JComboBox<>(servicios);
        JButton btnFecha = new JButton(GestorIdiomas.getTexto("cita.fecha"));
        JButton btnHora = new JButton(GestorIdiomas.getTexto("cita.hora"));

        JPanel pnlAcciones = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton btnOk = new JButton(GestorIdiomas.getTexto("cita.ok"));
        btnOk.setBackground(new Color(0, 128, 0)); 
        btnOk.setForeground(Color.WHITE);
        
        JButton btnCancelar = new JButton(GestorIdiomas.getTexto("cita.cancelar"));
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
 
        btnHora.addActionListener(e -> {
            String[] opciones = {"10:00", "12:30", "17:00", "18:00"};
            String seleccion = (String) JOptionPane.showInputDialog(this, GestorIdiomas.getTexto("cita.hora_seleccionar"), GestorIdiomas.getTexto("cita.hora"), JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            if (seleccion != null) {
                horaSeleccionada = seleccion;
                btnHora.setText(horaSeleccionada);
            }
        });

        btnFecha.addActionListener(e -> {
            String[] dias = new String[31]; 
            for(int i=0; i<31; i++) dias[i] = String.valueOf(i+1);
            JComboBox<String> comboDias = new JComboBox<>(dias);
            
            String[] meses = {
                GestorIdiomas.getTexto("cita.enero"), GestorIdiomas.getTexto("cita.febrero"),
                GestorIdiomas.getTexto("cita.marzo"), GestorIdiomas.getTexto("cita.abril"),
                GestorIdiomas.getTexto("cita.mayo"), GestorIdiomas.getTexto("cita.junio"),
                GestorIdiomas.getTexto("cita.julio"), GestorIdiomas.getTexto("cita.agosto"),
                GestorIdiomas.getTexto("cita.septiembre"), GestorIdiomas.getTexto("cita.octubre"),
                GestorIdiomas.getTexto("cita.noviembre"), GestorIdiomas.getTexto("cita.diciembre")
            };
            JComboBox<String> comboMeses = new JComboBox<>(meses);
            
            JPanel pnlFechaConf = new JPanel(new GridLayout(1, 2, 5, 0));
            pnlFechaConf.add(comboDias);
            pnlFechaConf.add(comboMeses);
            
            int result = JOptionPane.showConfirmDialog(this, pnlFechaConf, GestorIdiomas.getTexto("cita.seleccionar_dia_mes"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                fechaSeleccionada = comboDias.getSelectedItem() + " " + GestorIdiomas.getTexto("cita.de") + " " + comboMeses.getSelectedItem();
                btnFecha.setText(fechaSeleccionada);
            }
        });

        btnOk.addActionListener(e -> {
            if (fechaSeleccionada.isEmpty() || horaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("cita.elegir_fecha_hora"));
                return;
            }

            GestorCitas gestor = new GestorCitas();
            String datosCita = fechaSeleccionada + " a las " + horaSeleccionada;
            
            
            gestor.guardarCita(datosCita, txtNombre.getText());
            
            String mensaje = GestorIdiomas.getTexto("cita.reservada").replace("{0}", datosCita);
            JOptionPane.showMessageDialog(this, mensaje);
            ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal));
        });

        btnCancelar.addActionListener(e -> ventanaPrincipal.cambiarVista(new PanelClienteMenu(ventanaPrincipal)));
    }

}