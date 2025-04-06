package view;

import dao.ClienteDAO;
import java.util.*;
import model.ClienteModel;

public class ClienteView {

    private Scanner sc = new Scanner(System.in);
    private CitasView citasView;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public ClienteView(EmpleadoView empleadoView) {
        this.citasView = new CitasView(this, empleadoView);
    }

    public void mostrarMenuCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        VehiculosView vehiculosView = new VehiculosView(this);

        do {
            System.out.println("\n" + "*".repeat(35));
            System.out.println("  ** Portal del Cliente **");
            System.out.println("*".repeat(35));
            System.out.println("  1. [REGISTRAR] Datos personales");
            System.out.println("  2. [MODIFICAR] Datos personales");
            System.out.println("  3. [ELIMINAR] Cuenta de cliente");
            System.out.println("  4. [AnADIR] Un vehículo");
            System.out.println("  5. [MODIFICAR] Un vehículo");
            System.out.println("  6. [MOSTRAR] Mis vehículos");
            System.out.println("  7. [ELIMINAR] Un vehículo");
            System.out.println("  8. [GESTIONAR] Mis citas");
            System.out.println("  9. [SALIR] Del portal");
            System.out.print("  Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            System.out.println("-".repeat(35)); 

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> modificarCliente();
                case 3 -> eliminarCliente();
                case 4 -> vehiculosView.agregarVehiculos();
                case 5 -> vehiculosView.modificarVehiculos();
                case 6 -> vehiculosView.mostrarVehiculos();
                case 7 -> vehiculosView.eliminarVehiculos();
                case 8 -> citasView.menuCitasCliente();
                default -> System.out.println(RED + "Opción no válida. Intente nuevamente." + RESET);
            }

        } while (opcion != 9);
        System.out.println("Saliendo del portal del cliente. ¡Hasta pronto!");
        System.out.println("¡Hasta pronto!");
        System.out.println("-".repeat(35)); 
    }

    public void registrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "+".repeat(35));
        System.out.println("  ** Registrar Nuevo Cliente **");
        System.out.println("+".repeat(35));

        System.out.println("  Introduce el DNI: ");
        String dni = scanner.nextLine();

        System.out.println("  Introduce el nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("  Introduce el apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("  Introduce el teléfono: ");
        int telefono = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("  Introduce el email: ");
        String email = scanner.nextLine();

        ClienteModel cliente = new ClienteModel(dni, nombre, apellido, telefono, email);
        ClienteDAO clientedb = new ClienteDAO();
        clientedb.anadirCliente(cliente);

        System.out.println("\n  " + GREEN + "¡Cliente registrado correctamente!" + RESET);
        System.out.println("-".repeat(35));
    }

    public ClienteModel getClienteDni() {
        System.out.println("\n  [BUSCAR] Introduce el DNI del cliente: ");
        String dni = sc.nextLine();
        ClienteModel cliente = clienteDAO.getClienteDni(dni);
        if (cliente == null) {
            System.out.println("  " + RED + "Error:" + RESET + " No se encontró ningún cliente con ese DNI.");
        }
        return cliente;
    }

    public void modificarCliente() {
        System.out.println("\n" + "=".repeat(36));
        System.out.println("  ** Modificar Datos del Cliente **");
        System.out.println("=".repeat(36));

        ClienteModel cliente = this.getClienteDni();
        if (cliente == null) {
            return; 
        }

        String dniOriginal = cliente.getDni();
        int opcion;
        do {
            System.out.println("\n  --- ¿Qué dato desea modificar? ---");
            System.out.println("  1. Nombre");
            System.out.println("  2. Apellido");
            System.out.println("  3. Teléfono");
            System.out.println("  4. Email");
            System.out.println("  5. DNI");
            System.out.println("  6. [VOLVER] Al menú anterior");
            System.out.print("  Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 
            System.out.println("-".repeat(35));

            switch (opcion) {
                case 1 -> {
                    System.out.println("  Introduce el nuevo nombre: ");
                    String nombre = sc.nextLine();
                    clienteDAO.actualizarNombreCliente(dniOriginal, nombre);
                    System.out.println("  " + GREEN + "Nombre modificado correctamente." + RESET);
                }

                case 2 -> {
                    System.out.println("  Introduce el nuevo apellido: ");
                    String apellido = sc.nextLine();
                    clienteDAO.actualizarApellidoCliente(dniOriginal, apellido);
                    System.out.println("  " + GREEN + "Apellido modificado correctamente." + RESET);
                }

                case 3 -> {
                    int telefono;
                    do {
                        System.out.println("  Introduce el nuevo teléfono: ");
                        telefono = sc.nextInt();
                        sc.nextLine(); 
                        if (clienteDAO.existeTelefono(telefono) && cliente.getTelefono() != telefono) {
                            System.out.println("  " + RED + "Error:" + RESET + " El teléfono ya está registrado. Intente con otro.");
                        } else {
                            break; 
                        }

                    } while (true);
                    clienteDAO.actualizarTlfCliente(dniOriginal, telefono);
                    System.out.println("  " + GREEN + "Teléfono modificado correctamente." + RESET);
                }

                case 4 -> {
                    String email;
                    do {
                        System.out.println("  Introduce el nuevo email: ");
                        email = sc.nextLine();
                        if (clienteDAO.existeEmail(email) && !email.equals(cliente.getEmail())) {
                            System.out.println("  " + RED + "Error:" + RESET + " El email ya está registrado. Intente con otro.");
                        } else {
                            break; 
                        }

                    } while (true);
                    clienteDAO.actualizarEmailCliente(dniOriginal, email);
                    System.out.println("  " + GREEN + "Email modificado correctamente." + RESET);
                }

                case 5 -> {
                    String nuevoDni;
                    do {
                        System.out.println("  Introduce el nuevo DNI: ");
                        nuevoDni = sc.nextLine();
                        if (clienteDAO.existeDni(nuevoDni) && !nuevoDni.equals(dniOriginal)) {
                            System.out.println("  " + RED + "Error:" + RESET + " El DNI ya está registrado. Intente con otro.");
                        } else {
                            break; 
                        }

                    } while (true);
                    clienteDAO.actualizarDniCliente(dniOriginal, nuevoDni);
                    System.out.println("  " + GREEN + "DNI modificado correctamente." + RESET);
                    dniOriginal = nuevoDni;
                }
                default -> System.out.println(RED + "Opción no válida. Intente nuevamente." + RESET);
            }
        } while (opcion != 6);
        System.out.println("Volviendo al menú anterior...");
        System.out.println("-".repeat(35));
    }

    public void eliminarCliente() {
        System.out.println("\n" + "!".repeat(35));
        System.out.println("  ** Eliminar Cuenta de Cliente **");
        System.out.println("!".repeat(35));

        ClienteModel cliente = this.getClienteDni();
        if (cliente == null) {
            return; 
        }

        System.out.println("\n  ¿Está seguro de que desea eliminar su cuenta? (S/N): ");
        String confirmacion = sc.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            clienteDAO.eliminarCliente(cliente);
            if (clienteDAO.existeDni(cliente.getDni())) {
                System.out.println("  " + RED + "Error al eliminar el cliente. Por favor, intente nuevamente." + RESET);
            } else {
                System.out.println("  " + GREEN + "Cliente eliminado correctamente. ¡Esperamos verte pronto!" + RESET);
            }
        } else {
            System.out.println("  Operación de eliminación cancelada.");
        }
        System.out.println("-".repeat(33));
    }
}