package view;

import dao.ClienteDAO;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        int opcion = 0; 
        VehiculosView vehiculosView = new VehiculosView(this);

        do {
            System.out.println("\n" + "*".repeat(35));
            System.out.println("   ** Portal del Cliente **");
            System.out.println("*".repeat(35));
            System.out.println("   1. [REGISTRAR] Datos personales");
            System.out.println("   2. [MODIFICAR] Datos personales");
            System.out.println("   3. [ELIMINAR] Cuenta de cliente");
            System.out.println("   4. [AÑADIR] Un vehículo");
            System.out.println("   5. [MODIFICAR] Un vehículo");
            System.out.println("   6. [MOSTRAR] Mis vehículos");
            System.out.println("   7. [ELIMINAR] Un vehículo");
            System.out.println("   8. [GESTIONAR] Mis citas");
            System.out.println("   9. [SALIR] Del portal");
            System.out.print("   Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("   " + RED + "\n Error:" + RESET + " Por favor, introduce un número como opción.");
                scanner.next(); 
                opcion = -1; 
                continue; 
            }
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
            }

        } while (opcion != 9);
        System.out.println("Saliendo del portal del cliente. ¡Hasta pronto!");
        System.out.println("¡Hasta pronto!");
        System.out.println("-".repeat(35));
    }

    public void registrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "+".repeat(35));
        System.out.println("   ** Registrar Nuevo Cliente **");
        System.out.println("+".repeat(35));

        String dni;
        do {
            System.out.print("   Introduce el DNI (ej: 12345678A): ");
            dni = scanner.nextLine();
            if (!validarDNI(dni)) {
                System.out.println("   " + RED + "\n   Error: El DNI debe tener 8 números y una letra al final. "+ RESET +"Ejemplo: 12345678A");
            }
        } while (!validarDNI(dni));

        System.out.print("   Introduce el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("   Introduce el apellido: ");
        String apellido = scanner.nextLine();

        String telefonoStr;
        int telefono = 0;
        do {
            System.out.print("   Introduce el teléfono (9 números. Ej: 123456789): ");
            telefonoStr = scanner.nextLine();
            if (!validarTelefono(telefonoStr)) {
                System.out.println("   " + RED + "\n   Error: El teléfono debe tener 9 números. " + RESET + "Ejemplo: 123456789");
            } else {
                try {
                    telefono = Integer.parseInt(telefonoStr);
                } catch (NumberFormatException e) {
                    System.out.println("   " + RED + "\n   Error: El teléfono debe contener solo números." + RESET);
                }
            }
        } while (!validarTelefono(telefonoStr));

        String email;
        do {
            System.out.println("   Introduce el email (ej: usuario@dominio.com): ");
            email = scanner.nextLine();
            if (!validarEmail(email)) {
                System.out.println("   " + RED + "\n   Error: El email no tiene un formato válido. " + RESET + "Ejemplo: usuario@dominio.com");
            }
        } while (!validarEmail(email));

        ClienteModel cliente = new ClienteModel(dni, nombre, apellido, telefono, email);
        ClienteDAO clientedb = new ClienteDAO();
        clientedb.anadirCliente(cliente);

        System.out.println("\n   " + GREEN + "\n ¡Cliente registrado correctamente!" + RESET);
        System.out.println("-".repeat(35));
        mostrarMenuCliente();
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{9}");
    }

    public boolean validarDNI(String dni) {
        return dni.matches("\\d{8}[A-Z]");
    }

    private boolean validarEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public ClienteModel getClienteDni() {
        System.out.println("\n   [BUSCAR] Introduce el DNI del cliente: ");
        String dni = sc.nextLine();
        ClienteModel cliente = clienteDAO.getClienteDni(dni);
        if (cliente == null) {
            System.out.println("   " + RED + "\n Error:" + RESET + " No se encontró ningún cliente con ese DNI.");
        }
        return cliente;
    }

    public void modificarCliente() {
        System.out.println("\n" + "=".repeat(36));
        System.out.println("   ** Modificar Datos del Cliente **");
        System.out.println("=".repeat(36));

        ClienteModel cliente = this.getClienteDni();
        if (cliente == null) {
            return;
        }

        String dniOriginal = cliente.getDni();
        int opcion = 0; 
        do {
            System.out.println("\n   --- ¿Qué dato desea modificar? ---");
            System.out.println("   1. Nombre");
            System.out.println("   2. Apellido");
            System.out.println("   3. Teléfono");
            System.out.println("   4. Email");
            System.out.println("   5. DNI");
            System.out.println("   6. [VOLVER] Al menú anterior");
            System.out.print("   Ingrese una opción: ");
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("   " + RED + "\n Error:" + RESET + " Por favor, introduce un número como opción.");
                sc.next(); 
                opcion = -1;
                continue;
            }
            sc.nextLine();
            System.out.println("-".repeat(35));

            switch (opcion) {
                case 1 -> {
                    System.out.print("   Introduce el nuevo nombre: ");
                    String nombre = sc.nextLine();
                    clienteDAO.actualizarNombreCliente(dniOriginal, nombre);
                    System.out.println("   " + GREEN + "\n Nombre modificado correctamente." + RESET);
                }

                case 2 -> {
                    System.out.print("   Introduce el nuevo apellido: ");
                    String apellido = sc.nextLine();
                    clienteDAO.actualizarApellidoCliente(dniOriginal, apellido);
                    System.out.println("   " + GREEN + "\n Apellido modificado correctamente." + RESET);
                }

                case 3 -> {
                    String telefonoStr;
                    int telefono = 0;
                    do {
                        System.out.print("   Introduce el nuevo teléfono (9 números. Ej: 123456789): ");
                        telefonoStr = sc.nextLine();
                        if (!validarTelefono(telefonoStr)) {
                            System.out.println("   " + RED + "\n   Error: El teléfono debe tener 9 números. " + RESET + "Ejemplo: 123456789");
                        } else {
                            try {
                                telefono = Integer.parseInt(telefonoStr);
                                if (clienteDAO.existeTelefono(telefono) && cliente.getTelefono() != telefono) {
                                    System.out.println("   " + RED + "\n Error:" + RESET + " El teléfono ya está registrado. Intente con otro.");
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "   \n Error: El teléfono debe contener solo números." + RESET);
                            }
                        }
                    } while (!validarTelefono(telefonoStr) || (validarTelefono(telefonoStr) && clienteDAO.existeTelefono(telefono) && cliente.getTelefono() != telefono));
                    if (validarTelefono(telefonoStr)) {
                        clienteDAO.actualizarTlfCliente(dniOriginal, telefono);
                        System.out.println("   " + GREEN + "\n Teléfono modificado correctamente." + RESET);
                    }
                }

                case 4 -> {
                    String email;
                    do {
                        System.out.println("   Introduce el nuevo email (ej: usuario@dominio.com): ");
                        email = sc.nextLine();
                        if (!validarEmail(email)) {
                            System.out.println("   " + RED + "\n   Error: El email no tiene un formato válido. " + RESET + "Ejemplo: usuario@dominio.com");
                        } else if (clienteDAO.existeEmail(email) && !email.equals(cliente.getEmail())) {
                            System.out.println("   " + RED + "\n Error:" + RESET + " El email ya está registrado. Intente con otro.");
                        } else {
                            break;
                        }
                    } while (!validarEmail(email) || (validarEmail(email) && clienteDAO.existeEmail(email) && !email.equals(cliente.getEmail())));
                    if (validarEmail(email)) {
                        clienteDAO.actualizarEmailCliente(dniOriginal, email);
                        System.out.println("   " + GREEN + "\n Email modificado correctamente." + RESET);
                    }
                }

                case 5 -> {
                    String nuevoDni;
                    do {
                        System.out.print("   Introduce el nuevo DNI (ej: 12345678A): ");
                        nuevoDni = sc.nextLine();
                        if (!validarDNI(nuevoDni)) {
                            System.out.println("   " + RED + "\n   Error: El DNI debe tener 8 números y una letra al final. "+ RESET +"Ejemplo: 12345678A");
                        } else if (clienteDAO.existeDni(nuevoDni) && !nuevoDni.equals(dniOriginal)) {
                            System.out.println("   " + RED + "\n Error:" + RESET + " El DNI ya está registrado. Intente con otro.");
                        } else {
                            break;
                        }
                    } while (!validarDNI(nuevoDni) || (validarDNI(nuevoDni) && clienteDAO.existeDni(nuevoDni) && !nuevoDni.equals(dniOriginal)));
                    if (validarDNI(nuevoDni)) {
                        clienteDAO.actualizarDniCliente(dniOriginal, nuevoDni);
                        System.out.println("   " + GREEN + "\n DNI modificado correctamente." + RESET);
                        dniOriginal = nuevoDni;
                    }
                }
            }
        } while (opcion != 6);
        System.out.println("Volviendo al menú anterior...");
        System.out.println("-".repeat(35));
    }

    public void eliminarCliente() {
        System.out.println("\n" + "!".repeat(35));
        System.out.println("   ** Eliminar Cuenta de Cliente **");
        System.out.println("!".repeat(35));

        ClienteModel cliente = this.getClienteDni();
        if (cliente == null) {
            return;
        }

        System.out.print("\n   ¿Está seguro de que desea eliminar su cuenta? (s/n): ");
        String confirmacion = sc.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            clienteDAO.eliminarCliente(cliente);
            if (clienteDAO.existeDni(cliente.getDni())) {
                System.out.println("   " + RED + "\n Error al eliminar el cliente. Por favor, intente nuevamente." + RESET);
            } else {
                System.out.println("   " + GREEN + "\n Cliente eliminado correctamente. ¡Esperamos verte pronto!" + RESET);
            }
        } else {
            System.out.println("   \n Operación de eliminación cancelada.");
        }
        System.out.println("-".repeat(33));
        mostrarMenuCliente();
    }
}