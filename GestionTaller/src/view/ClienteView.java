package view;

import java.util.*;

import dao.ClienteDAO;
import model.ClienteModel;

public class ClienteView {
    public void mostrarMenuCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("¡Bienvenido al menú de clientes!");
            System.out.println("1. Introducir tus datos");
            System.out.println("2. Mostrar Vehículos");
            System.out.println("3. Agregar Vehículo");
            System.out.println("4. Eliminar Vehículo");
            System.out.println("5. Reportar problema");
            System.out.println("6.Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                } // mostrarVehiculos()
                case 2 -> {
                } // agregarVehiculo()
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
        System.out.println("Introduce el nombre: ");
        String nombre = scanner.nextLine();
        scanner.next();
        System.out.println("Introduce el apellido: ");
        String apellido = scanner.nextLine();
        scanner.next();
        System.out.println("Introduce el telefono: ");
        int telefono = scanner.nextInt();
        scanner.next();
        System.out.println("Introduce el email: ");
        String email = scanner.nextLine();
        scanner.next();
        System.out.println("Introduce el DNI: ");
        String DNI = scanner.nextLine();
        scanner.next();
 
        ClienteModel cliente = new cliente(nombre, apellido, telefono, email, DNI);

        ClienteDAO.añadirCliente(cliente);
        
        System.out.println("Cliente agregado correctamente");
    }
}
