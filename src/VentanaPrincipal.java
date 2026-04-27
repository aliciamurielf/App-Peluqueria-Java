import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private String usuarioLogueado;

    public void setUsuarioLogueado(String nombre) {
        this.usuarioLogueado = nombre;
    }

    public String getUsuarioLogueado() {
        return this.usuarioLogueado;
    }

    public VentanaPrincipal() {
        super("Laura Estilistas - App"); 

        setSize(350, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); 
        setLocationRelativeTo(null); 

        PanelLogin panelLogin = new PanelLogin(this);
        setContentPane(panelLogin);
    }

    public void cambiarVista(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate(); 
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            
        }
        
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}