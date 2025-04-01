package view;

import java.util.*;

public class EmpleadoView {
    public void mostrarMenuEmpleado() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("¡Bienvenido al menú de empleados!");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Eliminar Cliente");
            System.out.println("3. Mostrar Vehículos de Cliente");
            System.out.println("4. Agregar Vehículos de Cliente");
            System.out.println("5. Eliminar Vehículos de Cliente ");
            System.out.println("6. Mostrar Órdenes de Trabajo");
            System.out.println("7. Agregar Órdenes de Trabajo");
            System.out.println("8. Eliminar Órdenes de Trabajo");
            System.out.println("9. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> {
                } // eliminarCliente()
                case 3 -> {
                } // mostrarVehiculoCliente()
                case 4 -> {
                } // agregarVehiculoCliente()
                case 5 -> {
                } // eliminarVehiculoCliente()
                case 6 -> {
                } // mostrarOrdenesTrabajo()
                case 7 -> {
                } // agregarOrdenesTrabajo()
                case 8 -> {
                } // eliminarOrdenesTrabajo()

            }
        } while (opcion != 9);
    }

    public void agregarCliente() {
        System.out.println("Agregar cliente");
        System.out.println("Introduce el nombre: ");

    }
}
