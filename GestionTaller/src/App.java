import dao.ConexionBD;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;
import view.ClienteView;
import view.EmpleadoView;

public class App {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) throws Exception {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        System.out.println("\n" + "*".repeat(60));
        System.out.println("   ** ¡Bienvenido al mejor taller mecánico de Zaragoza! **");
        System.out.println("*".repeat(60));

        if (conexion != null) {
            System.out.println("   " + GREEN + "\n [OK] Conexión a la base de datos establecida." + RESET);
        } else {
            System.out.println("   " + RED + "Error:" + RESET + " No se pudo conectar a la base de datos.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int opcion = 0; 
        String contrasenaCorrecta = "MecanicosZGZ";
        EmpleadoView empleadoView = new EmpleadoView();
        ClienteView clienteView = new ClienteView(empleadoView);

        do {
            System.out.println("\n" + "-".repeat(60));
            System.out.println("   ¿Qué tipo de usuario eres?");
            System.out.println("   1. [CLIENTE]");
            System.out.println("   2. [EMPLEADO]");
            System.out.println("   3. [SALIR]");
            System.out.print("   Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("   " + RED + "\n Error:" + RESET + " Por favor, introduce un número como opción.");
                scanner.next(); 
                opcion = -1; 
                continue; 
            }
            scanner.nextLine(); 

            System.out.println("-".repeat(53));

            switch (opcion) {
                case 1 -> {
                    System.out.println(GREEN + "\n   Accediendo como Cliente..." + RESET);
                    clienteView.mostrarMenuCliente();
                }

                case 2 -> {
                    System.out.println(GREEN + "\n   Accediendo como Empleado..." + RESET);
                    System.out.print("\n   [!] Introduzca la contraseña de empleado: ");
                    String contrasenaIngresada = scanner.nextLine();
                    if (contrasenaIngresada.equals(contrasenaCorrecta)) {
                        System.out.println(GREEN + "   [+] Contraseña correcta. Accediendo al menú de empleados." + RESET);
                        empleadoView.mostrarMenuEmpleado();
                    } else {
                        System.out.println("   " + RED + "\n Error:" + RESET + " Contraseña incorrecta. Inténtelo de nuevo.");
                    }
                }
                case 3 -> System.out.println(" ¡Gracias por visitar nuestro taller! ¡Hasta pronto!");
                default -> System.out.println("   " + RED + "Opción inválida." + RESET + " Por favor, seleccione una opción válida.");
            }

        } while (opcion != 3);

        if (conexion != null) {
            conexion.close();
            System.out.println("   "+ GREEN +"\n [CLOSED] Conexión a la base de datos cerrada. \n"+ RESET);
        }
    }
}