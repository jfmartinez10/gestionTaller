package view;

import dao.ClienteDAO;
import dao.VehiculoDAO;
import java.util.Scanner;
import model.ClienteModel;
import model.VehiculosModel; // Asegúrate de importar ClienteModel

public class VehiculosView {
    private Scanner scanner = new Scanner(System.in);
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO(); 
    private ClienteView clienteView;

    public VehiculosView(ClienteView clienteView) {
        this.clienteView = clienteView;
    }

    public void agregarVehiculos() {
        System.out.println("Agregar vehículo:");
        System.out.print("Introduce la matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Introduce el año de fabricación: ");
        int año = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Introduce la marca: ");
        String marca = scanner.nextLine();
        System.out.print("Introduce el modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Introduce el DNI del cliente propietario: ");
        String dniCliente = scanner.nextLine();

        // Verificar si el cliente con este DNI existe en la base de datos
        ClienteModel cliente = clienteDAO.getClienteDNI(dniCliente);

        if (cliente != null) {
            VehiculosModel nuevoVehiculo = new VehiculosModel(matricula, año, marca, modelo, dniCliente);
            vehiculoDAO.añadirVehiculo(nuevoVehiculo);
            System.out.println("Vehículo agregado correctamente.");
        } else {
            System.out.println("\u001B[31mError al agregar vehículo: No existe ningún cliente registrado con el DNI: " + dniCliente + ". Por favor, registre al cliente primero.\u001B[0m");
        }
    }
    
    /*public void mostrarVehiculos(){
        VehiculosModel vehiculo = new VehiculosModel(matricula, año, marca, modelo);
        vehiculoDao.mostrarVehiculos(vehiculo);
    }*/
}
