package proyectofinal;

import java.util.Scanner;

public class ProyectoFinal {
    // ==============================================
    // CONSTANTES (valores que no cambian)
    // ==============================================
    static final int MAX_PRODUCTOS = 100;   // Máximo 100 productos
    static final int MAX_CLIENTES = 100;     // Máximo 100 clientes
    static final int MAX_VENTAS = 100;       // Máximo 100 ventas
    
    // ==============================================
    // VARIABLES PARA PRODUCTOS
    // ==============================================
    static String[] codigosProductos = new String[MAX_PRODUCTOS];
    static String[] nombresProductos = new String[MAX_PRODUCTOS];
    static double[] preciosProductos = new double[MAX_PRODUCTOS];
    static int[] stocksProductos = new int[MAX_PRODUCTOS];
    static int totalProductos = 0;  // Contador de productos registrados
    
    // ==============================================
    // VARIABLES PARA CLIENTES
    // ==============================================
    static String[] dniClientes = new String[MAX_CLIENTES];
    static String[] nombresClientes = new String[MAX_CLIENTES];
    static String[] apellidosClientes = new String[MAX_CLIENTES];
    static String[] telefonosClientes = new String[MAX_CLIENTES];
    static String[] direccionesClientes = new String[MAX_CLIENTES];
    static int totalClientes = 0;   // Contador de clientes registrados
    
    // ==============================================
    // VARIABLES PARA VENTAS
    // ==============================================
    static String[] ventaDNICliente = new String[MAX_VENTAS];
    static String[] ventaProductos = new String[MAX_VENTAS]; // Guarda los productos como texto
    static double[] ventaTotales = new double[MAX_VENTAS];
    static int totalVentas = 0;     // Contador de ventas realizadas
    
    // Scanner para leer entrada del usuario
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        // ==============================================
        // INICIO DEL PROGRAMA
        // ==============================================
        boolean sesionIniciada = iniciarSesion();
        
        if (sesionIniciada) {
            mostrarMenuPrincipal();
        }
        
        entrada.close();
        System.out.println("\n¡Gracias por usar nuestro sistema!");
    }
    
    // ==============================================
    // FUNCIÓN PARA INICIAR SESIÓN
    // ==============================================
    static boolean iniciarSesion() {
        System.out.println("================================");
        System.out.println("       INICIO DE SESIÓN        ");
        System.out.println("================================");
        
        int intentos = 0;
        final int MAX_INTENTOS = 3;
        
        while (intentos < MAX_INTENTOS) {
            System.out.print("Usuario: ");
            String usuario = entrada.nextLine();
            
            System.out.print("Contraseña: ");
            String clave = entrada.nextLine();
            
            // Credenciales válidas (en un sistema real esto estaría en una base de datos)
            if (usuario.equals("admin") && clave.equals("admin123")) {
                System.out.println("\n¡Bienvenido al sistema!");
                return true;
            } else {
                intentos++;
                System.out.println("\nCredenciales incorrectas. Intentos restantes: " + (MAX_INTENTOS - intentos));
            }
        }
        
        System.out.println("\nDemasiados intentos fallidos. Sistema bloqueado.");
        return false;
    }
    
    // ==============================================
    // FUNCIÓN PARA MOSTRAR EL MENÚ PRINCIPAL
    // ==============================================
    static void mostrarMenuPrincipal() {
        int opcion;
        
        do {
            System.out.println("\n================================");
            System.out.println("        MENÚ PRINCIPAL         ");
            System.out.println("================================");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Realizar Venta");
            System.out.println("4. Ver Reportes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerNumero();
            
            switch(opcion) {
                case 1:
                    gestionProductos();
                    break;
                case 2:
                    gestionClientes();
                    break;
                case 3:
                    realizarVenta();
                    break;
                case 4:
                    verReportes();
                    break;
                case 5:
                    System.out.println("\nSaliendo del sistema...");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }
    
    // ==============================================
    // FUNCIONES PARA GESTIÓN DE PRODUCTOS
    // ==============================================
    static void gestionProductos() {
        int opcion;
        
        do {
            System.out.println("\n================================");
            System.out.println("      GESTIÓN DE PRODUCTOS     ");
            System.out.println("================================");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Ver Productos");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerNumero();
            
            switch(opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    mostrarProductos();
                    break;
                case 3:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }
    
    static void agregarProducto() {
        if (totalProductos >= MAX_PRODUCTOS) {
            System.out.println("\nNo se pueden agregar más productos. Límite alcanzado.");
            return;
        }
        
        System.out.println("\n================================");
        System.out.println("       NUEVO PRODUCTO          ");
        System.out.println("================================");
        
        System.out.print("Código del producto: ");
        codigosProductos[totalProductos] = entrada.nextLine();
        
        System.out.print("Nombre del producto: ");
        nombresProductos[totalProductos] = entrada.nextLine();
        
        System.out.print("Precio: $");
        preciosProductos[totalProductos] = leerDecimal();
        
        System.out.print("Cantidad en stock: ");
        stocksProductos[totalProductos] = leerNumero();
        
        totalProductos++;
        System.out.println("\n¡Producto agregado con éxito!");
    }
    
    static void mostrarProductos() {
        if (totalProductos == 0) {
            System.out.println("\nNo hay productos registrados.");
            return;
        }
        
        System.out.println("\n==================================================");
        System.out.println("               LISTA DE PRODUCTOS               ");
        System.out.println("==================================================");
        System.out.printf("%-10s %-20s %-10s %-10s\n", "CÓDIGO", "NOMBRE", "PRECIO", "STOCK");
        System.out.println("--------------------------------------------------");
        
        for (int i = 0; i < totalProductos; i++) {
            System.out.printf("%-10s %-20s $%-9.2f %-10d\n", 
                codigosProductos[i], 
                nombresProductos[i], 
                preciosProductos[i], 
                stocksProductos[i]);
        }
        
        System.out.println("==================================================");
    }
    
    // ==============================================
    // FUNCIONES PARA GESTIÓN DE CLIENTES
    // ==============================================
    static void gestionClientes() {
        int opcion;
        
        do {
            System.out.println("\n================================");
            System.out.println("      GESTIÓN DE CLIENTES      ");
            System.out.println("================================");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Ver Clientes");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerNumero();
            
            switch(opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    mostrarClientes();
                    break;
                case 3:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }
    
    static void registrarCliente() {
        if (totalClientes >= MAX_CLIENTES) {
            System.out.println("\nNo se pueden registrar más clientes. Límite alcanzado.");
            return;
        }
        
        System.out.println("\n================================");
        System.out.println("      REGISTRAR CLIENTE       ");
        System.out.println("================================");
        
        System.out.print("DNI: ");
        dniClientes[totalClientes] = entrada.nextLine();
        
        System.out.print("Nombres: ");
        nombresClientes[totalClientes] = entrada.nextLine();
        
        System.out.print("Apellidos: ");
        apellidosClientes[totalClientes] = entrada.nextLine();
        
        System.out.print("Teléfono: ");
        telefonosClientes[totalClientes] = entrada.nextLine();
        
        System.out.print("Dirección: ");
        direccionesClientes[totalClientes] = entrada.nextLine();
        
        totalClientes++;
        System.out.println("\n¡Cliente registrado con éxito!");
    }
    
    static void mostrarClientes() {
        if (totalClientes == 0) {
            System.out.println("\nNo hay clientes registrados.");
            return;
        }
        
        System.out.println("\n=================================================================================");
        System.out.println("                            LISTA DE CLIENTES                            ");
        System.out.println("=================================================================================");
        System.out.printf("%-10s %-20s %-20s %-15s %-20s\n", "DNI", "NOMBRES", "APELLIDOS", "TELÉFONO", "DIRECCIÓN");
        System.out.println("---------------------------------------------------------------------------------");
        
        for (int i = 0; i < totalClientes; i++) {
            System.out.printf("%-10s %-20s %-20s %-15s %-20s\n", 
                dniClientes[i], 
                nombresClientes[i], 
                apellidosClientes[i], 
                telefonosClientes[i], 
                direccionesClientes[i]);
        }
        
        System.out.println("=================================================================================");
    }
    
    // ==============================================
    // FUNCIONES PARA REALIZAR VENTAS
    // ==============================================
    static void realizarVenta() {
        if (totalProductos == 0 || totalClientes == 0) {
            System.out.println("\nNo hay suficientes datos para realizar una venta.");
            System.out.println("Necesita tener al menos 1 producto y 1 cliente registrado.");
            return;
        }
        
        System.out.println("\n================================");
        System.out.println("         NUEVA VENTA          ");
        System.out.println("================================");
        
        // Mostrar clientes para seleccionar
        mostrarClientes();
        System.out.print("\nIngrese el DNI del cliente: ");
        String dniCliente = entrada.nextLine();
        
        // Verificar si el cliente existe
        int indiceCliente = buscarCliente(dniCliente);
        if (indiceCliente == -1) {
            System.out.println("\nCliente no encontrado. Regístrelo primero.");
            return;
        }
        
        // Variables para la venta
        StringBuilder productosVenta = new StringBuilder();
        double totalVenta = 0;
        boolean continuar = true;
        
        while (continuar) {
            // Mostrar productos disponibles
            mostrarProductos();
            
            System.out.print("\nIngrese el código del producto: ");
            String codigoProducto = entrada.nextLine();
            
            // Buscar producto
            int indiceProducto = buscarProducto(codigoProducto);
            if (indiceProducto == -1) {
                System.out.println("\nProducto no encontrado.");
                continue;
            }
            
            System.out.print("Ingrese la cantidad a vender: ");
            int cantidad = leerNumero();
            
            // Verificar stock
            if (cantidad > stocksProductos[indiceProducto]) {
                System.out.println("\nNo hay suficiente stock. Stock disponible: " + stocksProductos[indiceProducto]);
                continue;
            }
            
            // Actualizar stock
            stocksProductos[indiceProducto] -= cantidad;
            
            // Calcular subtotal
            double subtotal = preciosProductos[indiceProducto] * cantidad;
            totalVenta += subtotal;
            
            // Agregar producto a la venta
            productosVenta.append(String.format("%s x%d ($%.2f) | ", 
                nombresProductos[indiceProducto], 
                cantidad, 
                subtotal));
            
            System.out.print("\n¿Desea agregar otro producto? (s/n): ");
            String respuesta = entrada.nextLine();
            continuar = respuesta.equalsIgnoreCase("s");
        }
        
        // Guardar la venta
        ventaDNICliente[totalVentas] = dniCliente;
        ventaProductos[totalVentas] = productosVenta.toString();
        ventaTotales[totalVentas] = totalVenta;
        totalVentas++;
        
        // Mostrar factura
        System.out.println("\n================================");
        System.out.println("          FACTURA              ");
        System.out.println("================================");
        System.out.println("Cliente: " + nombresClientes[indiceCliente] + " " + apellidosClientes[indiceCliente]);
        System.out.println("DNI: " + dniCliente);
        System.out.println("\nPRODUCTOS:");
        System.out.println(productosVenta.toString());
        System.out.println("\nTOTAL A PAGAR: $" + String.format("%.2f", totalVenta));
        System.out.println("\n¡Venta registrada con éxito!");
    }
    
    // ==============================================
    // FUNCIONES PARA REPORTES
    // ==============================================
    static void verReportes() {
        System.out.println("\n================================");
        System.out.println("          REPORTES             ");
        System.out.println("================================");
        System.out.println("1. Ventas realizadas");
        System.out.println("2. Productos con bajo stock (menos de 5 unidades)");
        System.out.println("3. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        
        int opcion = leerNumero();
        
        switch(opcion) {
            case 1:
                mostrarVentas();
                break;
            case 2:
                mostrarBajoStock();
                break;
            case 3:
                System.out.println("\nVolviendo al menú principal...");
                break;
            default:
                System.out.println("\nOpción no válida.");
        }
    }
    
    static void mostrarVentas() {
        if (totalVentas == 0) {
            System.out.println("\nNo hay ventas registradas.");
            return;
        }
        
        System.out.println("\n==================================================================================================");
        System.out.println("                                  HISTORIAL DE VENTAS                                   ");
        System.out.println("==================================================================================================");
        System.out.printf("%-20s %-50s %-10s\n", "CLIENTE", "PRODUCTOS", "TOTAL");
        System.out.println("--------------------------------------------------------------------------------------------------");
        
        for (int i = 0; i < totalVentas; i++) {
            int indiceCliente = buscarCliente(ventaDNICliente[i]);
            String nombreCliente = nombresClientes[indiceCliente] + " " + apellidosClientes[indiceCliente];
            
            // Limitar la longitud del texto de productos para que no desborde
            String productos = ventaProductos[i].length() > 50 ? 
                              ventaProductos[i].substring(0, 47) + "..." : 
                              ventaProductos[i];
            
            System.out.printf("%-20s %-50s $%-10.2f\n", 
                nombreCliente, 
                productos, 
                ventaTotales[i]);
        }
        
        System.out.println("==================================================================================================");
        
        // Calcular y mostrar el total de todas las ventas
        double totalGeneral = 0;
        for (double total : ventaTotales) {
            totalGeneral += total;
        }
        System.out.printf("\nTOTAL GENERAL DE VENTAS: $%.2f\n", totalGeneral);
    }
    
    static void mostrarBajoStock() {
        System.out.println("\n================================================");
        System.out.println("       PRODUCTOS CON STOCK BAJO (<5 UNIDADES)  ");
        System.out.println("================================================");
        System.out.printf("%-10s %-20s %-10s\n", "CÓDIGO", "NOMBRE", "STOCK");
        System.out.println("------------------------------------------------");
        
        boolean hayBajoStock = false;
        
        for (int i = 0; i < totalProductos; i++) {
            if (stocksProductos[i] < 5) {
                System.out.printf("%-10s %-20s %-10d\n", 
                    codigosProductos[i], 
                    nombresProductos[i], 
                    stocksProductos[i]);
                hayBajoStock = true;
            }
        }
        
        if (!hayBajoStock) {
            System.out.println("No hay productos con stock bajo.");
        }
        
        System.out.println("================================================");
    }
    
    // ==============================================
    // FUNCIONES AUXILIARES
    // ==============================================
    static int buscarCliente(String dni) {
        for (int i = 0; i < totalClientes; i++) {
            if (dniClientes[i].equals(dni)) {
                return i;
            }
        }
        return -1; // No encontrado
    }
    
    static int buscarProducto(String codigo) {
        for (int i = 0; i < totalProductos; i++) {
            if (codigosProductos[i].equals(codigo)) {
                return i;
            }
        }
        return -1; // No encontrado
    }
    
    static int leerNumero() {
        while (true) {
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }
    
    static double leerDecimal() {
        while (true) {
            try {
                return Double.parseDouble(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número decimal: ");
            }
        }
    }
}