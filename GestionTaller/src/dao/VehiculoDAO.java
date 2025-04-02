package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.VehiculosModel;
public class VehiculoDAO {

    public void añadirVehiculo(VehiculosModel vehiculo){
        String matricula = vehiculo.getMatricula();
        int año = vehiculo.getAño();
        String marca = vehiculo.getMarca();
        String modelo = vehiculo.getModelo();
    
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if(conexion != null){
            String query = "INSERT INTO Vehiculo (matricula, año, marca, modelo) VALUES (?, ?, ?, ?)";
        
            try (PreparedStatement ps = conexion.prepareStatement(query)){
                ps.setString(1, matricula);
                ps.setInt(2, año);
                ps.setString(3, marca);
                ps.setString(4, modelo);

                ps.executeUpdate();
             } catch (SQLException e) {
                System.err.println("Error al insertar cliente: " + e.getMessage());
            }
        
        }
    }

    
}
