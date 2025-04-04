package view;

import dao.ClienteDAO;
import java.util.*;
import model.ClienteModel;

public class ClienteView {
    private Scanner sc = new Scanner(System.in);
    public void mostrarMenuCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        VehiculosView vehiculosView = new VehiculosView();

        do {
            System.out.println("¡Bienvenido al menú de clientes!");
            System.out.println("1. Introducir tus datos");
            System.out.println("2. Agregar Vehículo");
            System.out.println("3. Mostrar Vehículos");
            System.out.println("4. Eliminar Vehículo");
            System.out.println("5. Reportar problema");
            System.out.println("6.Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> vehiculosView.agregarVehiculos();
                case 3 -> {
                } // eliminarVehiculo()
                case 4 -> {
                } // reportarProblema()
                case 5 ->{   
                }
            }

        } while (opcion != 6);
    }
    public void registrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Agregar cliente");
        System.out.println("Introduce el DNI: ");
        String DNI = scanner.nextLine();

        System.out.println("Introduce el nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.println("Introduce el apellido: ");
        String apellido = scanner.nextLine();
        
        System.out.println("Introduce el telefono: ");
        int telefono = scanner.nextInt();
        
        System.out.println("Introduce el email: ");
        String email = scanner.nextLine();
        
        
 
        ClienteModel cliente = new ClienteModel(DNI, nombre, apellido, telefono, email, "");

        ClienteDAO clientedb = new ClienteDAO();

        clientedb.añadirCliente(cliente);

        System.out.println("Cliente agregado correctamente");
    }

    public ClienteModel getClienteDNI() {
        System.out.println("Introduzca el DNI del cliente: ");
        String dni = sc.nextLine();
        return new ClienteModel(dni); // Asumiendo que ClienteModel tiene un constructor que acepta el DNI
    }

}