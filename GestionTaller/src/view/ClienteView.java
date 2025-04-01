package view;

import java.util.*;

public class ClienteView {
    public void mostrarMenuCliente() {
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
}
