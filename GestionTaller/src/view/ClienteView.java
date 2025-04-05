package view;

import dao.ClienteDAO;
import java.util.*;
import model.ClienteModel;

public class ClienteView {
    private Scanner sc = new Scanner(System.in);
    private CitasView citasView; 
    private EmpleadoView empleadoView; 

    public ClienteView(EmpleadoView empleadoView) {
        this.empleadoView = empleadoView;
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
            System.out.println("5. Mostrar Vehículos");
            System.out.println("6. Eliminar Vehículo");
            System.out.println("7. Citas");
            System.out.println("8. Reportar problema");
            System.out.println("9.Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> {}
                case 3 -> {
                    
                } // eliminarVehiculo()
                case 4 -> vehiculosView.agregarVehiculos();
                case 5 -> {
                } // mostrarVehiculo()
                case 6 ->{   
                } // eliminarVehiculo()
                case 7 -> citasView.menuCitasCliente(); 
                case 8-> {
                } // reportarProblema()
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

    public ClienteModel getClienteDNI() {
        System.out.println("Introduzca el DNI del cliente: ");
        String dni = sc.nextLine();
        ClienteDAO clienteDAO = new ClienteDAO(); 
        return clienteDAO.getClienteDNI(dni); 
    }
    
}