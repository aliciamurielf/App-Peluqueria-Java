import javax.swing.*;
import java.awt.*;

public class DialogoAnadirCita extends JDialog {
    private String nombreCliente = "";
    private String horaCita = "";
    private boolean confirmado = false;

    private JTextField txtNombre;
    private JComboBox<String> comboServicio;
    private JButton btnFecha;
    private JButton btnHora;

    public DialogoAnadirCita(Window parent) {
        super(parent, "Laura Estilistas", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(280, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        setUndecorated(true);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(168, 222, 206), 3));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.gridx = 0;

        txtNombre = new JTextField();
        txtNombre.setBorder(BorderFactory.createTitledBorder(GestorIdiomas.getTexto("cita.nombre")));
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        txtNombre.setHorizontalAlignment(JTextField.LEFT);

        // Combo Servicio
        String[] servicios = { GestorIdiomas.getTexto("cita.servicio"), "Corte", "Tinte", "Peinado" };
        comboServicio = new JComboBox<>(servicios);
        comboServicio.setBackground(new Color(230, 230, 230));

        // Botón Fecha
        btnFecha = new JButton("\uD83D\uDCC5 " + GestorIdiomas.getTexto("cita.fecha"));
        btnFecha.setBackground(Color.WHITE);
        btnFecha.setFocusPainted(false);
        btnFecha.addActionListener(e -> {
            String[] dias = new String[31];
            for (int i = 0; i < 31; i++)
                dias[i] = String.valueOf(i + 1);
            JComboBox<String> comboDias = new JComboBox<>(dias);

            String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
                    "Octubre", "Noviembre", "Diciembre" };
            JComboBox<String> comboMeses = new JComboBox<>(meses);

            JPanel pnlFechaConf = new JPanel(new GridLayout(1, 2, 5, 0));
            pnlFechaConf.add(comboDias);
            pnlFechaConf.add(comboMeses);

            int result = JOptionPane.showConfirmDialog(this, pnlFechaConf, "Seleccione día y mes",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                btnFecha.setText("📅 " + comboDias.getSelectedItem() + " de " + comboMeses.getSelectedItem());
            }
        });

        // Botón Hora
        btnHora = new JButton("\u23F0 " + GestorIdiomas.getTexto("cita.hora"));
        btnHora.setBackground(Color.WHITE);
        btnHora.setFocusPainted(false);
        btnHora.addActionListener(e -> {
            String[] opciones = { "10:00", "12:30", "17:00", "18:00" };
            String seleccion = (String) JOptionPane.showInputDialog(this,
                    GestorIdiomas.getTexto("cita.hora_seleccionar"), GestorIdiomas.getTexto("cita.hora"),
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            if (seleccion != null)
                btnHora.setText("\u23F0 " + seleccion);
        });

        // Botones Inferiores (OK / Cancelar)
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotones.setBackground(Color.WHITE);

        JButton btnOk = new JButton("OK");
        btnOk.setBackground(new Color(0, 128, 0));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 230, 100));
        btnCancelar.setForeground(new Color(10, 0, 60));
        btnCancelar.setFocusPainted(false);

        btnOk.addActionListener(e -> {
            if (txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("cita.error_nombre"));
                return;
            }
            if (comboServicio.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("cita.error_servicio"));
                return;
            }
            // Asignamos las variables finales
            this.nombreCliente = txtNombre.getText().trim();

            String fechaExtr = btnFecha.getText().replace("📅 ", "");
            String horaExtr = btnHora.getText().replace("⏰ ", "");
            this.horaCita = (fechaExtr.equals("Fecha") ? "Hoy" : fechaExtr) + " - "
                    + (horaExtr.equals("Hora") ? "00:00" : horaExtr);

            this.confirmado = true;
            dispose();
        });

        btnCancelar.addActionListener(e -> {
            this.confirmado = false;
            dispose();
        });

        pnlBotones.add(btnOk);
        pnlBotones.add(btnCancelar);

        // Añadimos todo al panel
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 15, 10, 15);
        panelPrincipal.add(txtNombre, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 15, 10, 15);
        panelPrincipal.add(comboServicio, gbc);
        gbc.gridy = 2;
        panelPrincipal.add(btnFecha, gbc);
        gbc.gridy = 3;
        panelPrincipal.add(btnHora, gbc);
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 15, 20, 15);
        panelPrincipal.add(pnlBotones, gbc);

        add(panelPrincipal);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getHoraCita() {
        return horaCita;
    }
}
