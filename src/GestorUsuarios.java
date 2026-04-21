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

    // Método para registrar usuarios
    public boolean registrarUsuario(String telefono, String pass) {
        // Primero comprobamos que el teléfono no exista ya
        try (Scanner sc = new Scanner(new File("usuarios.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos[0].equals(telefono)) {
                    return false; // El usuario ya existe
                }
            }
        } catch (Exception e) {} // Si el fichero no existe, simplemente seguiremos para crearlo

        // Si llegamos aquí, el usuario no existe. Lo guardamos (append = true para no borrar los anteriores)
        try (java.io.FileWriter fw = new java.io.FileWriter("usuarios.txt", true);
             java.io.PrintWriter out = new java.io.PrintWriter(fw)) {
            // Guardamos con formato: usuario;contraseña;rol (todos los nuevos son "cliente")
            out.println("\n" + telefono + ";" + pass + ";cliente");
            return true;
        } catch (Exception e) {
            System.err.println("Error al escribir en usuarios.txt");
            return false;
        }
    }
}