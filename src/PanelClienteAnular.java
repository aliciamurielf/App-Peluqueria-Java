import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PanelClienteAnular extends JPanel {
    private VentanaPrincipal vp;
    private DefaultListModel<String> modelo;
    private JList<String> lista;
    private GestorCitas gestor = new GestorCitas();

    public PanelClienteAnular(VentanaPrincipal vp) {
        this.vp = vp;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); // Verde menta de tu diseño

        // --- CABECERA ---
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

        // --- CUERPO (El "Cuaderno" de citas) ---
        modelo = new DefaultListModel<>();
        actualizarListaFiltrada();
        
        lista = new JList<>(modelo);
        lista.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder("Selecciona la cita que deseas anular"));
        
        add(scroll, BorderLayout.CENTER);

        // --- BOTONES INFERIORES ---
        JPanel pnlBotones = new JPanel(new FlowLayout());
        pnlBotones.setOpaque(false);

        JButton btnAnular = new JButton("- Anular cita");
        btnAnular.setBackground(Color.RED);
        btnAnular.setForeground(Color.WHITE);

        JButton btnVolver = new JButton("Volver");

        pnlBotones.add(btnAnular);
        pnlBotones.add(btnVolver);
        add(pnlBotones, BorderLayout.SOUTH);

        // --- LÓGICA ---
        btnAnular.addActionListener(e -> {
            String seleccion = lista.getSelectedValue();
            if (seleccion != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres borrarla?");
                if (confirm == JOptionPane.YES_OPTION) {
                    gestor.eliminarCita(seleccion);
                    actualizarListaFiltrada(); // Refresca la pantalla
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una cita primero.");
            }
        });

        btnVolver.addActionListener(e -> vp.cambiarVista(new PanelClienteMenu(vp)));
    }

    private void actualizarListaFiltrada() {
        modelo.clear();
        String usuarioActual = vp.getUsuarioLogueado(); // Sacamos el nombre del cliente
        
        // Leemos todas las citas y solo nos quedamos con las que son de este cliente
        List<String> todas = gestor.leerCitas();
        for (String cita : todas) {
            if (cita.contains(usuarioActual)) {
                modelo.addElement(cita);
            }
        }
    }
}