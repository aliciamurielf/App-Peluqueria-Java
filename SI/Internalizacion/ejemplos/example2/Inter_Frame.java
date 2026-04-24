package example2;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Internacionalización en Java
 * Ejemplo 2
 */
public class Inter_Frame {
  private static Locale[] locales = {Locale.of("es", "ES"), Locale.of("en", "GB"), Locale.of("de", "DE")};
  private static int currentLocaleIndex = 0; // Índice para rastrear el idioma actual
  private static ResourceBundle bundle_text = ResourceBundle.getBundle("bundle.Bundle", locales[currentLocaleIndex]);
  private static JFrame jf;
  private static JLabel ej_label;
  private static JLabel saludo_label;
  private static JButton changeLanguageButton;

  public static void main(String[] args) {
    // Crear el JFrame
    jf = new JFrame(bundle_text.getString("Titulo"));

    /* Definición de la interfaz */
    BorderLayout bl = new BorderLayout(5, 5);
    JPanel jp = new JPanel(bl);
    jf.add(jp);

    // Norte
    ej_label = new JLabel(bundle_text.getString("Cabecera"));
    ej_label.setHorizontalAlignment(JLabel.CENTER);
    ej_label.setVerticalAlignment(JLabel.CENTER);
    jp.add(ej_label, BorderLayout.NORTH);

    // Centro (Saludo)
    saludo_label = new JLabel(bundle_text.getString("Saludo"));
    saludo_label.setHorizontalAlignment(JLabel.CENTER);
    saludo_label.setVerticalAlignment(JLabel.CENTER);
    jp.add(saludo_label, BorderLayout.CENTER);

    // Sur
    JPanel south_panel = new JPanel();
    FlowLayout south_layout = new FlowLayout();
    south_layout.setAlignment(FlowLayout.RIGHT);
    south_panel.setLayout(south_layout);

    changeLanguageButton = new JButton(bundle_text.getString("Cambiar_idioma"));

    // Asignar ActionListener al botón usando la clase externa
    changeLanguageButton.addActionListener(new ChangeLanguageAction(locales, currentLocaleIndex, jf, ej_label, saludo_label, changeLanguageButton));

    south_panel.add(changeLanguageButton);
    jp.add(south_panel, BorderLayout.SOUTH);

    jf.setSize(400, 300);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}