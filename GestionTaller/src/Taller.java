import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taller {
    Connection conexion = ConexionBD.conectar();
    
    // Atributos
    private List<Cliente> clientes = new ArrayList<>();
    private String nombreCliente;
    private Scanner scanner = new Scanner(System.in);

    public void iniciarSesion() {

        System.out.print("Introduce tu nombre: ");
        String cliente = scanner.nextLine(); 
        System.out.print("Introduce tu contraseña: ");
        String contraseña = scanner.nextLine();

        for (Cliente u : clientes) {
            if (u.getNombreCliente().equals(cliente) && u.getContraseña().equals(contraseña)) {
                nombreCliente = cliente; 
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + nombreCliente + "!");
                mostrarMenuCliente();
            }
        }

        System.out.println("\u001B[31mCredenciales incorrectas. Por favor, intenta de nuevo o crea una cuenta.\u001B[0m");
        // Aquí iría el método main
    }

    public void crearCuenta() {
        System.out.print("Introduce tu nombre: ");
        String nuevoNombre = scanner.nextLine(); 

        for (Cliente u : clientes) {
            if (u.getNombreCliente().equals(nuevoNombre)) {
                System.out.println("\u001B[31mEl nombre de usuario ya existe. Por favor, elige otro.\u001B[0m");
                crearCuenta();
                return; 
            }
        }

        System.out.print("Introduce tu contraseña: ");
        String nuevaContraseña = scanner.nextLine(); 
        System.out.print("Confirma tu contraseña: ");
        String confirmarContraseña = scanner.nextLine(); 

        if (!nuevaContraseña.equals(confirmarContraseña)) {
            System.out.println("\u001B[31mLas contraseñas no coinciden. Por favor, inténtalo de nuevo.\u001B[0m");
            crearCuenta(); 
            return; 
        }

        System.out.print("Introduce tu saldo inicial: ");
        int saldoInicial = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Cuenta creada con éxito. ¡Bienvenido, " + nuevoNombre + "!");
        mostrarMenuCliente();
    }

    public void mostrarMenuEmpleado() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("¡Bienvenido al menú de empleados!");
            System.out.println("1. Mostrar Clientes");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Mostrar Vehículos de Cliente");
            System.out.println("5. Agregar Vehículos de Cliente");
            System.out.println("6. Eliminar Vehículos de Cliente ");
            System.out.println("7. Mostrar Órdenes de Trabajo");
            System.out.println("8. Agregar Órdenes de Trabajo");
            System.out.println("9. Eliminar Órdenes de Trabajo");
            System.out.println("10. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {} //mostrarClientes()
                case 2 -> {} //agregarCliente()
                case 3 -> {} //eliminarCliente()
                case 4 -> {} //mostrarVehiculoCliente()
                case 5 -> {} //agregarVehiculoCliente()
                case 6 -> {} //eliminarVehiculoCliente()
                case 7 -> {} //mostrarOrdenesTrabajo()
                case 8 -> {} //agregarOrdenesTrabajo()
                case 9 -> {} //eliminarOrdenesTrabajo()

            }
        }while (opcion != 10);
    }

    private void mostrarMenuCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("¡Bienvenido al menú de clientes!");
            System.out.println("1. Mostrar Vehículos");
            System.out.println("2. Agregar Vehículo");
            System.out.println("3. Eliminar Vehículo");
            System.out.println("4. Reportar problema");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {} //mostrarVehiculos()
                case 2 -> {} //agregarVehiculo()
                case 3 -> {} //eliminarVehiculo()
                case 4 -> {} //reportarProblema()
            }

        }while (opcion != 5);
    }

    public void registrarCliente(){

        Scanner scanner = new Scanner(System.in);

        String nombreCliente="";
        int telefono=0;
        String DNI ="";
        String query = "INSERT INTO clientes (nombre, telefono, DNI) VALUES ("+nombreCliente+"," +telefono+","+DNI+")";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
            System.out.println("Escribe tu nombre");
            nombreCliente = scanner.next();
            stmt.setString(1, nombreCliente); // Asigna el valor del
            System.out.println("Escribe tu telefono"); 
            telefono = scanner.nextInt();
            stmt.setInt(2, telefono); // Asigna el valor del 
            System.out.println("Escribe tu DNI");
            DNI = scanner.next();
            stmt.setString(3, DNI); // Asigna el valor del 
            stmt.executeUpdate(); // Ejecuta la consulta de inserción
            System.out.println("Cliente agregado exitosamente.");
                
        } catch (Exception e)  {
                System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

}
