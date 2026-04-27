import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoErrorLogin extends JDialog {

    public DialogoErrorLogin(JFrame parent) {
        super(parent, true); 
        setUndecorated(true); 
        setSize(280, 320);
        setLocationRelativeTo(parent); 

        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.setBackground(Color.WHITE);
        pnlPrincipal.setBorder(new LineBorder(new Color(224, 204, 58), 5, true));

        // ---------------------------------------------------------
        // 1. CABECERA AMARILLA
        // ---------------------------------------------------------
        JPanel pnlCabecera = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCabecera.setBackground(new Color(224, 204, 58)); 
        
        JLabel lblIcono = new JLabel(" ⚠️ "); 
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
            dispose();
        });

        btnOlvide.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                VentanaPrincipal principal = (VentanaPrincipal) parent;
                principal.cambiarVista(new PanelRecuperar(principal));
            }
        });
    }
}