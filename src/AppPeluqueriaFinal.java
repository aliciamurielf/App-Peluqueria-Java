import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppPeluqueriaFinal extends JFrame {

    // --- NUEVA PALETA DE COLORES (Azulito / Spa Elegante) ---
    private Color bgApp = new Color(240, 248, 255);       // Fondo azul hielo muy clarito (Alice Blue)
    private Color bgCards = Color.WHITE;                  // Blanco puro para tarjetas
    private Color colorPrimario = new Color(84, 160, 255); // Azul Cielo / Azul Pastel vibrante
    private Color textDark = new Color(40, 50, 70);       // Azul marino muy oscuro para textos
    private Color textLight = new Color(130, 140, 160);   // Gris azulado suave para textos secundarios

    // --- GESTORES DE PANTALLAS ---
    private JPanel panelRaiz;
    private CardLayout cardLayoutRaiz;
    private JPanel panelAdminCards;
    private CardLayout cardLayoutAdmin;

    // --- MODELOS COMPARTIDOS (Nuestra "Base de Datos" en memoria) ---
    private DefaultTableModel modeloAgenda;
    private DefaultListModel<String> modeloClientes;

    public AppPeluqueriaFinal() {
        setTitle("Laura Estilistas - App");
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); 

        // Aplicar estilo visual moderno "Nimbus"
        try { 
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) { 
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception ex){}
        }

        // 1. Inicializar "Base de datos" de la Agenda
        String[] columnasAgenda = {"Día/Hora", "Cita"};
        modeloAgenda = new DefaultTableModel(null, columnasAgenda);
        modeloAgenda.addRow(new Object[]{"15 Oct - 10:00", "Carmen Martínez (Tinte)"});
        modeloAgenda.addRow(new Object[]{"15 Oct - 11:30", "Luis Pérez (Corte)"});

        // 2. Inicializar "Base de datos" de los Clientes
        modeloClientes = new DefaultListModel<>();
        modeloClientes.addElement("Ana García");
        modeloClientes.addElement("Beatriz López");
        modeloClientes.addElement("Carmen Martínez");

        // Inicializar Gestor de Pantallas principal
        cardLayoutRaiz = new CardLayout();
        panelRaiz = new JPanel(cardLayoutRaiz);

        panelRaiz.add(crearPantallaLogin(), "Login");
        panelRaiz.add(crearAppAdministrador(), "AppAdmin");
        panelRaiz.add(crearAppCliente(), "AppCliente");

        add(panelRaiz);
    }

    // =========================================================
    // 1. PANTALLA DE LOGIN
    // =========================================================
    private JPanel crearPantallaLogin() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgApp);

        // Cabecera con imagen del Logo
        JPanel header = new JPanel(new GridBagLayout());
        header.setBackground(colorPrimario);
        header.setPreferredSize(new Dimension(400, 250));
        
        JLabel tituloLogo = new JLabel("LAURA ESTILISTAS");
        tituloLogo.setFont(new Font("SansSerif", Font.BOLD, 28)); 
        tituloLogo.setForeground(Color.WHITE);
        tituloLogo.setHorizontalTextPosition(JLabel.CENTER);
        tituloLogo.setVerticalTextPosition(JLabel.BOTTOM);
        
        try {
            // Comprobamos la ruta para que VS Code encuentre el .jpg sin problemas
            String rutaLogo = new java.io.File("images/logo.jpg").exists() ? "images/logo.jpg" : "src/images/logo.jpg";
            
            ImageIcon icon = new ImageIcon(rutaLogo); 
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            tituloLogo.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println("Nota: No se ha podido cargar logo.jpg");
        }
        
        header.add(tituloLogo);

        // Cuerpo del Login
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(bgApp);
        body.setBorder(new EmptyBorder(30, 40, 40, 40));

        JLabel lblInstrucciones = new JLabel("Bienvenido/a");
        lblInstrucciones.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblInstrucciones.setForeground(colorPrimario);
        lblInstrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblUsuario.setForeground(textDark);
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(250, 40));
        txtUsuario.setMaximumSize(new Dimension(250, 40));
        txtUsuario.setHorizontalAlignment(JTextField.CENTER);
        txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPass.setForeground(textDark);
        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setPreferredSize(new Dimension(250, 40));
        txtPass.setMaximumSize(new Dimension(250, 40));
        txtPass.setHorizontalAlignment(JPasswordField.CENTER);
        txtPass.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton btnEntrar = crearBotonEstilo("ENTRAR", colorPrimario, Color.WHITE);
        btnEntrar.setMaximumSize(new Dimension(250, 50));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim().toLowerCase(); 
            String password = new String(txtPass.getPassword()); 

            if (usuario.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Rellena tu nombre y contraseña.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (usuario.equals("admin") && password.equals("1234")) {
                txtUsuario.setText(""); txtPass.setText(""); 
                cardLayoutRaiz.show(panelRaiz, "AppAdmin");
            } else if (usuario.equals("cliente") && password.equals("1234")) {
                txtUsuario.setText(""); txtPass.setText(""); 
                cardLayoutRaiz.show(panelRaiz, "AppCliente");
            } else {
                JOptionPane.showMessageDialog(panel, "Credenciales incorrectas.\nPrueba admin/1234 o cliente/1234", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        body.add(lblInstrucciones);
        body.add(Box.createRigidArea(new Dimension(0, 25)));
        body.add(lblUsuario);
        body.add(Box.createRigidArea(new Dimension(0, 5)));
        body.add(txtUsuario);
        body.add(Box.createRigidArea(new Dimension(0, 15)));
        body.add(lblPass);
        body.add(Box.createRigidArea(new Dimension(0, 5)));
        body.add(txtPass);
        body.add(Box.createRigidArea(new Dimension(0, 30)));
        body.add(btnEntrar);

        panel.add(header, BorderLayout.NORTH);
        panel.add(body, BorderLayout.CENTER);
        return panel;
    }

    // =========================================================
    // 2. VISTA DEL CLIENTE (Oferta + Reservas + BBDD)
    // =========================================================
    private JPanel crearAppCliente() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgApp);

        JPanel header = crearCabecera("Reserva de Citas", "Encuentra tu momento");
        
        JPanel content = new JPanel(new BorderLayout(0, 15));
        content.setBackground(bgApp);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));

        // TARJETA DE PUBLICIDAD
        JPanel tarjetaPromo = new JPanel(new BorderLayout());
        tarjetaPromo.setBackground(new Color(230, 240, 255)); // Azul hielo suave para el fondo de la oferta
        tarjetaPromo.setBorder(BorderFactory.createLineBorder(colorPrimario, 2));

        JLabel lblPromoImage = new JLabel();
        lblPromoImage.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            // Comprobamos la ruta del .jpg
            String rutaPromo = new java.io.File("images/promo.jpg").exists() ? "images/promo.jpg" : "src/images/promo.jpg";
            
            ImageIcon iconPromo = new ImageIcon(rutaPromo); 
            Image scaledPromo = iconPromo.getImage().getScaledInstance(350, 150, Image.SCALE_SMOOTH);
            lblPromoImage.setIcon(new ImageIcon(scaledPromo));
        } catch (Exception e) {
            lblPromoImage.setText("<html><center><br><b>[ IMAGEN OFERTA ]</b><br><br>Champú Reparador Aloe Vera<br>20% Descuento</center></html>");
        }
        tarjetaPromo.add(lblPromoImage, BorderLayout.CENTER);

        // BOTÓN DE RESERVA
        JButton btnReservar = crearBotonEstilo("PEDIR CITA AHORA", colorPrimario, Color.WHITE);
        btnReservar.setPreferredSize(new Dimension(300, 60));
        
        btnReservar.addActionListener(e -> {
            JTextField campoNombre = new JTextField();
            JComboBox<String> comboServicios = new JComboBox<>(new String[]{"Corte", "Tinte", "Balayage", "Peinado"});
            
            String[] dias = new String[31]; for(int i=0; i<31; i++) dias[i] = String.valueOf(i+1);
            JComboBox<String> comboDias = new JComboBox<>(dias);
            JComboBox<String> comboMeses = new JComboBox<>(new String[]{"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"});

            JPanel panelFecha = new JPanel(new GridLayout(1, 2, 5, 0));
            panelFecha.add(comboDias); panelFecha.add(comboMeses);

            JComboBox<String> comboHoras = new JComboBox<>(new String[]{"09:30", "10:30", "11:30", "12:30", "16:30", "17:30", "18:30"});

            Object[] mensaje = { "Tu nombre (y Apellido):", campoNombre, "Servicio:", comboServicios, "Día y Mes:", panelFecha, "Hora:", comboHoras };

            if (JOptionPane.showConfirmDialog(panel, mensaje, "Nueva Reserva", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                String nom = campoNombre.getText().trim();
                
                // --- LÓGICA DE BASE DE DATOS (MVC) ---
                if(!nom.isEmpty()) {
                    nom = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
                    if(!modeloClientes.contains(nom)) {
                        modeloClientes.addElement(nom);
                    }
                } else {
                    nom = "Cliente Anónimo";
                }

                // Guardar cita en agenda
                String fecha = comboDias.getSelectedItem() + " " + comboMeses.getSelectedItem() + " - " + comboHoras.getSelectedItem();
                String servicio = nom + " (" + comboServicios.getSelectedItem() + ")";
                modeloAgenda.addRow(new Object[]{fecha, servicio});

                JOptionPane.showMessageDialog(panel, "Cita confirmada con éxito.", "Reserva", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        content.add(tarjetaPromo, BorderLayout.NORTH); 
        content.add(btnReservar, BorderLayout.CENTER); 

        JButton btnVolver = crearBotonEstilo("Cerrar Sesión", Color.WHITE, textDark);
        btnVolver.addActionListener(e -> cardLayoutRaiz.show(panelRaiz, "Login"));

        panel.add(header, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        panel.add(btnVolver, BorderLayout.SOUTH);

        return panel;
    }

    // =========================================================
    // 3. VISTA DEL ADMINISTRADOR
    // =========================================================
    private JPanel crearAppAdministrador() {
        JPanel appPanel = new JPanel(new BorderLayout());
        cardLayoutAdmin = new CardLayout();
        panelAdminCards = new JPanel(cardLayoutAdmin);
        panelAdminCards.setBackground(bgApp);

        panelAdminCards.add(crearDashboard(), "Dashboard");
        panelAdminCards.add(crearAgenda(), "Agenda");
        panelAdminCards.add(crearClientes(), "Clientes");
        panelAdminCards.add(crearInventario(), "Inventario");

        JPanel navBar = crearBarraNavegacionAdmin();

        appPanel.add(panelAdminCards, BorderLayout.CENTER);
        appPanel.add(navBar, BorderLayout.SOUTH);
        return appPanel;
    }

    // --- DASHBOARD ADMIN ---
    private JPanel crearDashboard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgApp);
        panel.add(crearCabecera("Panel de Control", "Visión general"), BorderLayout.NORTH);
        
        JPanel cuerpo = new JPanel(new BorderLayout());
        cuerpo.setBackground(bgApp);
        cuerpo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel tarjetaCitas = new JPanel(new BorderLayout());
        tarjetaCitas.setBackground(bgCards);
        tarjetaCitas.setBorder(BorderFactory.createCompoundBorder(new LineBorder(colorPrimario, 1, true), new EmptyBorder(15, 15, 15, 15)));
        
        JLabel subtitulo = new JLabel("Citas pendientes hoy");
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        subtitulo.setForeground(colorPrimario);
        
        JList<String> lista = new JList<>(new String[]{"10:00 - Carmen Martínez", "11:30 - Luis Pérez"});
        lista.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lista.setBackground(bgCards);
        
        tarjetaCitas.add(subtitulo, BorderLayout.NORTH);
        tarjetaCitas.add(new JScrollPane(lista), BorderLayout.CENTER);
        cuerpo.add(tarjetaCitas, BorderLayout.CENTER);
        panel.add(cuerpo, BorderLayout.CENTER);
        return panel;
    }

    // --- AGENDA ADMIN ---
    private JPanel crearAgenda() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgApp);
        panel.add(crearCabecera("Agenda", "Todas las reservas"), BorderLayout.NORTH);
        
        panel.add(new JScrollPane(estilizarTabla(new JTable(modeloAgenda))), BorderLayout.CENTER);

        JButton btnNuevaCita = crearBotonEstilo("+ AÑADIR CITA MANUAL", colorPrimario, Color.WHITE);
        btnNuevaCita.addActionListener(e -> {
            JTextField campoNombre = new JTextField();
            JTextField campoServicio = new JTextField();
            String[] dias = new String[31]; for(int i=0; i<31; i++) dias[i] = String.valueOf(i+1);
            JComboBox<String> comboDias = new JComboBox<>(dias);
            JComboBox<String> comboMeses = new JComboBox<>(new String[]{"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"});
            JPanel panelFecha = new JPanel(new GridLayout(1, 2, 5, 0));
            panelFecha.add(comboDias); panelFecha.add(comboMeses);
            JComboBox<String> comboHoras = new JComboBox<>(new String[]{"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "16:00", "16:30", "17:00", "17:30"});

            Object[] mensaje = { "Nombre del cliente:", campoNombre, "Servicio:", campoServicio, "Día y Mes:", panelFecha, "Hora:", comboHoras };

            if (JOptionPane.showConfirmDialog(panel, mensaje, "Añadir Cita Manual", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                String nom = campoNombre.getText().trim();
                String serv = campoServicio.getText().trim();
                if(nom.isEmpty()) nom = "Desconocido";
                if(serv.isEmpty()) serv = "Varios";
                
                String fecha = comboDias.getSelectedItem() + " " + comboMeses.getSelectedItem() + " - " + comboHoras.getSelectedItem();
                modeloAgenda.addRow(new Object[]{fecha, nom + " (" + serv + ")"});
            }
        });

        panel.add(btnNuevaCita, BorderLayout.SOUTH);
        return panel;
    }

    // --- CLIENTES ADMIN ---
    private JPanel crearClientes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgApp);
        panel.add(crearCabecera("Clientes", "Fichas técnicas"), BorderLayout.NORTH);
        
        // Carga automática del modelo compartido (MVC)
        JList<String> listaClientes = new JList<>(modeloClientes);
        listaClientes.setFont(new Font("SansSerif", Font.PLAIN, 18));
        listaClientes.setFixedCellHeight(50);
        
        JButton btnFicha = crearBotonEstilo("VER FICHA TÉCNICA", colorPrimario, Color.WHITE);
        btnFicha.addActionListener(e -> {
            String seleccionado = listaClientes.getSelectedValue();
            if (seleccionado != null) {
                String notas = "";
                if (seleccionado.contains("Carmen")) {
                    notas = "📅 Última visita: Hace 3 semanas\n🎨 Fórmula Tinte: Raíz Castaño Claro (5.0) 20 vol. + Medios 5.3 a 10 vol.\n☕ Preferencias: Café con sacarina.";
                } else if (seleccionado.contains("Ana")) {
                    notas = "📅 Última visita: Hace 2 meses\n🎨 Fórmula Tinte: Balayage deco 30 vol. Matiz ceniza (8.1).\n⚠️ Alergias: Cuero cabelludo sensible.";
                } else {
                    notas = "📅 Última visita: Primera cita\n🎨 Fórmula Tinte: Sin registro previo.\n📝 Notas: Cliente nuevo, abrir ficha al llegar.";
                }
                JOptionPane.showMessageDialog(panel, notas, "Ficha de " + seleccionado, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Selecciona un cliente de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        panel.add(new JScrollPane(listaClientes), BorderLayout.CENTER);
        panel.add(btnFicha, BorderLayout.SOUTH); 
        return panel;
    }

    // --- INVENTARIO ADMIN ---
    private JPanel crearInventario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgApp);
        panel.add(crearCabecera("Inventario", "Control de stock"), BorderLayout.NORTH);
        
        DefaultTableModel mod = new DefaultTableModel(new Object[][]{{"Champú 1L", "12"}, {"Tinte 6.0", "2"}}, new String[]{"Producto", "Stock"});
        JTable tablaStock = estilizarTabla(new JTable(mod));

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        panelBotones.setBackground(bgApp);
        panelBotones.setBorder(new EmptyBorder(15, 20, 15, 20));

        JButton btnAnadir = crearBotonEstilo("+ AÑADIR", colorPrimario, Color.WHITE);
        JButton btnEliminar = crearBotonEstilo("- ELIMINAR", Color.WHITE, new Color(220, 53, 69)); // Rojo suave
        btnEliminar.setBorder(new LineBorder(new Color(220, 53, 69), 1, true));

        btnAnadir.addActionListener(e -> {
            String nuevo = JOptionPane.showInputDialog(panel, "Nombre del nuevo producto:", "Nuevo Producto", JOptionPane.PLAIN_MESSAGE);
            if (nuevo != null && !nuevo.trim().isEmpty()) {
                mod.addRow(new Object[]{nuevo, "0"});
                JOptionPane.showMessageDialog(panel, "Añadido: " + nuevo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaStock.getSelectedRow();
            if (fila != -1) { 
                if (JOptionPane.showConfirmDialog(panel, "¿Eliminar producto?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    mod.removeRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Selecciona un producto primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        panelBotones.add(btnEliminar);
        panelBotones.add(btnAnadir);

        panel.add(new JScrollPane(tablaStock), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        return panel;
    }

    // =========================================================
    // UTILIDADES DE DISEÑO GLOBALES
    // =========================================================
    private JPanel crearCabecera(String tituloStr, String subtituloStr) {
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(colorPrimario);
        header.setBorder(new EmptyBorder(25, 20, 20, 20));
        
        JLabel titulo = new JLabel(tituloStr);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26)); 
        titulo.setForeground(Color.WHITE);
        
        JLabel subtitulo = new JLabel(subtituloStr);
        subtitulo.setFont(new Font("SansSerif", Font.ITALIC, 16));
        subtitulo.setForeground(new Color(230, 240, 255)); 
        header.add(titulo); header.add(subtitulo);
        return header;
    }

    private JTable estilizarTabla(JTable tabla) {
        tabla.setRowHeight(55);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tabla.setBackground(bgCards);
        tabla.setShowGrid(false);
        tabla.setTableHeader(null);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230,240,240)));
        tabla.setDefaultRenderer(Object.class, renderer);
        return tabla;
    }

    private JPanel crearBarraNavegacionAdmin() {
        JPanel navBar = new JPanel(new GridLayout(1, 5));
        navBar.setPreferredSize(new Dimension(400, 60));
        navBar.setBackground(Color.WHITE);
        navBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JButton b1 = crearBotonNav("INICIO"); b1.addActionListener(e -> cardLayoutAdmin.show(panelAdminCards, "Dashboard"));
        JButton b2 = crearBotonNav("AGENDA"); b2.addActionListener(e -> cardLayoutAdmin.show(panelAdminCards, "Agenda"));
        JButton b3 = crearBotonNav("CLIENTES"); b3.addActionListener(e -> cardLayoutAdmin.show(panelAdminCards, "Clientes"));
        JButton b4 = crearBotonNav("STOCK"); b4.addActionListener(e -> cardLayoutAdmin.show(panelAdminCards, "Inventario"));
        JButton b5 = crearBotonNav("SALIR"); b5.setForeground(new Color(220, 53, 69)); b5.addActionListener(e -> cardLayoutRaiz.show(panelRaiz, "Login"));

        navBar.add(b1); navBar.add(b2); navBar.add(b3); navBar.add(b4); navBar.add(b5);
        return navBar;
    }

    private JButton crearBotonNav(String t) {
        JButton b = new JButton(t); b.setFont(new Font("SansSerif", Font.BOLD, 10));
        b.setBackground(Color.WHITE); b.setForeground(textLight); b.setFocusPainted(false); b.setBorderPainted(false);
        return b;
    }

    private JButton crearBotonEstilo(String t, Color bg, Color fg) {
        JButton b = new JButton(t); b.setFont(new Font("SansSerif", Font.BOLD, 16));
        b.setBackground(bg); b.setForeground(fg); b.setFocusPainted(false); b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppPeluqueriaFinal().setVisible(true));
    }
}