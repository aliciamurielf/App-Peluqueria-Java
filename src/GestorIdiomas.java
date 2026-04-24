import java.util.Locale;
import java.util.ResourceBundle;

public class GestorIdiomas {
    // Por defecto iniciamos en Español (España)
    private static Locale localeActual = new Locale("es", "ES");
    private static ResourceBundle bundle = ResourceBundle.getBundle("bundles.Textos", localeActual);

    // Método para cambiar el idioma: Si está en ES pasa a EN, luego a FR, y viceversa
    public static void cambiarIdiomaBase() {
        if (localeActual.getLanguage().equals("es")) {
            localeActual = new Locale("en", "GB"); // Inglés
        } else if (localeActual.getLanguage().equals("en")) {
            localeActual = new Locale("fr", "FR"); // Francés
        } else {
            localeActual = new Locale("es", "ES"); // Volver a Español
        }
        bundle = ResourceBundle.getBundle("bundles.Textos", localeActual);
    }

    // Método que servirá para llamar a todos los textos limpios
    public static String getTexto(String clave) {
        try {
            return bundle.getString(clave);
        } catch (Exception e) {
            return "!" + clave + "!"; // Fallback si olvidamos traducir algo
        }
    }
}
