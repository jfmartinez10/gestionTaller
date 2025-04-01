import dao.ConexionBD;
import java.sql.Connection;
import java.util.Scanner;
import view.EmpleadoView;
import view.TallerView;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conexion = ConexionBD.conectar();

        if (conexion != null) {
            System.out.println("Conexión establecida correctamente.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;
        int subopcion;
        String contraseñaCorrecta = "MecanicosZGZ";
        TallerView taller = new TallerView();
        EmpleadoView empleadoView = new EmpleadoView();

        do {
            System.out.println("¡Bienvenido al mejor taller de Zaragoza!");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    do {
                        System.out.println("1. Iniciar sesion");
                        System.out.println("2. Crear usuario");
                        System.out.println("3. Salir");
                        System.out.println("Introduce una opción: ");
                        subopcion = scanner.nextInt();
                        scanner.nextLine();

                        switch (subopcion) {
                            case 1 -> taller.iniciarSesion();
                            case 2 -> taller.crearCuenta();
                        }
                    }while(subopcion !=3);
                    System.out.println("Has salido.");
                }

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



