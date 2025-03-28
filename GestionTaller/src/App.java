import java.sql.Connection;
import java.util.Scanner;

import dao.ConexionBD;
import view.Taller;
public class App {
    public static void main(String[] args) throws Exception{
        Connection conexion = ConexionBD.conectar();
        
            if (conexion != null) {
                System.out.println("Conexión establecida correctamente.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        Scanner scanner = new Scanner(System.in);
        int opcion;
        String contraseñaCorrecta = "pene";
        Taller taller = new Taller();

        do {
            System.out.println("¡Bienvenido al mejor taller de Zaragoza!");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
        }while(opcion !=3);
        System.out.println("Has salido.");

        try {
            switch (opcion) {
                case 1 -> {
                    System.out.println("1. Iniciar sesion");
                    System.out.println("2. Crear usuario");
                    System.out.println("3. Salir");
                    System.out.println("Introduce una opción: ");
                    int subopcion = scanner.nextInt();

                        switch (subopcion) {
                            case 1 -> taller.iniciarSesion();
                            case 2 -> taller.crearCuenta();
                        }
                    }

                case 2 -> {
                    System.out.print("Introduzca contraseña necesaria: ");
                    String contraseñaIngresada = scanner.nextLine();
                    if (contraseñaIngresada.equals(contraseñaCorrecta)) {
                        taller.mostrarMenuEmpleado();
                    } else {
                        System.out.println("Contraseña incorrecta. Inténtalo de nuevo.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mEntrada inválida. Por favor, ingresa un número.\u001B[0m");
            //Método main
        }
    }
}

