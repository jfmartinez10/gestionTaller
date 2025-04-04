import dao.ConexionBD;
import java.sql.Connection;
import java.util.Scanner;
import view.ClienteView;
import view.EmpleadoView;


public class App {
    public static void main(String[] args) throws Exception {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            System.out.println("Conexión establecida correctamente.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;
        int subopcion;
        String contraseñaCorrecta = "MecanicosZGZ";
        EmpleadoView empleadoView = new EmpleadoView();
        ClienteView clienteView = new ClienteView();

        do {
            System.out.println("¡Bienvenido al mejor taller de Zaragoza!");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> clienteView.mostrarMenuCliente();

                case 2 -> {
                    System.out.print("Introduzca contraseña necesaria: ");
                    String contraseñaIngresada = scanner.nextLine();
                    if (contraseñaIngresada.equals(contraseñaCorrecta)) {
                        empleadoView.mostrarMenuEmpleado();
                    } else {
                        System.out.println("Contraseña incorrecta. Inténtalo de nuevo.");
                    }
                }
                default -> System.out.println("\u001B[31mOpción inválida. Por favor, selecciona una opción válida.\u001B[0m");
            }

        } while (opcion != 3);
        System.out.println("Has salido.");
    }
}



