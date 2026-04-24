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
        // Quitamos la barra de título por defecto para un pop-up más moderno tipo app móvil
        setUndecorated(true); 

        // Panel principal con borde redondeado (simulado) y fondo blanco
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(168, 222, 206), 3)); // Borde verde simulando el fondo de la app

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.gridx = 0;

        // Campo Nombre
        txtNombre = new JTextField("Nombre y Apellido / Apodo");
        txtNombre.setForeground(Color.GRAY);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtNombre.getText().equals("Nombre y Apellido / Apodo")) {
                    txtNombre.setText("");
                    txtNombre.setForeground(Color.BLACK);
                }
            }
        });

        // Combo Servicio
        String[] servicios = {"Seleccione el servicio \u25BC", "Corte", "Tinte", "Peinado"};
        comboServicio = new JComboBox<>(servicios);
        comboServicio.setBackground(new Color(230, 230, 230)); 

        // Botón Fecha
        btnFecha = new JButton("📅 Fecha");
        btnFecha.setBackground(Color.WHITE);
        btnFecha.setFocusPainted(false);
        btnFecha.addActionListener(e -> {
            // Utilizamos JComboBox para días y meses, tal y como se vio en AppPeluqueriaFinal.java
            String[] dias = new String[31]; 
            for(int i=0; i<31; i++) dias[i] = String.valueOf(i+1);
            JComboBox<String> comboDias = new JComboBox<>(dias);
            
            String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            JComboBox<String> comboMeses = new JComboBox<>(meses);
            
            JPanel pnlFechaConf = new JPanel(new GridLayout(1, 2, 5, 0));
            pnlFechaConf.add(comboDias);
            pnlFechaConf.add(comboMeses);
            
            int result = JOptionPane.showConfirmDialog(this, pnlFechaConf, "Seleccione día y mes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                btnFecha.setText("📅 " + comboDias.getSelectedItem() + " de " + comboMeses.getSelectedItem());
            }
        });

        // Botón Hora
        btnHora = new JButton("⏰ Hora");
        btnHora.setBackground(Color.WHITE);
        btnHora.setFocusPainted(false);
        btnHora.addActionListener(e -> {
            String[] opciones = {"10:00", "12:30", "17:00", "18:00"};
            String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona la hora:", "Hora", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            if(seleccion != null) btnHora.setText("⏰ " + seleccion);
        });

        // Botones Inferiores (OK / Cancelar)
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotones.setBackground(Color.WHITE);

        JButton btnOk = new JButton("OK");
        btnOk.setBackground(new Color(0, 128, 0)); // Verde oscuro Figma
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 230, 100)); // Amarillo Figma
        btnCancelar.setForeground(new Color(10, 0, 60)); // Azul oscuro
        btnCancelar.setFocusPainted(false);

        btnOk.addActionListener(e -> {
            if (txtNombre.getText().trim().isEmpty() || txtNombre.getText().equals("Nombre y Apellido / Apodo")) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce el nombre.");
                return;
            }
            if (comboServicio.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un servicio.");
                return;
            }
            // Asignamos las variables finales
            this.nombreCliente = txtNombre.getText().trim();
            
            String fechaExtr = btnFecha.getText().replace("📅 ", "");
            String horaExtr = btnHora.getText().replace("⏰ ", "");
            this.horaCita = (fechaExtr.equals("Fecha") ? "Hoy" : fechaExtr) + " - " + (horaExtr.equals("Hora") ? "00:00" : horaExtr);
            
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
        gbc.gridy = 0; gbc.insets = new Insets(20, 15, 10, 15); panelPrincipal.add(txtNombre, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(5, 15, 10, 15); panelPrincipal.add(comboServicio, gbc);
        gbc.gridy = 2; panelPrincipal.add(btnFecha, gbc);
        gbc.gridy = 3; panelPrincipal.add(btnHora, gbc);
        gbc.gridy = 4; gbc.insets = new Insets(20, 15, 20, 15); panelPrincipal.add(pnlBotones, gbc);

        add(panelPrincipal);
    }

    public boolean isConfirmado() { return confirmado; }
    public String getNombreCliente() { return nombreCliente; }
    public String getHoraCita() { return horaCita; }
}
