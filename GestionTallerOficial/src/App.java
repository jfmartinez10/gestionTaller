
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        String contraseñaCorrecta = "pene";

        do {
            System.out.println("¡Bienbenido al mejor taller de Zaragoza!");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
        }while(opcion !=3);
        
        try {
            switch (opcion) {
                case 1 -> {
                    System.out.println("1. Iniciar sesion");
                    System.out.println("2. Crear usuario");
                    System.out.println("3. Salir");
                    System.out.println("Introduce una opción: ");
                    int subopcion = scanner.nextInt();

                        switch (subopcion) {
                            case 1 -> {}//Método iniciarSesión 
                            case 2 -> {}//Método crearUsuario 
                        }
                    }

                case 2 -> {
                    System.out.print("Introduzca contraseña necesaria: ");
                    String contraseñaIngresada = scanner.nextLine();
                    if (contraseñaIngresada.equals(contraseñaCorrecta)) {
                        //Método mostrarMenuEmpleado
                    } else {
                        System.out.println("Contraseña incorrecta. Inténtalo de nuevo.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            // Captura la excepción si la entrada del usuario no es un número válido
            System.out.println("\u001B[31mEntrada inválida. Por favor, ingresa un número.\u001B[0m");
            //Método main
        }  
    }
}