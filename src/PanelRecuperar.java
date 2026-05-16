import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRecuperar extends JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelRecuperar(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        setLayout(new BorderLayout());
        setBackground(new Color(168, 222, 206)); 

        // Cabecera refinada: logo centrado arriba y título centrado debajo (estilo SI)
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

        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setOpaque(false); 

        JPanel tarjetaBlanca = new JPanel(new GridBagLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        
        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("recuperar.titulo"), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(10, 0, 60)); 
        
        
        JLabel lblExplicacion = new JLabel("<html><div style='text-align: center; color: gray;'>" + GestorIdiomas.getTexto("recuperar.explicacion") + "</div></html>", SwingConstants.CENTER);
        lblExplicacion.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JTextField txtTelefono = new JTextField(15);
        txtTelefono.setToolTipText(GestorIdiomas.getTexto("recuperar.intro_telefono"));
        
        JButton btnRecuperar = new JButton(GestorIdiomas.getTexto("recuperar.boton"));
        btnRecuperar.setBackground(new Color(10, 0, 60)); 
        btnRecuperar.setForeground(Color.WHITE);
        
        JButton btnVolver = new JButton(GestorIdiomas.getTexto("recuperar.volver"));
        btnVolver.setBackground(new Color(30, 20, 80)); 
        btnVolver.setForeground(Color.WHITE);

        gbc.gridy = 0; tarjetaBlanca.add(lblTitulo, gbc);
        gbc.gridy = 1; gbc.insets = new Insets(15, 0, 20, 0); tarjetaBlanca.add(lblExplicacion, gbc);
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 25, 0); tarjetaBlanca.add(txtTelefono, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 10, 0); tarjetaBlanca.add(btnRecuperar, gbc);
        gbc.gridy = 4; gbc.insets = new Insets(0, 0, 0, 0); tarjetaBlanca.add(btnVolver, gbc);

        pnlCentro.add(tarjetaBlanca);
        add(pnlCentro, BorderLayout.CENTER);
 
        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tel = txtTelefono.getText().trim();
                
                if(tel.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelRecuperar.this, GestorIdiomas.getTexto("recuperar.intro_telefono"), GestorIdiomas.getTexto("recuperar.aviso"), JOptionPane.WARNING_MESSAGE);
                    return;
                }

                GestorUsuarios gestor = new GestorUsuarios();
                
                if (gestor.existeUsuario(tel)) {
                    
                    Object[] opcionesExito = {GestorIdiomas.getTexto("recuperar.exito_boton")};
                    JOptionPane.showOptionDialog(PanelRecuperar.this,
                            GestorIdiomas.getTexto("recuperar.exito"),
                            GestorIdiomas.getTexto("recuperar.exito_titulo"),
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null, opcionesExito, opcionesExito[0]);
                    
                    
                    ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
                    
                } else {
                    
                    Object[] opcionesError = {GestorIdiomas.getTexto("recuperar.reintentar"), GestorIdiomas.getTexto("recuperar.crear_cuenta")};
                    int seleccion = JOptionPane.showOptionDialog(PanelRecuperar.this,
                            GestorIdiomas.getTexto("recuperar.error"),
                            GestorIdiomas.getTexto("recuperar.error_titulo"),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null, opcionesError, opcionesError[0]);
                    
                    if (seleccion == 1) { 
                        
                        ventanaPrincipal.cambiarVista(new PanelRegistro(ventanaPrincipal));
                    }
                    
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ventanaPrincipal.cambiarVista(new PanelLogin(ventanaPrincipal));
            }
        });
    }
}