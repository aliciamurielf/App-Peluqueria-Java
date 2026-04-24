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

        JPanel pnlCabecera = new JPanel(new GridBagLayout());
        pnlCabecera.setBackground(new Color(10, 0, 60)); // Azul oscuro
        pnlCabecera.setPreferredSize(new Dimension(350, 140));

        GridBagConstraints gbcCabecera = new GridBagConstraints();

        // -- COLUMNA IZQUIERDA (Icono de idioma) --
        gbcCabecera.gridx = 0; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 2; // Ocupa las dos filas de altura
        gbcCabecera.weightx = 0.33; // Ocupa un tercio del espacio horizontal
        gbcCabecera.anchor = GridBagConstraints.NORTHWEST; // Pegado arriba a la izquierda
        gbcCabecera.insets = new Insets(15, 15, 0, 0); // Margen
        
        JLabel lblIconoIdioma = new JLabel("🌐"); // Emoji por si no hay imagen
        lblIconoIdioma.setForeground(Color.WHITE);
        try {
            ImageIcon iconIdioma = new ImageIcon("images/icono_idioma.png");
            if (iconIdioma.getIconWidth() > 0) {
                Image imgIdioma = iconIdioma.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                lblIconoIdioma.setIcon(new ImageIcon(imgIdioma));
                lblIconoIdioma.setText(""); // Borramos el emoji si carga la imagen
            }
        } catch (Exception e) {}
        pnlCabecera.add(lblIconoIdioma, gbcCabecera);

        // -- COLUMNA CENTRAL (Logo y Texto) --
        gbcCabecera.gridx = 1; 
        gbcCabecera.gridy = 0;
        gbcCabecera.gridheight = 1; 
        gbcCabecera.weightx = 0.33; // Ocupa el tercio central
        gbcCabecera.anchor = GridBagConstraints.CENTER;
        gbcCabecera.insets = new Insets(15, 0, 5, 0);
        
        JLabel lblLogo = new JLabel("✂️"); // Emoji por si no hay logo
        lblLogo.setForeground(Color.WHITE);
        try {
            ImageIcon iconLogo = new ImageIcon("images/logo.png");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgLogo));
                lblLogo.setText(""); 
            }
        } catch (Exception e) {}
        pnlCabecera.add(lblLogo, gbcCabecera);

        gbcCabecera.gridy = 1; // Fila de abajo para el título
        gbcCabecera.insets = new Insets(0, 0, 15, 0);
        JLabel lblLogoTexto = new JLabel("Laura Estilistas");
        lblLogoTexto.setForeground(Color.WHITE);
        lblLogoTexto.setFont(new Font("Arial", Font.BOLD, 22));
        pnlCabecera.add(lblLogoTexto, gbcCabecera);

        // -- COLUMNA DERECHA (Fantasma para equilibrar el centro) --
        gbcCabecera.gridx = 2; 
        gbcCabecera.gridy = 0;
        gbcCabecera.weightx = 0.33; // El último tercio vacío
        pnlCabecera.add(new JLabel(" "), gbcCabecera);

        add(pnlCabecera, BorderLayout.NORTH);


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

        // OK action se traslada abajo para evitar duplicidad

        // --- BOTÓN HORA ---
        btnHora.addActionListener(e -> {
            String[] opciones = {"10:00", "12:30", "17:00", "18:00"};
            String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona la hora:", "Hora", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            if (seleccion != null) {
                horaSeleccionada = seleccion;
                btnHora.setText("⏰ " + horaSeleccionada);
            }
        });

        // --- BOTÓN FECHA ---
        btnFecha.addActionListener(e -> {
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
                fechaSeleccionada = comboDias.getSelectedItem() + " de " + comboMeses.getSelectedItem();
                btnFecha.setText("📅 " + fechaSeleccionada);
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