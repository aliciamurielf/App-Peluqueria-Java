# Laura Estilistas - App de Gestión y Reservas

Este proyecto es un prototipo de aplicación de escritorio desarrollada en **Java Swing** para la gestión integral de un salón de belleza/peluquería. Ha sido diseñado siguiendo los principios fundamentales del **Diseño Centrado en el Usuario (DCU)** y el **Diseño Basado en Tareas**.

## Características Principales

La aplicación cuenta con un sistema de inicio de sesión que divide la experiencia en dos roles principales:

### Rol Administrador (Peluquera)
* **Dashboard:** Panel de control principal con resumen visual de las próximas citas.
* **Agenda:** Visualización y gestión de todas las reservas en formato tabla. Permite añadir citas manualmente.
* **Clientes:** Base de datos de clientes con acceso a una **Ficha Técnica** detallada (fórmulas de tintes, fechas de visitas, alergias).
* **Inventario:** Control de stock de productos con opciones rápidas para añadir o eliminar artículos, previniendo errores mediante cuadros de confirmación.

### Rol Cliente
* **Publicidad y Cross-selling:** Visualización de ofertas destacadas (ej. descuentos en productos) en la pantalla principal.
* **Reserva Intuitiva:** Sistema de petición de cita mediante menús desplegables (`JComboBox`) para evitar errores de escritura al seleccionar fecha, hora y servicio.

## Tecnologías y Conceptos Aplicados

Este prototipo pone en práctica diversos conceptos avanzados de interfaces gráficas:
* **Java Swing:** Uso de contenedores (`JFrame`, `JPanel`) y gestores de diseño (`CardLayout`, `BorderLayout`, `GridLayout`).
* **Manejo de Eventos:** Implementación de `ActionListener` para dar interactividad a botones y formularios.
* **Arquitectura MVC (Modelo-Vista-Controlador):** Uso de `DefaultTableModel` y `DefaultListModel` compartidos para que las reservas de los clientes actualicen automáticamente la agenda y la base de datos del administrador en tiempo real.
* **Cuadros de Diálogo:** Uso extensivo de la clase `JOptionPane` para interactuar con el usuario (entradas de datos, mensajes de confirmación y alertas de error).
* **Look and Feel (LaF):** Personalización estética de la interfaz mediante `NimbusLookAndFeel` y una paleta de colores personalizada (tonos menta y turquesa) para huir del diseño clásico por defecto de Java.

## Estructura del Proyecto

Para que la aplicación funcione con su diseño gráfico completo, requiere la siguiente estructura de carpetas:

```text
/src
 ├── AppPeluqueriaFinal.java  # Código fuente principal
 └── /images                  # Carpeta de recursos gráficos
      ├── logo.png            # Logo del inicio de sesión
      └── promo.png           # Imagen de la oferta para clientes
