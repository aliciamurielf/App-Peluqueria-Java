import java.util.Locale;
import java.util.ResourceBundle;

public class GestorIdiomas {
    
    private static Locale localeActual = new Locale("es", "ES");
    private static ResourceBundle bundle = ResourceBundle.getBundle("bundles.Textos", localeActual);

    public static void cambiarIdiomaBase() {
        if (localeActual.getLanguage().equals("es")) {
            localeActual = new Locale("en", "GB"); 
        } else if (localeActual.getLanguage().equals("en")) {
            localeActual = new Locale("fr", "FR"); 
        } else {
            localeActual = new Locale("es", "ES"); 
        }
        bundle = ResourceBundle.getBundle("bundles.Textos", localeActual);
    }

    public static String getTexto(String clave) {
        try {
            return bundle.getString(clave);
        } catch (Exception e) {
            return "!" + clave + "!";
        }
    }

    public static Locale getLocaleActual() {
        return localeActual;
    }
}
