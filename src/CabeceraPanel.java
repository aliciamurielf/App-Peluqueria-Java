import javax.swing.*;
import java.awt.*;

public class CabeceraPanel extends JPanel {

    public CabeceraPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(10, 0, 60));
        setPreferredSize(new Dimension(0, 120));

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);

        JLabel lblLogo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon icon = (ImageIcon) getIcon();
                if (icon != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 55, 55));
                    g2.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                    g2.dispose();
                }
            }
        };
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLogo.setPreferredSize(new Dimension(60, 60));
        lblLogo.setMaximumSize(new Dimension(60, 60));

        try {
            ImageIcon iconLogo = new ImageIcon("src/images/logo.jpg");
            if (iconLogo.getIconWidth() > 0) {
                Image imgLogo = iconLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgLogo));
            }
        } catch (Exception e) {}

        JLabel lblTitulo = new JLabel("Laura Estilistas");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalGlue());
        center.add(lblLogo);
        center.add(Box.createVerticalStrut(5));
        center.add(lblTitulo);
        center.add(Box.createVerticalGlue());

        add(center, BorderLayout.CENTER);
    }
}
