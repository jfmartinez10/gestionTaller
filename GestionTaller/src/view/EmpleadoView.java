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

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public void mostrarMenuEmpleado() {
        
        int opcion;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("   ** Panel de Empleados **");
            System.out.println("=".repeat(35));
            System.out.println("   1. [INSERTAR] Nuevo Empleado");
            System.out.println("   2. [MODIFICAR] Datos de Empleado");
            System.out.println("   3. [MOSTRAR] Lista de Empleados");
            System.out.println("   4. [BUSCAR] Empleado por ID");
            System.out.println("   5. [BUSCAR] Cliente por DNI");
            System.out.println("   6. [ELIMINAR] Empleado");
            System.out.println("   7. [MENÚ] de Proveedores");
            System.out.println("   8. [SALIR] del Panel");
            System.out.print("   Seleccione una opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("   " + RED + "Error:" + RESET + " Opción no válida. Ingrese un número.");
                sc.nextLine(); 
                opcion = 0; 
            }
            System.out.println("-".repeat(35)); 

            switch (opcion) {
                case 1 -> insertarEmpleado();
                case 2 -> modificarEmpleado();
                case 3 -> mostrarEmpleados();
                case 4 -> buscarEmpleadoPorId();
                case 5 -> buscarClientePorDni();
                case 6 -> eliminarEmpleado();
                case 7 -> proveedoresView.mostrarMenuProveedor();
                default -> {
                    System.out.println("   Opción no válida.");
                }
            }

        } while (opcion != 8);
        System.out.println("Saliendo del panel de empleados...");

    }

    public void insertarEmpleado() {
        System.out.println("\n" + "+".repeat(35));
        System.out.println("   ** Insertar Nuevo Empleado **");
        System.out.println("+".repeat(35));

        try {
            System.out.print("   Ingrese el nombre del empleado: ");
            String nombre = sc.nextLine();
            System.out.print("   Ingrese el apellido del empleado: ");
            String apellido = sc.nextLine();
            System.out.print("   Ingrese el ID del empleado: ");
            int idEmpleado = sc.nextInt();
            System.out.print("   Ingrese el teléfono del empleado: ");
            int telefono = sc.nextInt();
            sc.nextLine(); // Consume la nueva línea

            EmpleadosModel empleado = new EmpleadosModel(idEmpleado, nombre, apellido, telefono);
            empleadoDAO.insertarEmpleado(empleado);
            System.out.println("\n   " + GREEN + "Empleado insertado correctamente." + RESET);

        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " Al ingresar datos. Asegúrese de que el ID y el teléfono sean números.");
            sc.nextLine(); // Limpiar el buffer del scanner
        }
        System.out.println("-".repeat(35));
    }

    public void modificarEmpleado() {
        System.out.println("\n" + "*".repeat(37));
        System.out.println("   ** Modificar Datos de Empleado **");
        System.out.println("*".repeat(37));

        System.out.print("   Ingrese el ID del empleado a modificar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); 

            EmpleadosModel empleado = empleadoDAO.getIdEmpleado(id);
            if (empleado == null) {
                System.out.println("   " + RED + "Error:" + RESET + " Empleado no encontrado con el ID: " + id + ".");
                return;
            }

            int opcion;
            do {
                System.out.println("\n   --- ¿Qué desea modificar del empleado con ID " + id + "? ---");
                System.out.println("   1. Nombre");
                System.out.println("   2. Apellido");
                System.out.println("   3. Teléfono");
                System.out.println("   4. [SALIR] del menú de modificación");
                System.out.print("   Ingrese una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); 
                System.out.println("-".repeat(35));

                switch (opcion) {
                    case 1 -> {
                        System.out.print("   Introducir nuevo nombre: ");
                        String nombre = sc.nextLine();
                        empleadoDAO.modificarNombreEmpleado(nombre, id);
                        System.out.println("   " + GREEN + "Nombre modificado correctamente." + RESET);
                    }

                    case 2 -> {
                        System.out.print("   Introducir nuevo apellido: ");
                        String apellido = sc.nextLine();
                        empleadoDAO.modificarApellidoEmpleado(apellido, id);
                        System.out.println("   " + GREEN + "Apellido modificado correctamente." + RESET);
                    }

                    case 3 -> {
                        System.out.print("   Introducir nuevo teléfono: ");
                        try {
                            int telefono = sc.nextInt();
                            sc.nextLine(); // Consume la nueva línea
                            empleadoDAO.modificarTlfEmpleado(telefono, id);
                            System.out.println("   " + GREEN + "Teléfono modificado correctamente." + RESET);
                        } catch (InputMismatchException e) {
                            System.out.println("   " + RED + "Error:" + RESET + " Teléfono debe ser un número.");
                            sc.nextLine(); // Limpiar el buffer
                        }
                    }
                    default -> System.out.println("   Opción no válida.");
                }
            } while (opcion != 4);
            System.out.println("Saliendo del menú de modificación de empleado...");

        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " ID de empleado no válido. Debe ser un número.");
            sc.nextLine(); 
        }
        System.out.println("-".repeat(35));
    }

    public void mostrarEmpleados() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Lista de Empleados **");
        System.out.println("-".repeat(35));

        ArrayList<EmpleadosModel> empleados = empleadoDAO.listarEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("   No hay empleados registrados.");
        } else {
            for (EmpleadosModel empleado : empleados) {
                System.out.println("   " + empleado);
                System.out.println("   " + "-".repeat(70));
            }
        }
        System.out.println("-".repeat(35));
    }

    public void buscarEmpleadoPorId() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Buscar Empleado por ID **");
        System.out.println("-".repeat(35));

        try {
            System.out.print("   Ingrese el ID del empleado a buscar: ");
            int id = sc.nextInt();
            sc.nextLine(); 

            EmpleadosModel empleado = empleadoDAO.getIdEmpleado(id);
            if (empleado == null) {
                System.out.println("   " + RED + "Error:" + RESET + " Empleado no encontrado con el ID: " + id + ".");
            } else {
                System.out.println("   Empleado encontrado:");
                System.out.println("   " + empleado);
            }

        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " ID no válido. Ingrese un número.");
            sc.nextLine(); 
        }
        System.out.println("-".repeat(35));
    }

    public void buscarClientePorDni() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Buscar Cliente por DNI **");
        System.out.println("-".repeat(35));

        System.out.print("   Ingrese el DNI del cliente a buscar: ");
        String dni = sc.nextLine();

        ClienteModel cliente = clienteDAO.getClienteDNI(dni);
        if (cliente == null) {
            System.out.println("   " + RED + "Error:" + RESET + " Cliente no encontrado con el DNI: " + dni + ".");
        } else {
            System.out.println("   Cliente encontrado:");
            System.out.println("   " + cliente);
        }
        System.out.println("-".repeat(35));
    }

    public void eliminarEmpleado() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Eliminar Empleado **");
        System.out.println("-".repeat(35));

        try {
            System.out.print("   Ingrese el ID del empleado a eliminar: ");
            int idEmpleado = sc.nextInt();
            sc.nextLine(); 

            EmpleadosModel empleado = empleadoDAO.getIdEmpleado(idEmpleado);
            if (empleado == null) {
                System.out.println("   " + RED + "Error:" + RESET + " Empleado no encontrado con el ID: " + idEmpleado + ".");
            } else {
                System.out.print("   ¿Está seguro de que desea eliminar a este empleado? (s/n): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("s")) {
                    empleadoDAO.eliminarEmpleado(idEmpleado);
                    System.out.println("   " + GREEN + "Empleado eliminado correctamente." + RESET);
                } else {
                    System.out.println("   Eliminación cancelada.");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " ID no válido. Ingrese un número.");
            sc.nextLine(); 
        }
        System.out.println("-".repeat(35));
    }

    public EmpleadosModel getIdEmpleado(int idEmpleado) {
        return empleadoDAO.getIdEmpleado(idEmpleado);
    }
}