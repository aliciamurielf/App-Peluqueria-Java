import javax.swing.*;
import java.awt.*;

public class PanelAdminPrincipal extends JPanel {

    private VentanaPrincipal ventanaPrincipal;
    private JPanel pnlContenidoCentral;

    public PanelAdminPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206));

        // CABECERA 
        add(new CabeceraPanel(), BorderLayout.NORTH);

        // CONTENIDO CENTRAL 
        pnlContenidoCentral = new JPanel(new BorderLayout());
        pnlContenidoCentral.setOpaque(false);
        add(pnlContenidoCentral, BorderLayout.CENTER);

        JPanel pnlNavegacion = crearBarraNavegacion();
        add(pnlNavegacion, BorderLayout.SOUTH);
        cambiarVistaInterna(new PanelAdminInicio());
    }

    public void cambiarVistaInterna(JPanel nuevaVista) {
        pnlContenidoCentral.removeAll();
        pnlContenidoCentral.add(nuevaVista, BorderLayout.CENTER);
        pnlContenidoCentral.revalidate();
        pnlContenidoCentral.repaint();
    }

    private JPanel crearBarraNavegacion() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(350, 75));

        JButton btnInicio = new JButton();
        JButton btnAgenda = new JButton();
        JButton btnClientes = new JButton();
        JButton btnInventario = new JButton();
        JButton btnSalir = new JButton();

        JButton[] botones = {btnInicio, btnAgenda, btnClientes, btnInventario, btnSalir};
        String[] nombresArchivos = {"nav_inicio.png", "nav_agenda.png", "nav_clientes.png", "nav_inventario.png", "nav_salir.png"};
        String[] textos = {"nav.inicio", "nav.agenda", "nav.clientes", "nav.inventario", "nav.salir"};

        for (int i = 0; i < botones.length; i++) {
            botones[i].setOpaque(false);
            botones[i].setContentAreaFilled(false);
            botones[i].setFocusPainted(false);
            botones[i].setBorderPainted(false);
            botones[i].setText("");
            botones[i].setToolTipText(GestorIdiomas.getTexto(textos[i]));
            java.io.File archivo = new java.io.File("src/images/" + nombresArchivos[i]);
            if (archivo.exists()) {
                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                botones[i].setIcon(new ImageIcon(img));
            } else {
                botones[i].setText("?");
            }
            panel.add(botones[i]);
        }

        btnInicio.addActionListener(e -> cambiarVistaInterna(new PanelAdminInicio()));

        btnAgenda.addActionListener(e -> {
            try {
                cambiarVistaInterna(new PanelAdminAgenda());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnClientes.addActionListener(e -> cambiarVistaInterna(new PanelAdminClientes(this)));

        btnInventario.addActionListener(e -> cambiarVistaInterna(new PanelAdminInventario(ventanaPrincipal)));

        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    GestorIdiomas.getTexto("nav.confirmar_salir"),
                    GestorIdiomas.getTexto("nav.salir"),
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });

        return panel;
    }
}