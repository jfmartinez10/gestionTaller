package view;

import java.util.*;
import model.ClienteModel; // Importa ClienteModel

public class EmpleadoView {
    private Scanner sc = new Scanner(System.in);

    public void mostrarMenuEmpleado() {
        int opcion;

        do {
            System.out.println("¡Bienvenido al menú de empleados!");
            System.out.println("1. Modificar Cliente"); // Corregido el número de opción
            System.out.println("2. Eliminar Cliente"); // Corregido el número de opción
            System.out.println("5. Eliminar Vehículos de Cliente");
            System.out.println("6. Mostrar Órdenes de Trabajo");
            System.out.println("8. Eliminar Órdenes de Trabajo");
            System.out.println("9. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Consume la nueva línea después de nextInt()

            switch (opcion) {
                case 1 -> modificarCliente();
                case 2 -> eliminarCliente();
                case 5 -> eliminarVehiculoCliente();
                case 6 -> mostrarOrdenesTrabajo();
                case 8 -> eliminarOrdenesTrabajo();
            }
        } while (opcion != 9);
    }

    public void modificarCliente() {
        ClienteModel cliente = this.getDNI();
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        int opcion;
        do {
            System.out.println("Modificar cliente");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar telefono");
            System.out.println("4. Modificar email");
            System.out.println("5. Modificar dni");
            System.out.println("6. Volver al menu anterior");
            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Introduce el nombre: ");
                    String nombre = sc.nextLine();
                    // clienteDAO.actualizarNombreCliente(cliente.getDNI(), nombre);
                    System.out.println("Nombre modificado correctamente");
                }
                case 2 -> {
                    System.out.print("Introduce el apellido: ");
                    String apellido = sc.nextLine();
                    // clienteDAO.actualizarApellidoCliente(cliente.getDNI(), apellido);
                    System.out.println("Apellido modificado correctamente");
                }
                case 3 -> {
                    System.out.print("Introduce el telefono: ");
                    int telefono = sc.nextInt();
                    sc.nextLine(); 
                    // clienteDAO.actualizarTlfCliente(cliente.getDNI(), telefono);
                    System.out.println("Telefono modificado correctamente");
                }
                case 4 -> {
                    System.out.print("Introduce el email: ");
                    String email = sc.nextLine();
                    // clienteDAO.actualizarEmailCliente(cliente.getDNI(), email);
                    System.out.println("Email modificado correctamente");
                }
                case 5 -> {
                    System.out.print("Introduce el dni: ");
                    String dni = sc.nextLine();
                    // clienteDAO.actualizarDniCliente(dni);
                    System.out.println("Dni modificado correctamente");
                }
            }
            
        } while (opcion != 6);
        System.out.println("Volviendo al menu anterior");
    }

    public void eliminarCliente() {
    }

    public void eliminarVehiculoCliente() {
    }

    public void mostrarOrdenesTrabajo() {
    }

    public void eliminarOrdenesTrabajo() {
    }
}