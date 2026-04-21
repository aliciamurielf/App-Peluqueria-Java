import javax.swing.*;
import java.awt.*;

public class PanelAdminAgenda extends JPanel {

    public PanelAdminAgenda() {
        setOpaque(false); // Transparente para ver el fondo verde del contenedor
        setLayout(new GridBagLayout()); // Para centrar la tarjeta blanca

        // --- TARJETA BLANCA ---
        JPanel tarjetaBlanca = new JPanel(new BorderLayout(0, 15));
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjetaBlanca.setPreferredSize(new Dimension(310, 450)); // Altura adaptada

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("AGENDA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        // --- ZONA CENTRAL (Lista de Reservas) ---
        JPanel pnlCentro = new JPanel(new BorderLayout(0, 10));
        pnlCentro.setBackground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Todas las reservas:");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 12));
        pnlCentro.add(lblSubtitulo, BorderLayout.NORTH);

        // Simulamos las reservas (En una versión final leerían del txt)
        String[] reservas = {
            "09:00 - María López",
            "10:00 - Ana García",
            "11:30 - Juan Pérez",
            "12:15 - Carlos Ruiz",
            "16:00 - Elena Gómez",
            "17:30 - Laura Martínez",
            "18:15 - Sofía Herrera"
        };
        
        // Usamos JList para que el admin pueda hacer clic y seleccionar una cita
        JList<String> listaReservas = new JList<>(reservas);
        listaReservas.setFont(new Font("Arial", Font.PLAIN, 12));
        listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Lo metemos en un JScrollPane por si hay muchas citas
        JScrollPane scrollPane = new JScrollPane(listaReservas);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        pnlCentro.add(scrollPane, BorderLayout.CENTER);
        tarjetaBlanca.add(pnlCentro, BorderLayout.CENTER);

        // --- BOTONES INFERIORES ---
        // GridLayout de 1 fila y 2 columnas, con 10px de separación entre botones
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0)); 
        pnlBotones.setBackground(Color.WHITE);
        
        JButton btnAnadir = new JButton("+ Añadir cita");
        btnAnadir.setBackground(new Color(10, 0, 60));
        btnAnadir.setForeground(Color.WHITE);
        
        JButton btnAnular = new JButton("- Anular cita");
        btnAnular.setBackground(Color.WHITE);
        btnAnular.setForeground(Color.RED); // Texto rojo como en tu Figma
        btnAnular.setBorder(BorderFactory.createLineBorder(Color.RED)); // Borde rojo

        // Evento temporal para probar que la lista funciona
        btnAnular.addActionListener(e -> {
            String seleccion = listaReservas.getSelectedValue();
            if (seleccion != null) {
                JOptionPane.showMessageDialog(this, "Se anularía la cita: " + seleccion);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una cita de la lista primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        pnlBotones.add(btnAnadir);
        pnlBotones.add(btnAnular);
        tarjetaBlanca.add(pnlBotones, BorderLayout.SOUTH);

        add(tarjetaBlanca); // Añadir tarjeta al panel general
    }
}