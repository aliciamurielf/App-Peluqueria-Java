import javax.swing.*;
import java.awt.*;

public class DialogoErrorLogin extends JDialog {

    public DialogoErrorLogin(JFrame parent) {
        super(parent, true); 
        setUndecorated(true); 
        setSize(280, 320);
        setLocationRelativeTo(parent); 

        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.setBackground(Color.WHITE);
        pnlPrincipal.setBorder(BorderFactory.createLineBorder(new Color(224, 204, 58), 5, true));
        
        JPanel pnlCabecera = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCabecera.setBackground(new Color(224, 204, 58)); 
        
        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("error_login.titulo"));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        
        pnlCabecera.add(lblTitulo);
        pnlPrincipal.add(pnlCabecera, BorderLayout.NORTH);     
        
        JPanel pnlCuerpo = new JPanel(new GridBagLayout());
        pnlCuerpo.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblTexto1 = new JLabel("<html>" + GestorIdiomas.getTexto("error_login.texto1") + "</html>");
        lblTexto1.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel lblTexto2 = new JLabel("<html>" + GestorIdiomas.getTexto("error_login.texto2") + "</html>");
        lblTexto2.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton btnReintentar = new JButton(GestorIdiomas.getTexto("error_login.reintentar"));
        btnReintentar.setBackground(new Color(10, 0, 60)); 
        btnReintentar.setForeground(Color.WHITE);
        
        JButton btnOlvide = new JButton(GestorIdiomas.getTexto("error_login.olvide"));
        btnOlvide.setBackground(new Color(10, 0, 60)); 
        btnOlvide.setForeground(Color.WHITE);

        gbc.gridy = 0; pnlCuerpo.add(lblTexto1, gbc);
        gbc.gridy = 1; pnlCuerpo.add(lblTexto2, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(20, 15, 5, 15); pnlCuerpo.add(btnReintentar, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(5, 15, 10, 15); pnlCuerpo.add(btnOlvide, gbc);

        pnlPrincipal.add(pnlCuerpo, BorderLayout.CENTER);
        
        add(pnlPrincipal);

        
        
        
        btnReintentar.addActionListener(e -> {
            dispose();
        });

        btnOlvide.addActionListener(e -> {
            dispose(); 
            VentanaPrincipal principal = (VentanaPrincipal) parent;
            principal.cambiarVista(new PanelRecuperar(principal));
        });
    }
}