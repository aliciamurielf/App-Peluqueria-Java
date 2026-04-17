import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GestorUsuarios {
    
    // Aquí pegas el método que tienes
    public String validarUsuario(String user, String pass) {
        try (Scanner sc = new Scanner(new File("usuarios.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue; // Saltamos líneas vacías
                
                String[] datos = linea.split(";");
                // Comparamos usuario y contraseña
                if (datos[0].equals(user) && datos[1].equals(pass)) {
                    return datos[2]; // Retorna "admin" o "cliente"
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo usuarios.txt no existe en la raíz del proyecto.");
        }
        return null; // Si no lo encuentra o hay error
    }
}