import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoErrorLogin extends JDialog {

    public DialogoErrorLogin(JFrame parent) {
        super(parent, true); // El 'true' la hace modal (bloquea el fondo)
        setUndecorated(true); // Quitamos la barra superior típica de Windows/Mac para que sea 100% personalizada
        setSize(280, 320);
        setLocationRelativeTo(parent); // Centrar respecto a la ventana principal

        // Panel principal que contendrá todo (usamos BorderLayout)
        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.setBackground(Color.WHITE);
        // Le ponemos un borde amarillo grueso simulando tu diseño
        pnlPrincipal.setBorder(new LineBorder(new Color(224, 204, 58), 5, true));

        // ---------------------------------------------------------
        // 1. CABECERA AMARILLA
        // ---------------------------------------------------------
        JPanel pnlCabecera = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCabecera.setBackground(new Color(224, 204, 58)); // Amarillo mostaza
        
        JLabel lblIcono = new JLabel(" ⚠️ "); // Aquí podrías poner un ImageIcon si tienes el SVG de advertencia
        JLabel lblTitulo = new JLabel("Credenciales incorrectas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        
        pnlCabecera.add(lblIcono);
        pnlCabecera.add(lblTitulo);
        pnlPrincipal.add(pnlCabecera, BorderLayout.NORTH);

        // ---------------------------------------------------------
        // 2. CUERPO DE TEXTO Y BOTONES
        // ---------------------------------------------------------
        JPanel pnlCuerpo = new JPanel(new GridBagLayout());
        pnlCuerpo.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Textos (usamos HTML básico para los saltos de línea automáticos en Swing)
        JLabel lblTexto1 = new JLabel("<html>El usuario o la contraseña no<br>coinciden.</html>");
        lblTexto1.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblTexto2 = new JLabel("<html>Por favor, revisa tus datos e<br>inténtalo de nuevo.</html>");
        lblTexto2.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton btnReintentar = new JButton("Reintentar");
        btnReintentar.setBackground(new Color(10, 0, 60)); // Azul oscuro
        btnReintentar.setForeground(Color.WHITE);
        
        JButton btnOlvide = new JButton("Olvidé mi contraseña");
        btnOlvide.setBackground(new Color(10, 0, 60)); 
        btnOlvide.setForeground(Color.WHITE);

        // Añadimos los elementos al cuerpo
        gbc.gridy = 0; pnlCuerpo.add(lblTexto1, gbc);
        gbc.gridy = 1; pnlCuerpo.add(lblTexto2, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(20, 15, 5, 15); pnlCuerpo.add(btnReintentar, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(5, 15, 10, 15); pnlCuerpo.add(btnOlvide, gbc);

        pnlPrincipal.add(pnlCuerpo, BorderLayout.CENTER);
        
        add(pnlPrincipal);

        // ---------------------------------------------------------
        // 3. EVENTOS DE LOS BOTONES
        // ---------------------------------------------------------
        btnReintentar.addActionListener(e -> {
            dispose(); // Cierra esta ventanita y permite al usuario volver a teclear
        });

        btnOlvide.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Cerramos la ventanita amarilla de error
                dispose(); 
                
                // 2. Recuperamos la ventana principal y le decimos que cambie la vista
                VentanaPrincipal principal = (VentanaPrincipal) parent;
                principal.cambiarVista(new PanelRecuperar(principal));
            }
        });
    }
}