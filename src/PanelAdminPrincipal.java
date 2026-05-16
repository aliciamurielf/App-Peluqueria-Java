import javax.swing.*;
import java.awt.*;

public class PanelAdminPrincipal extends JPanel {

    private VentanaPrincipal ventanaPrincipal;
    private JPanel pnlContenidoCentral; 

    public PanelAdminPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 
  
        // Cabecera refinada (estilo SI): logo centrado arriba y título debajo
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(10, 0, 60));
        header.setPreferredSize(new Dimension(0, 120));

        JPanel center = new JPanel(new GridLayout(2, 1));
        center.setOpaque(false);
        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                JLabel lblLogo = new JLabel(new ImageIcon(imgLogo), SwingConstants.CENTER);
                lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
                center.add(lblLogo);
            }
        } catch (Exception e) {}
        JLabel lblTituloCab = new JLabel("Laura Estilistas", SwingConstants.CENTER);
        lblTituloCab.setForeground(Color.WHITE);
        lblTituloCab.setFont(new Font("Arial", Font.BOLD, 22));
        center.add(lblTituloCab);
        header.add(center, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
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

        
        JButton btnInicio = new JButton(GestorIdiomas.getTexto("nav.inicio"));
        JButton btnAgenda = new JButton(GestorIdiomas.getTexto("nav.agenda"));
        JButton btnClientes = new JButton(GestorIdiomas.getTexto("nav.clientes"));
        JButton btnInventario = new JButton(GestorIdiomas.getTexto("nav.inventario"));
        JButton btnSalir = new JButton(GestorIdiomas.getTexto("nav.salir"));

        
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

        btnInicio.addActionListener(e -> {
            System.out.println("Cargando Inicio...");
            cambiarVistaInterna(new PanelAdminInicio());
        });
        
        btnAgenda.addActionListener(e -> {
            System.out.println("Intentando abrir PanelAdminAgenda...");
            try {
                PanelAdminAgenda vistaAgenda = new PanelAdminAgenda();
                cambiarVistaInterna(vistaAgenda);
                System.out.println("¡Agenda cargada!");
            } catch (Exception ex) {
                System.err.println("Error al instanciar PanelAdminAgenda: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        
        btnClientes.addActionListener(e -> {
            System.out.println("Cargando Panel de Clientes...");
            cambiarVistaInterna(new PanelAdminClientes(this));
        });

        btnInventario.addActionListener(e -> {
            System.out.println("Cargando Panel de Inventario...");
            cambiarVistaInterna(new PanelAdminInventario(ventanaPrincipal));
        });

        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, GestorIdiomas.getTexto("nav.confirmar_salir"), GestorIdiomas.getTexto("nav.salir"), JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });
        return panel;
    }
}