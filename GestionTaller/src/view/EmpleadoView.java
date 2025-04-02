package view;

import java.util.*;
import model.ClienteModel;
import model.EmpleadosModel;
import dao.ClienteDAO;
import dao.EmpleadoDAO;

public class EmpleadoView {
    private Scanner sc = new Scanner(System.in);
    private ClienteDAO clienteDAO = new ClienteDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public void mostrarMenuEmpleado() {
        int opcion;

        do {
            System.out.println("¡Bienvenido al menú de empleados!");
            System.out.println("1. Modificar Cliente");
            System.out.println("2. Eliminar Cliente");
            System.out.println("5. Eliminar Vehículos de Cliente");
            System.out.println("6. Mostrar Órdenes de Trabajo");
            System.out.println("8. Eliminar Órdenes de Trabajo");
            System.out.println("9. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> modificarCliente();
                case 2 -> eliminarCliente();
                case 5 -> eliminarVehiculoCliente();
                case 6 -> mostrarEmpleados();
                case 8 -> modificarEmpleado();
            }
        } while (opcion != 9);
    }

    public void modificarCliente(ClienteModel cliente) {
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
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Introducir nombre: ");
                    String nombre = sc.nextLine();
                    clienteDAO.modificarNombreCliente(cliente.getDNI(), nombre);
                    System.out.println("Nombre modificado correctamente");
                }
                case 2 -> {
                    System.out.print("Introducir apellido: ");
                    String apellido = sc.nextLine();
                    clienteDAO.actualizarApellidoCliente(cliente.getDNI(), apellido);
                    System.out.println("Apellido modificado correctamente");
                }
                case 3 -> {
                    System.out.print("Introducir teléfono: ");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    clienteDAO.actualizarTlfCliente(cliente.getDNI(), telefono);
                    System.out.println("Telefono modificado correctamente");
                }
<<<<<<< HEAD
=======
                case 4 -> {
                    System.out.print("Introduce el email: ");
                    String email = sc.nextLine();
                    // clienteDAO.actualizarEmailCliente(cliente.getDNI(), email);
                    System.out.println("Email modificado correctamente");
                }
                case 5 -> {
                    System.out.print("Introduce el DNI: ");
                    String DNI = sc.nextLine();
                    // clienteDAO.actualizarDniCliente(dni);
                    System.out.println("DNI modificado correctamente");
                }
>>>>>>> fd9751f845b2ddda4d7c772315c3561f6d0d4b39
            }
            
        } while (opcion != 4);
        System.out.println("Saliendo del menú");
    }

    public void eliminarCliente() {
    }

    public void eliminarVehiculoCliente() {
    }

    public void mostrarEmpleados() {
    }

    public void modificarEmpleado() {
    }

}