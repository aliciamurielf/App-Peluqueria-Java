import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GestorUsuarios {
    
    private final String RUTA = "usuarios.txt";
    
    public String validarUsuario(String user, String pass) {
        try (Scanner sc = new Scanner(new File("usuarios.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue; 
                
                String[] datos = linea.split(";");
                if (datos[0].equals(user) && datos[1].equals(pass)) {
                    return datos[2];
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo usuarios.txt no existe en la raíz del proyecto.");
        }
        return null; 
    }

    public boolean registrarUsuario(String telefono, String pass) {
        
        try (Scanner sc = new Scanner(new File("usuarios.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos[0].equals(telefono)) {
                    return false;
                }
            }
        } catch (Exception e) {} 

        try (java.io.FileWriter fw = new java.io.FileWriter("usuarios.txt", true);
             java.io.PrintWriter out = new java.io.PrintWriter(fw)) {
            out.println("\n" + telefono + ";" + pass + ";cliente");
            return true;
        } catch (Exception e) {
            System.err.println("Error al escribir en usuarios.txt");
            return false;
        }
    }

    public boolean existeUsuario(String telefono) {
        try (java.util.Scanner sc = new java.util.Scanner(new java.io.File("usuarios.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos[0].equals(telefono)) {
                    return true;
                }
            }
        } catch (Exception e) {}
        return false; 
    }

    public String obtenerRol(String usuario, String contrasena) {
        try (Scanner sc = new Scanner(new File(RUTA))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] partes = linea.split(";");
                if (partes.length >= 3 && partes[0].equals(usuario) && partes[1].equals(contrasena)) {
                    return partes[2]; 
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encuentra usuarios.txt");
        }
        return null; 
    }
}