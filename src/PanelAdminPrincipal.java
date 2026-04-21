import javax.swing.*;
import java.awt.*;

public class PanelAdminPrincipal extends JPanel {

    private VentanaPrincipal ventanaPrincipal;
    private JPanel pnlContenidoCentral; // Aquí inyectaremos las distintas pantallas

    public PanelAdminPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); // Fondo verde menta

        // ---------------------------------------------------------
        // 1. CABECERA (Fija arriba)
        // ---------------------------------------------------------
        JPanel pnlCabecera = crearCabecera();
        add(pnlCabecera, BorderLayout.NORTH);

        // ---------------------------------------------------------
        // 2. CONTENEDOR CENTRAL (Dinámico)
        // ---------------------------------------------------------
        pnlContenidoCentral = new JPanel(new BorderLayout());
        pnlContenidoCentral.setOpaque(false);
        add(pnlContenidoCentral, BorderLayout.CENTER);

        // ---------------------------------------------------------
        // 3. BARRA DE NAVEGACIÓN (Fija abajo)
        // ---------------------------------------------------------
        JPanel pnlNavegacion = crearBarraNavegacion();
        add(pnlNavegacion, BorderLayout.SOUTH);

        // Por defecto, al entrar, cargamos la vista de "Inicio"
        cambiarVistaInterna(new PanelAdminInicio());
    }

    // --- MÉTODO PARA CAMBIAR SOLO EL CENTRO ---
    public void cambiarVistaInterna(JPanel nuevaVista) {
        pnlContenidoCentral.removeAll(); // Quitamos lo que haya
        pnlContenidoCentral.add(nuevaVista, BorderLayout.CENTER); // Añadimos la nueva
        pnlContenidoCentral.revalidate(); // Refrescamos
        pnlContenidoCentral.repaint();
    }

    // --- MÉTODOS AUXILIARES DE DISEÑO ---
    private JPanel crearCabecera() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(10, 0, 60)); 
        panel.setPreferredSize(new Dimension(350, 100)); // Un poco más fina que en el login
        
        JLabel lblTitulo = new JLabel("Laura Estilistas");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(lblTitulo);
        return panel;
    }

    private JPanel crearBarraNavegacion() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(350, 50));

        // 1. Creamos los objetos de los botones
        JButton btnInicio = new JButton();
        JButton btnAgenda = new JButton();
        JButton btnClientes = new JButton();
        JButton btnInventario = new JButton();
        JButton btnSalir = new JButton();

        // 2. Los metemos en un array para aplicarles el diseño (iconos)
        JButton[] botones = {btnInicio, btnAgenda, btnClientes, btnInventario, btnSalir};
        String[] nombresArchivos = {"nav_inicio.png", "nav_agenda.png", "nav_clientes.png", "nav_inventario.png", "nav_salir.png"};

        for (int i = 0; i < botones.length; i++) {
            botones[i].setBackground(Color.WHITE);
            botones[i].setFocusPainted(false);
            botones[i].setBorderPainted(false);
            
            // Intento de carga de imagen (Híbrido para VS Code)
            java.io.File archivo = new java.io.File("src/images/" + nombresArchivos[i]);
            if (archivo.exists()) {
                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                botones[i].setIcon(new ImageIcon(img));
            } else {
                botones[i].setText("?"); 
            }
            panel.add(botones[i]);
        }

        // ---------------------------------------------------------
        // 3. EVENTOS DE NAVEGACIÓN (Asignación directa por variable)
        // ---------------------------------------------------------
        
        // Evento INICIO
        btnInicio.addActionListener(e -> {
            System.out.println("Cargando Inicio...");
            cambiarVistaInterna(new PanelAdminInicio());
        });

        // Evento AGENDA (El que nos falla)
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

        // Evento SALIR (El que sí te funciona)
        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });

        return panel;
    }
}