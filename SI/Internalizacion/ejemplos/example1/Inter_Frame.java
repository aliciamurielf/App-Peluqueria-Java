package example1;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Internacionalización en Java
 * Ejemplo 1
 */
public class Inter_Frame {
  public static void main(String[] args) {
    
    //Obtener Locale por defecto
    Locale currentLocale = Locale.getDefault();
    System.out.println(currentLocale.getLanguage());
    System.out.println(currentLocale.getCountry());

    //Definir un Locale específico
    //currentLocale = Locale.of("en", "GB");
    //currentLocale = Locale.of("de", "DE");
    
    //Si el Locale actual no está entre los soportados, asignar por defecto
    if(! (
        (currentLocale.getLanguage().equals("es") &&
            currentLocale.getCountry().equals("ES")) ||
        (currentLocale.getLanguage().equals("en") && 
            currentLocale.getCountry().equals("GB")) || 
        (currentLocale.getLanguage().equals("de") && 
            currentLocale.getCountry().equals("DE"))
    )){
        System.out.println("Español por defecto");
        currentLocale =  Locale.of("es", "ES");
    }
    
    //Obtener el conjunto de cadenas del bundle para el Locale actual
    ResourceBundle bundle_text = ResourceBundle.getBundle("bundle.Bundle", currentLocale);
    
    //Utilizar textos del bundle en la interfaz
    JFrame jf = new JFrame(bundle_text.getString("Titulo"));   
    
    
    /* Definición de la interfaz */
    BorderLayout bl = new BorderLayout(5, 5);
    bl.setHgap(5);
    bl.setVgap(5);
    
    //Panel común
    JPanel jp = new JPanel(bl);
    jf.add(jp);
    
    //Norte
    JLabel ej_label = new JLabel(bundle_text.getString("Cabecera"));
    ej_label.setHorizontalAlignment(JLabel.CENTER);
    ej_label.setVerticalAlignment(JLabel.CENTER);
    jp.add(ej_label, BorderLayout.NORTH);
    
    //Sur
    JPanel south_panel = new JPanel();
    FlowLayout south_layout = new FlowLayout();
    south_layout.setAlignment(FlowLayout.RIGHT);
    south_panel.setLayout(south_layout);
    south_panel.add(new JButton(bundle_text.getString("Boton_atras")));
    south_panel.add(new JButton(bundle_text.getString("Boton_siguiente")));
    jp.add(south_panel, BorderLayout.SOUTH);
    
    jf.setSize(400, 300);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
    
  }
}