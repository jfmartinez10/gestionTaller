import java.sql.Connection;
import java.util.Scanner;
public class App {
    public static void main(String[] args){
        Connection conexion = ConexionBD.conectar();
        
            if (conexion != null) {
                System.out.println("Conexión establecida correctamente.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        Scanner scanner = new Scanner(System.in);
        int opcion;
 
        do {
            System.out.println("\nMenú del Taller");
            System.out.println(" Seleciona tu rol");
            System.out.println("1. Cliente");
            System.out.println("2. Trabajador");
            System.out.println("3. Salir");
            opcion = scanner.nextInt();
        switch (opcion){
                case 1 -> {
                    do { 
                        System.out.println("\nBienvenido Cliente, que queires hacer");
                        System.out.println("1. Registrar Cliente");
                        System.out.println("2. Eliminar Cliente");
                        System.out.println("3. Registrar Vehiculo");
                        System.out.println("4. Eliminar Vehiculo");
                        System.out.println("5. Registrar Problema");
                        
                        switch (opcion){
                            case 1 -> {} //registrarCliente
                            case 2 -> {} //eliminarCliente
                            case 3 -> {} //registrarVehiculo
                            case 4 -> {} //eliminarVehiculo
                            case 5 -> {} //registrarProblema
                        }

                    } while (opcion != 5);
                }
                case 2 -> {
                    do { 
                        System.out.println("\nBienvenido Trabajador, que quieres hacer");
                        System.out.println("1. Fichar");
                        System.out.println("2. Ver trabajos");

                        switch (opcion){
                            case 1 -> {} //fichar
                            case 2 -> {} //verTrabajos
                        }
                    } while (opcion != 2);
                }
                case 3 -> {
                    System.out.println("Has salido, gracias por escogernos una vez mas.");
                }
            }
        } while (opcion != 3);
            System.out.println("Numero no valido, prueba otra vez.");
    }
}

