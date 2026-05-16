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
        setSize(250, 300);
        setLocationRelativeTo(parent);
        setResizable(false);
        setUndecorated(true);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.gridx = 0;

        txtNombre = new JTextField(GestorIdiomas.getTexto("cita.nombre"));
        txtNombre.setForeground(Color.GRAY);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        txtNombre.setPreferredSize(new Dimension(220, 28));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtNombre.getText().equals(GestorIdiomas.getTexto("cita.nombre"))) {
                    txtNombre.setText("");
                    txtNombre.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtNombre.getText().trim().isEmpty()) {
                    txtNombre.setForeground(Color.GRAY);
                    txtNombre.setText(GestorIdiomas.getTexto("cita.nombre"));
                }
            }
        });

        String[] servicios = { GestorIdiomas.getTexto("cita.corte"), 
                               GestorIdiomas.getTexto("cita.tinte"), 
                               GestorIdiomas.getTexto("cita.peinado") };
        comboServicio = new JComboBox<>(servicios);
        comboServicio.setBackground(new Color(245, 245, 245));
        comboServicio.setPreferredSize(new Dimension(220, 28));
        comboServicio.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        
        btnFecha = new JButton("\uD83D\uDCC5 " + GestorIdiomas.getTexto("cita.fecha"));
        btnFecha.setBackground(Color.WHITE);
        btnFecha.setFocusPainted(false);
        btnFecha.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btnFecha.setPreferredSize(new Dimension(220, 28));
        btnFecha.addActionListener(e -> {
            String[] dias = new String[31];
            for (int i = 0; i < 31; i++)
                dias[i] = String.valueOf(i + 1);
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

            int result = JOptionPane.showConfirmDialog(this, pnlFechaConf, GestorIdiomas.getTexto("cita.seleccionar_dia_mes"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                btnFecha.setText(comboDias.getSelectedItem() + " " + GestorIdiomas.getTexto("cita.de") + " " + comboMeses.getSelectedItem());
            }
        });
 
        btnHora = new JButton("\u23F0 " + GestorIdiomas.getTexto("cita.hora"));
        btnHora.setBackground(Color.WHITE);
        btnHora.setFocusPainted(false);
        btnHora.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btnHora.setPreferredSize(new Dimension(220, 28));
        btnHora.addActionListener(e -> {
            String[] opciones = { "10:00", "12:30", "17:00", "18:00" };
            String seleccion = (String) JOptionPane.showInputDialog(this,
                    GestorIdiomas.getTexto("cita.hora_seleccionar"), GestorIdiomas.getTexto("cita.hora"),
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            if (seleccion != null)
                btnHora.setText("\u23F0 " + seleccion);
        });

        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotones.setBackground(Color.WHITE);

        JButton btnOk = new JButton(GestorIdiomas.getTexto("cita.ok"));
        btnOk.setBackground(new Color(0, 128, 0));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);
        btnOk.setPreferredSize(new Dimension(100, 30));

        JButton btnCancelar = new JButton(GestorIdiomas.getTexto("cita.cancelar"));
        btnCancelar.setBackground(new Color(255, 230, 100));
        btnCancelar.setForeground(new Color(10, 0, 60));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(100, 30));

        btnOk.addActionListener(e -> {
            String nombreTexto = txtNombre.getText().trim();
            if (nombreTexto.isEmpty() || nombreTexto.equals(GestorIdiomas.getTexto("cita.nombre"))) {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("cita.error_nombre"));
                return;
            }
            if (comboServicio.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("cita.error_servicio"));
                return;
            }
            
            this.nombreCliente = nombreTexto;

            String fechaExtr = btnFecha.getText();
            String horaExtr = btnHora.getText();
            this.horaCita = (fechaExtr.equals(GestorIdiomas.getTexto("cita.fecha")) ? GestorIdiomas.getTexto("cita.hoy") : fechaExtr) + " - "
                    + (horaExtr.equals(GestorIdiomas.getTexto("cita.hora")) ? "00:00" : horaExtr);

            this.confirmado = true;
            dispose();
        });

        btnCancelar.addActionListener(e -> {
            this.confirmado = false;
            dispose();
        });

        pnlBotones.add(btnOk);
        pnlBotones.add(btnCancelar);

        
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
