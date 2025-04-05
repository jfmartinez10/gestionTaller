package view;

import dao.ClienteDAO;
import java.util.*;
import model.ClienteModel;
public class ClienteView {
    private Scanner sc = new Scanner(System.in);
    private CitasView citasView;
    private ClienteDAO clienteDAO = new ClienteDAO();


    public ClienteView(EmpleadoView empleadoView) {
        this.citasView = new CitasView(this, empleadoView);
    }

    public void mostrarMenuCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        VehiculosView vehiculosView = new VehiculosView(this);

        do {
            System.out.println("---------------------");
            System.out.println("¡Bienvenido al menú de clientes!");
            System.out.println("1. Introducir tus datos");
            System.out.println("2. Modificar tus datos");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Agregar Vehículo");
            System.out.println("5. Modificar Vehículo");
            System.out.println("6. Mostrar Vehículos");
            System.out.println("7. Eliminar Vehículo");
            System.out.println("8. Citas");
            System.out.println("9.Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

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
    }
    public void registrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Agregar cliente");
        System.out.println("Introduce el dni: ");
        String dni = scanner.nextLine();

        System.out.println("Introduce el nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Introduce el apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("Introduce el telefono: ");
        int telefono = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduce el email: ");
        String email = scanner.nextLine();
    
        ClienteModel cliente = new ClienteModel(dni, nombre, apellido, telefono, email);
    
        ClienteDAO clientedb = new ClienteDAO();
    
        clientedb.anadirCliente(cliente);

        System.out.println("Cliente agregado correctamente");
    }

    public ClienteModel getClienteDni() {
        System.out.println("Introduce el dni del cliente: ");
        String dni = sc.nextLine();
        sc.nextLine();
        ClienteModel cliente = clienteDAO.getClienteDni(dni);
        return cliente;
    }

    public void modificarCliente() {
        ClienteModel cliente = this.getClienteDni();
        String dni = cliente.getDni();
        int opcion;
        do {
            System.out.println("Modificar cliente");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar telefono");
            System.out.println("4. Modificar email");
            System.out.println("5. Modificar dni");
            System.out.println("6. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduce el nombre: ");
                    String nombre = sc.nextLine();
                    sc.nextLine();
                    clienteDAO.actualizarNombreCliente(dni, nombre);
                    System.out.println("Nombre modificado correctamente");
                }
                case 2 -> {
                    System.out.println("Introduce el apellido: ");
                    String apellido = sc.nextLine();
                    sc.nextLine();
                    clienteDAO.actualizarApellidoCliente(dni, apellido);
                    System.out.println("Apellido modificado correctamente");
                }
                case 3 -> {
                    int telefono;
                    do {
                        System.out.println("Introduce el telefono: ");
                        telefono = sc.nextInt();
                        sc.nextLine();
                    if (clienteDAO.existeTelefono(telefono)) {
                        System.out.println("El telefono ya está registrado. Intente con otro.");
                    }
                    } while (clienteDAO.existeTelefono(telefono));
                    clienteDAO.actualizarTlfCliente(dni, telefono);
                    System.out.println("Telefono modificado correctamente");
                }
                case 4 -> {
                    String email;
                    do { 
                        System.out.println("Introduce el email: ");
                        email = sc.nextLine();
                        sc.nextLine();
                        if (clienteDAO.existeEmail(email)) {
                            System.out.println("El email ya está registrado. Intente con otro.");
                        }
                    } while (clienteDAO.existeEmail(email));
                    clienteDAO.actualizarEmailCliente(dni, email);
                    System.out.println("Email modificado correctamente");
                }
                case 5 -> {
                    do {
                        System.out.println("Introduce el dni nuevo: ");
                        dni = sc.nextLine();
                        sc.nextLine();
                        if (clienteDAO.existeDni(dni)) {
                            System.out.println("El DNI ya está registrado. Intente con otro.");
                        }
                    } while (clienteDAO.existeDni(dni));
                    clienteDAO.actualizarDniCliente(cliente.getDni(), dni);
                    System.out.println("Dni modificado correctamente");
                }
                case 6 -> System.out.println("Volviendo al menu anterior");
            }
        } while (opcion != 7);
    }

    public void eliminarCliente() {
        ClienteModel cliente = this.getClienteDni();
        clienteDAO.eliminarCliente(cliente);
        if (clienteDAO.existeDni(cliente.getDni())) {
            System.out.println("Error al eliminar el cliente. Por favor, intente nuevamente.");
            return;
        }
        System.out.println("Cliente eliminado correctamente");
    }

    
}