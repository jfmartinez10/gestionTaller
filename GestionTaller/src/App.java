import dao.ConexionBD;
import java.sql.Connection;
import java.util.Scanner;
import view.ClienteView;
import view.EmpleadoView;

public class App {
    // Códigos de escape ANSI para colores
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) throws Exception {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        System.out.println("\n" + "*".repeat(53));
        System.out.println("  ** ¡Bienvenido al Taller Mecánico de Zaragoza! **");
        System.out.println("*".repeat(53));

        if (conexion != null) {
            System.out.println("  " + GREEN + "[OK] Conexión a la base de datos establecida." + RESET);
        } else {
            System.out.println("  " + RED + "Error:" + RESET + " No se pudo conectar a la base de datos.");
            return; // Si no hay conexión, no podemos continuar
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;
        String contrasenaCorrecta = "MecanicosZGZ";
        EmpleadoView empleadoView = new EmpleadoView();
        ClienteView clienteView = new ClienteView(empleadoView);

        do {
            System.out.println("\n" + "-".repeat(53));
            System.out.println("  ¿Qué tipo de usuario eres?");
            System.out.println("  1. [CLIENTE]");
            System.out.println("  2. [EMPLEADO]");
            System.out.println("  3. [SALIR]");
            System.out.print("  Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            System.out.println("-".repeat(53)); // Separador

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n  Accediendo como Cliente...");
                    clienteView.mostrarMenuCliente();
                }

                case 2 -> {
                    System.out.println("\n  Accediendo como Empleado...");
                    System.out.print("  [!] Introduzca la contraseña de empleado: ");
                    String contrasenaIngresada = scanner.nextLine();
                    if (contrasenaIngresada.equals(contrasenaCorrecta)) {
                        System.out.println("  [+] Contraseña correcta. Accediendo al menú de empleados.");
                        empleadoView.mostrarMenuEmpleado();
                    } else {
                        System.out.println("  " + RED + "Error:" + RESET + " Contraseña incorrecta. Inténtelo de nuevo.");
                    }
                }
                case 3 -> System.out.println("  => ¡Gracias por visitar nuestro taller! ¡Hasta pronto! <=");
                default -> System.out.println("  " + RED + "Opción inválida." + RESET + " Por favor, seleccione una opción válida.");
            }

        } while (opcion != 3);

        System.out.println("Fin del programa.");
        if (conexion != null) {
            conexion.close(); // Cierra la conexión al salir
            System.out.println("  [CLOSED] Conexión a la base de datos cerrada.");
        }
    }
}