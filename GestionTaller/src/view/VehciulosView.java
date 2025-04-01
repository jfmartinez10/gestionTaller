package view;

import java.util.Scanner;

import dao.ClienteDAO;
import dao.VehiculoDAO;
import model.ClienteModel;
import model.VehiculosModel;

public class VehciulosView {
public void agregarVehiculos(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Agregar Vehiculo");
        System.out.println("Introduce el matricula: ");
        String matricula = scanner.nextLine();

        System.out.println("Introduce el año: ");
        int año = scanner.nextInt();

        System.out.println("Introduce el marca vehiculo: ");
        String marca = scanner.next();

        System.out.println("Introduce la modelo del vehiculo: ");
        String modelo = scanner.next();

        VehiuclosModel vehiculo = new VehiculosModel(matricula, año, marca, modelo);

        VehiculoDAO clientedb = new VehiculoDAO();
    }
}
