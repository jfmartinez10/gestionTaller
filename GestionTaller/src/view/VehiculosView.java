package view;

import dao.VehiculoDAO;
import java.util.*;
import model.VehiculosModel;

public class VehiculosView {
    VehiculoDAO vehiculoDao = new VehiculoDAO();
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

        VehiculosModel vehiculo = new VehiculosModel(matricula, año, marca, modelo);

        VehiculoDAO vehiculodb = new VehiculoDAO();

        vehiculodb.añadirVehiculo(vehiculo);

        System.out.println("Vehiculo agregado correctamente");
    }
    
    /*public void mostrarVehiculos(){
        VehiculosModel vehiculo = new VehiculosModel(matricula, año, marca, modelo);
        vehiculoDao.mostrarVehiculos(vehiculo);
    }*/
}
