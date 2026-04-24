import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelAdminInventario extends JPanel {

    private VentanaPrincipal ventanaPrincipal;
    private JPanel gridProductos;

    class Producto {
        String nombre;
        int stock;
        public Producto(String n, int s) { nombre = n; stock = s; }
    }

    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public PanelAdminInventario(VentanaPrincipal ventana) {
        this.ventanaPrincipal = ventana;
        
        // Inicializar datos falsos
        listaProductos.add(new Producto("Champú 1L", 5));
        listaProductos.add(new Producto("Acondicionador", 4));
        listaProductos.add(new Producto("Tinte 6.0", 7));
        listaProductos.add(new Producto("Decolorante", 0));
        listaProductos.add(new Producto("Mascarilla", 3));
        listaProductos.add(new Producto("Tinte 8.1", 18));

        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel tarjetaBlanca = new JPanel(new BorderLayout());
        tarjetaBlanca.setBackground(Color.WHITE);
        tarjetaBlanca.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // TÍTULO
        JLabel lblTitulo = new JLabel("INVENTARIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        // CUADRÍCULA DE PRODUCTOS
        gridProductos = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columnas
        gridProductos.setBackground(Color.WHITE);
        refrescarGrid();

        JScrollPane scroll = new JScrollPane(gridProductos);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        tarjetaBlanca.add(scroll, BorderLayout.CENTER);

        // BOTONES DE AÑADIR / ELIMINAR
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotones.setBackground(Color.WHITE);
        pnlBotones.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnEliminar = new JButton("- ELIMINAR");
        btnEliminar.setBackground(new Color(220, 53, 69)); // Rojo suave
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);

        JButton btnAnadir = new JButton("+ AÑADIR");
        btnAnadir.setBackground(new Color(30, 130, 76)); // Verde fuerte
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.setFocusPainted(false);

        btnEliminar.addActionListener(e -> {
            if(listaProductos.size() > 0) {
                // Elimina el último producto para el ejemplo
                listaProductos.remove(listaProductos.size() - 1);
                refrescarGrid();
            } else {
                JOptionPane.showMessageDialog(this, "No hay productos para eliminar.");
            }
        });

        btnAnadir.addActionListener(e -> {
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nombre del nuevo producto:");
            if(nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                String stockStr = JOptionPane.showInputDialog(this, "Cantidad en stock (Ej: 10):");
                try {
                    int stock = Integer.parseInt(stockStr);
                    listaProductos.add(new Producto(nuevoNombre, stock));
                    refrescarGrid();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.");
                }
            }
        });

        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnAnadir);
        tarjetaBlanca.add(pnlBotones, BorderLayout.SOUTH);

        // Envolverlo en Panel Central
        JPanel wrapperCentro = new JPanel(new GridBagLayout());
        wrapperCentro.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH; gbc.insets = new Insets(10, 15, 10, 15);
        wrapperCentro.add(tarjetaBlanca, gbc);
        
        add(wrapperCentro, BorderLayout.CENTER);
    }

    private void refrescarGrid() {
        gridProductos.removeAll();
        for (Producto p : listaProductos) {
            gridProductos.add(crearCardProducto(p));
        }
        gridProductos.revalidate();
        gridProductos.repaint();
    }

    private JPanel crearCardProducto(Producto p) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        
        // Cantidad (STOCK) arriba a la izquierda
        JLabel lblStock = new JLabel(" " + String.valueOf(p.stock) + " ");
        lblStock.setFont(new Font("Arial", Font.BOLD, 10));
        lblStock.setOpaque(true);
        // Si el stock es 0, que salga rojito para advertir (es un extra que queda bien y es sencillo)
        lblStock.setBackground(p.stock == 0 ? Color.RED : Color.WHITE);
        lblStock.setForeground(p.stock == 0 ? Color.WHITE : Color.BLACK);
        lblStock.setBorder(new LineBorder(Color.GRAY, 1));

        JPanel pnlStock = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlStock.setBackground(Color.WHITE);
        pnlStock.add(lblStock);
        card.add(pnlStock, BorderLayout.NORTH);

        // IMAGEN EN EL CENTRO
        JLabel lblImg = new JLabel("📦", SwingConstants.CENTER); // Emoji caja
        lblImg.setFont(new Font("Arial", Font.PLAIN, 40));
        card.add(lblImg, BorderLayout.CENTER);

        // NOMBRE ABAJO
        JLabel lblNombre = new JLabel(p.nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 9)); // Letra más pequeñita para que quepa
        lblNombre.setBorder(new EmptyBorder(5, 2, 5, 2));
        card.add(lblNombre, BorderLayout.SOUTH);

        return card;
    }
}
