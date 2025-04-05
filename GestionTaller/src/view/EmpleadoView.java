package view;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import java.util.*;
import model.ClienteModel;
import model.EmpleadosModel;

public class EmpleadoView {
    private Scanner sc = new Scanner(System.in);
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProveedoresView proveedoresView = new ProveedoresView();

    public void mostrarMenuEmpleado() {
        int opcion;

        do {
            System.out.println("---------------------");
            System.out.println("¡Bienvenido al menú de empleados!");
            System.out.println("1. Insertar Empleado");
            System.out.println("2. Modificar Empleado");
            System.out.println("3. Mostrar Empleados");
            System.out.println("4. Buscar Empleado por ID");
            System.out.println("5. Buscar Cliente por ID");
            System.out.println("6. Eliminar Empleado");
            System.out.println("7. Menú Proveedores");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Ingrese un número.");
                sc.nextLine();
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> insertarEmpleado();
                case 2 -> modificarEmpleado();
                case 3 -> mostrarEmpleados();
                case 4 -> buscarEmpleadoPorId();
                case 5 -> buscarClientePorId();
                case 6 -> eliminarEmpleado();
                case 7 -> proveedoresView.mostrarMenuProveedor();
                default -> {
                    System.out.println("Opción no válida.");
                }
            }
        } while (opcion != 8);
        System.out.println("Saliendo del programa.");
    }

    public void insertarEmpleado() {
        try {
            System.out.print("Ingrese el nombre del empleado: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el apellido del empleado: ");
            String apellido = sc.nextLine();
            System.out.print("Ingrese el ID del empleado: ");
            int idEmpleado = sc.nextInt();
            System.out.print("Ingrese el teléfono del empleado: ");
            int telefono = sc.nextInt();
            sc.nextLine();

            EmpleadosModel empleado = new EmpleadosModel(idEmpleado, nombre, apellido, telefono);
            empleadoDAO.insertarEmpleado(empleado);
            System.out.println("Empleado insertado correctamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar datos. Asegúrese de que los datos sean correctos.");
            sc.nextLine();
        }
    }

    public void modificarEmpleado() {
        System.out.print("Ingrese el ID del empleado a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();

        EmpleadosModel empleado = empleadoDAO.getIdEmpleado(id);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        int opcion;
        do {
            System.out.println("Modificar empleado");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar teléfono");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Introducir nombre: ");
                    String nombre = sc.nextLine();
                    empleadoDAO.modificarNombreEmpleado(nombre, id);
                    System.out.println("Nombre modificado correctamente");
                }
                case 2 -> {
                    System.out.print("Introducir apellido: ");
                    String apellido = sc.nextLine();
                    empleadoDAO.modificarApellidoEmpleado(apellido, id);
                    System.out.println("Apellido modificado correctamente");
                }
                case 3 -> {
                    System.out.print("Introducir teléfono: ");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    empleadoDAO.modificarTlfEmpleado(telefono, id);
                    System.out.println("Teléfono modificado correctamente");
                }
            
            }
        } while (opcion != 4);
        System.out.println("Saliendo del menú de modificación");
    }

    public void mostrarEmpleados() {
        ArrayList<EmpleadosModel> empleados = empleadoDAO.listarEmpleados();
        System.out.println("Lista de empleados: ");
        for (EmpleadosModel empleado : empleados) {
            System.out.println(empleado.toString());
        }
    }

    public void buscarEmpleadoPorId() {
        try {
            System.out.print("Ingrese el ID del empleado a buscar: ");
            int id = sc.nextInt();
            sc.nextLine();

            EmpleadosModel empleado = empleadoDAO.getIdEmpleado(id);
            if (empleado == null) {
                System.out.println("Empleado no encontrado.");
            } else {
                System.out.println("Empleado encontrado:");
                System.out.println(empleado);
            }
        } catch (InputMismatchException e) {
            System.out.println("ID no válido. Ingrese un número.");
            sc.nextLine();
        }
    }

    public void buscarClientePorId() {
        System.out.print("Ingrese el DNI del cliente a buscar: ");
        String dni = sc.nextLine();

        ClienteModel cliente = clienteDAO.getClienteDNI(dni);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        } else {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        }
    }

    public void eliminarEmpleado() {
        try {
            System.out.print("Ingrese el ID del empleado a eliminar: ");
            int idEmpleado = sc.nextInt();
            sc.nextLine();

            EmpleadosModel empleado = empleadoDAO.getIdEmpleado(idEmpleado); // Corregido aquí
            if (empleado == null) {
                System.out.println("Empleado no encontrado.");
            } else {
                System.out.print("¿Está seguro de que desea eliminar a este empleado? (s/n): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("s")) {
                    empleadoDAO.eliminarEmpleado(idEmpleado);
                    System.out.println("Empleado eliminado correctamente.");
                } else {
                    System.out.println("Eliminación cancelada.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("ID no válido. Ingrese un número.");
            sc.nextLine();
        }
    }

    public EmpleadosModel getIdEmpleado(int idEmpleado) {
        return empleadoDAO.getIdEmpleado(idEmpleado);
    }
}