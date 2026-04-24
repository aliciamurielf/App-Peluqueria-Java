import javax.swing.*;
import java.awt.*;

/**
 * Ejemplo de inserción de imágenes en Java Swing
 * Imagen como etiqueta, como botón y favicon
 */
public class Example_Image {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Título de ventana");
    
    JPanel jp = new JPanel();
    jp.setLayout(new GridLayout(2, 2));
    
    jp.add(new JLabel("Label"));

    //Cargar imagen en icono
    ImageIcon icon = new ImageIcon("images/my_image.png");

    //Crear imagen escalada y volver a introducir en el icono
    Image newImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
    icon.setImage(newImage);

    //Crear JLabel (o lo que sea) con icono
    JLabel icon_label = new JLabel(icon);
    jp.add(icon_label);
    
    jp.add(new JLabel("Button"));
    JButton icon_button = new JButton(icon);
    jp.add(icon_button);
    
    //Favicon
    jf.setIconImage(icon.getImage());

    //Añadir panel al container
    Container cp = jf.getContentPane();
    cp.add(jp);
        
    jf.setSize(300, 200);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}


