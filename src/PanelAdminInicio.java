import javax.swing.*;
import java.awt.*;

public class PanelAdminInicio extends JPanel {

    public PanelAdminInicio() {
        setOpaque(false); // Transparente para que se vea el verde de PanelAdminPrincipal
        setLayout(new GridBagLayout());

        // TARJETA BLANCA
        JPanel tarjetaBlanca = new JPanel(new BorderLayout(0, 15));
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjetaBlanca.setPreferredSize(new Dimension(300, 400));

        // Título de la tarjeta
        JLabel lblTitulo = new JLabel("INICIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        // Centro: Lista de citas
        JPanel pnlCitas = new JPanel();
        pnlCitas.setLayout(new BoxLayout(pnlCitas, BoxLayout.Y_AXIS));
        pnlCitas.setBackground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Citas pendientes hoy:");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 12));
        pnlCitas.add(lblSubtitulo);
        pnlCitas.add(Box.createVerticalStrut(10)); // Espaciador

        // Simulamos datos de citas (luego lo leeríamos del txt)
        String[] citas = {"10:00 - Ana García", "11:30 - Juan Pérez", "12:15 - María López"};
        for (String cita : citas) {
            JLabel lblCita = new JLabel("• " + cita);
            lblCita.setFont(new Font("Arial", Font.PLAIN, 12));
            pnlCitas.add(lblCita);
            pnlCitas.add(Box.createVerticalStrut(5));
        }
        
        tarjetaBlanca.add(pnlCitas, BorderLayout.CENTER);

        // Botón Modificar abajo
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBackground(new Color(10, 0, 60));
        btnModificar.setForeground(Color.WHITE);
        tarjetaBlanca.add(btnModificar, BorderLayout.SOUTH);

        add(tarjetaBlanca); // Añadimos la tarjeta al panel principal
    }
}