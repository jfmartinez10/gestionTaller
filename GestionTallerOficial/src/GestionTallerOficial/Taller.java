import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taller {

    // Atributos taller
    private List<Cliente> clientes = new ArrayList<>();
    private String nombreCliente;
    private Scanner scanner = new Scanner(System.in);

    public void iniciarSesion() {
        // Método para iniciar sesión de un usuario existente

        System.out.print("Introduce tu nombre: ");
        String cliente = scanner.nextLine(); 
        System.out.print("Introduce tu contraseña: ");
        String contraseña = scanner.nextLine(); 

        for (Cliente u : clientes) {
            if (u.getNombreCliente().equals(cliente) && u.getContraseña().equals(contraseña)) {
                nombreCliente = cliente; 
                // Aquí iría el método para mostrar el menú del cliente
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + nombreCliente + "!");
                return; 
            }
        }

        System.out.println("\u001B[31mCredenciales incorrectas. Por favor, intenta de nuevo o crea una cuenta.\u001B[0m");
        // Aquí iría el método para mostrar el menú del cliente
    }

    public void crearCuenta() {
        // Método para crear una nueva cuenta de usuario

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
        // Aquí iría el método para mostrar el menú del cliente
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

        } while (opcion != 10);
    }
}