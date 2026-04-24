import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ModificarLabelListener implements ActionListener{
    private JLabel etiqueta;
    private JTextField campoTexto;

    public ModificarLabelListener(JLabel etiqueta, JTextField campoTexto) {
        this.etiqueta = etiqueta;
        this.campoTexto = campoTexto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = campoTexto.getText();
        etiqueta.setText(texto);
    }
}