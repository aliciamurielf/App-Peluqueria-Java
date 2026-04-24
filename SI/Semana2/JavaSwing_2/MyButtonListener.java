import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Listener para botón
 * Modifica el texto del botón original por "Pulsado!"
 */
public class MyButtonListener implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Con getSource podemos obtener el botón que lanzó el evento
        JButton button = (JButton)e.getSource();
        
        // Le modificamos el contenido
        button.setText("Pulsado!");
    }

}