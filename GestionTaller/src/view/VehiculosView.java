package view;

import dao.ClienteDAO;
import dao.VehiculoDAO;
import java.util.ArrayList;
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
        System.out.print("Introduce el ano de fabricación: ");
        int ano = scanner.nextInt();
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
            VehiculosModel nuevoVehiculo = new VehiculosModel(matricula, ano, marca, modelo, dniCliente);
            vehiculoDAO.anadirVehiculo(nuevoVehiculo);
            System.out.println("Vehículo agregado correctamente.");
        } else {
            System.out.println("\u001B[31mError al agregar vehículo: No existe ningún cliente registrado con el DNI: " + dniCliente + ". Por favor, registre al cliente primero.\u001B[0m");
        }
    }

    public VehiculosModel getVehiculoMatricula() {
        String matricula;
        do {
            System.out.println("Introduce la matricula del vehiculo (7 caracteres): ");
            matricula = scanner.nextLine();
        } while (matricula.length() != 7);
        VehiculosModel vehiculo = vehiculoDAO.getVehiculoMatricula(matricula);
        return vehiculo;
    }

    public void modificarVehiculos() {
        
        VehiculosModel vehiculo = this.getVehiculoMatricula();
        String matricula = vehiculo.getMatricula();

        int opcion;
        do {
            System.out.println("Modificar vehiculo");
            System.out.println("1. Modificar matricula");
            System.out.println("2. Modificar marca");
            System.out.println("3. Modificar modelo");
            System.out.println("4. Modificar año");
            System.out.println("5. Volver al menu anterior");
            System.out.println("Ingrese una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduce la matricula: ");
                    matricula = scanner.nextLine();
                    scanner.nextLine();
                    vehiculoDAO.modificarMatriculaVehiculo(matricula);
                    System.out.println("Matricula modificada correctamente");
                }
                case 2 -> {
                    System.out.println("Introduce la marca: ");
                    String marca = scanner.nextLine();
                    scanner.nextLine();
                    vehiculoDAO.modificarMarcaVehiculo(matricula, marca);
                    System.out.println("Marca modificada correctamente");
                }
                case 3 -> {
                    System.out.println("Introduce el modelo: ");
                    String modelo = scanner.nextLine();
                    scanner.nextLine();
                    vehiculoDAO.modificarModeloVehiculo(matricula, modelo);
                    System.out.println("Modelo modificado correctamente");
                }
                case 4 -> {
                    int year;
                    do {
                        System.out.println("Introduce el año: ");
                        year = scanner.nextInt();
                    } while (year < 1769 || year > 2025);
                    vehiculoDAO.modificarAnoVehiculo(matricula, year);
                    System.out.println("Año modificado correctamente");
                }
            }
        } while (opcion != 5);
        System.out.println("Volviendo al menu anterior");
    }

    public void mostrarVehiculos() {
        ArrayList<VehiculosModel> listaVehiculos = vehiculoDAO.listaVehiculos();
        System.out.println("Lista de vehiculos: ");
        for (VehiculosModel vehiculo : listaVehiculos) {
            System.out.println(vehiculo.toString());
        }
    }

    public void eliminarVehiculos() {
        VehiculosModel vehiculo = this.getVehiculoMatricula();
        vehiculoDAO.eliminarVehiculo(vehiculo);
        System.out.println("Vehiculo eliminado correctamente");
    }


}
