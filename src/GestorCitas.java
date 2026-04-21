import java.io.*;
import java.util.*;

public class GestorCitas {
    private final String RUTA = "citas.txt"; // Asegúrate de que esté en la raíz

    public List<String> leerCitas() {
        List<String> lista = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(RUTA))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split(";");
                    lista.add(datos[0] + " - " + datos[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("Creando nuevo archivo de citas...");
        }
        return lista;
    }

    public void guardarCita(String hora, String nombre) {
        try (PrintWriter out = new PrintWriter(new FileWriter(RUTA, true))) {
            out.println(hora + ";" + nombre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCita(String citaCompleta) {
        List<String> citas = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(RUTA))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] datos = linea.split(";");
                String formatoLista = datos[0] + " - " + datos[1];
                if (!formatoLista.equals(citaCompleta)) {
                    citas.add(linea);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        try (PrintWriter out = new PrintWriter(new FileWriter(RUTA))) {
            for (String c : citas) out.println(c);
        } catch (Exception e) { e.printStackTrace(); }
    }
}