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

        
        JLabel lblTitulo = new JLabel(GestorIdiomas.getTexto("inventario.titulo"), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(10, 0, 60));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        tarjetaBlanca.add(lblTitulo, BorderLayout.NORTH);

        
        gridProductos = new JPanel(new GridLayout(0, 3, 10, 10)); 
        gridProductos.setBackground(Color.WHITE);
        refrescarGrid();

        JScrollPane scroll = new JScrollPane(gridProductos);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        tarjetaBlanca.add(scroll, BorderLayout.CENTER);

        
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotones.setBackground(Color.WHITE);
        pnlBotones.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnEliminar = new JButton(GestorIdiomas.getTexto("inventario.eliminar"));
        btnEliminar.setBackground(new Color(220, 53, 69)); 
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);

        JButton btnAnadir = new JButton(GestorIdiomas.getTexto("inventario.anadir"));
        btnAnadir.setBackground(new Color(30, 130, 76)); 
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.setFocusPainted(false);

        btnEliminar.addActionListener(e -> {
            if(listaProductos.size() > 0) {
                
                listaProductos.remove(listaProductos.size() - 1);
                refrescarGrid();
            } else {
                JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("inventario.no_productos"));
            }
        });

        btnAnadir.addActionListener(e -> {
            String nuevoNombre = JOptionPane.showInputDialog(this, GestorIdiomas.getTexto("inventario.nombre_producto"));
            if(nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                String stockStr = JOptionPane.showInputDialog(this, GestorIdiomas.getTexto("inventario.stock"));
                try {
                    int stock = Integer.parseInt(stockStr);
                    listaProductos.add(new Producto(nuevoNombre, stock));
                    refrescarGrid();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, GestorIdiomas.getTexto("inventario.stock_error"));
                }
            }
        });

        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnAnadir);
        tarjetaBlanca.add(pnlBotones, BorderLayout.SOUTH);

        
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

        JLabel lblStock = new JLabel(" " + String.valueOf(p.stock) + " ");
        lblStock.setFont(new Font("Arial", Font.BOLD, 10));
        lblStock.setOpaque(true);
        lblStock.setBackground(p.stock == 0 ? Color.RED : Color.WHITE);
        lblStock.setForeground(p.stock == 0 ? Color.WHITE : Color.BLACK);
        lblStock.setBorder(new LineBorder(Color.GRAY, 1));

        JPanel pnlStock = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlStock.setBackground(Color.WHITE);
        pnlStock.add(lblStock);
        card.add(pnlStock, BorderLayout.NORTH);

        JLabel lblImg = new JLabel("", SwingConstants.CENTER);
        java.io.File archivoImg = new java.io.File("src/images/producto.png");
        if (archivoImg.exists()) {
            ImageIcon iconImg = new ImageIcon(archivoImg.getAbsolutePath());
            Image imgResized = iconImg.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblImg.setIcon(new ImageIcon(imgResized));
        }
        card.add(lblImg, BorderLayout.CENTER);

        JLabel lblNombre = new JLabel(p.nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 9));
        lblNombre.setBorder(new EmptyBorder(5, 2, 5, 2));
        card.add(lblNombre, BorderLayout.SOUTH);

        return card;
    }
}
