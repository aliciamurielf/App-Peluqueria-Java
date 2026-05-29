import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GestorUsuarios {
    
    private final String RUTA = buscarRutaUsuarios();
    
    private String buscarRutaUsuarios() {
        File archivo = new File("usuarios.txt");
        if (archivo.exists()) {
            return archivo.getPath();
        }

        archivo = new File("src/usuarios.txt");
        if (archivo.exists()) {
            return archivo.getPath();
        }

        File actual = new File(System.getProperty("user.dir"));
        for (int i = 0; i < 3 && actual != null; i++) {
            archivo = new File(actual, "usuarios.txt");
            if (archivo.exists()) {
                return archivo.getPath();
            }
            actual = actual.getParentFile();
        }

        return "usuarios.txt";
    }

    public String validarUsuario(String user, String pass) {
        user = user.trim();
        pass = pass.trim();
        try (Scanner sc = new Scanner(new File(RUTA))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue; 
                
                String[] datos = linea.split(";");
                if (datos.length >= 3) {
                    String usuarioArchivo = datos[0].trim();
                    String passArchivo = datos[1].trim();
                    if (usuarioArchivo.equals(user) && passArchivo.equals(pass)) {
                        return datos[2].trim();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo usuarios.txt no existe en la ruta: " + RUTA);
        }
        return null; 
    }

    public boolean registrarUsuario(String telefono, String pass, String nombre, String apellidos) {
        telefono = telefono.trim();
        pass = pass.trim();
        nombre = nombre.trim();
        apellidos = apellidos.trim();
        
        try (Scanner sc = new Scanner(new File(RUTA))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length >= 1 && datos[0].trim().equals(telefono)) {
                    return false;
                }
            }
        } catch (Exception e) {
            // Si el archivo no existe, se creará al escribir.
        } 

        try (java.io.FileWriter fw = new java.io.FileWriter(RUTA, true);
             java.io.PrintWriter out = new java.io.PrintWriter(fw)) {
            File archivo = new File(RUTA);
            if (archivo.exists() && archivo.length() > 0) {
                out.println();
            }
            out.print(telefono + ";" + pass + ";cliente;" + nombre + ";" + apellidos);
            return true;
        } catch (Exception e) {
            System.err.println("Error al escribir en usuarios.txt en la ruta: " + RUTA);
            return false;
        }
    }

    public boolean existeUsuario(String telefono) {
        telefono = telefono.trim();
        try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(RUTA))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length >= 1 && datos[0].trim().equals(telefono)) {
                    return true;
                }
            }
        } catch (Exception e) {}
        return false; 
    }

    public String obtenerRol(String usuario, String contrasena) {
        usuario = usuario.trim();
        contrasena = contrasena.trim();
        try (Scanner sc = new Scanner(new File(RUTA))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(";");
                if (partes.length >= 3) {
                    String usuarioArchivo = partes[0].trim();
                    String passArchivo = partes[1].trim();
                    String rol = partes[2].trim();
                    String nombreArchivo = partes.length >= 4 ? partes[3].trim() : "";
                    String apellidosArchivo = partes.length >= 5 ? partes[4].trim() : "";
                    String nombreCompleto = (nombreArchivo + " " + apellidosArchivo).trim();

                    boolean usuarioCoincide = usuarioArchivo.equalsIgnoreCase(usuario)
                            || (!nombreCompleto.isEmpty() && nombreCompleto.equalsIgnoreCase(usuario));

                    if (usuarioCoincide && passArchivo.equals(contrasena)) {
                        return rol;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encuentra usuarios.txt");
        }
        return null; 
    }
}