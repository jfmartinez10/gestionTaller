package view;

import java.util.*;

public class EmpleadoView {
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
}
