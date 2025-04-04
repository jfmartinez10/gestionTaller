package dao;

import java.sql.*;
import model.VehiculosModel;
public class VehiculoDAO {

    public void añadirVehiculo(VehiculosModel vehiculo){
        String matricula = vehiculo.getMatricula();
        int año = vehiculo.getAño();
        String marca = vehiculo.getMarca();
        String modelo = vehiculo.getModelo();
        String dni = vehiculo.getDni();
    
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if(conexion != null){
            String query = "INSERT INTO Vehiculo (matricula, año, marca, modelo, dni) VALUES (?, ?, ?, ?, ?)";
        
            try (PreparedStatement ps = conexion.prepareStatement(query)){
                ps.setString(1, matricula);
                ps.setInt(2, año);
                ps.setString(3, marca);
                ps.setString(4, modelo);
                ps.setString(5, dni);

                ps.executeUpdate();
             } catch (SQLException e) {
                System.err.println("Error al insertar cliente: " + e.getMessage());
            }
        
        }
    }

    public void mostrarVehiculos(VehiculosModel vehiculo){

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo";

            try (Statement stmt = conexion.createStatement(); 
            ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        System.out.println("matricula: " + rs.getInt("matricula"));
                        System.out.println("año: " + rs.getString("año "));
                        System.out.println("marca: " + rs.getString("marca"));
                        System.out.println("modelo: " + rs.getString("modelo"));
                        System.out.println("dni: " + rs.getString("dni"));

                        }
                
            } catch (SQLException e) {
                System.err.println("Error al listar vehiculos: " + e.getMessage());
                
            }
        }
    }

    public void eliminarVehiculo(VehiculosModel vehiculo){

        String matricula = vehiculo.getMatricula();

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "DELETE FROM Vehiculos WHERE matricula = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, matricula); 
                stmt.executeUpdate();

                System.out.println("Cliente eliminado.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
    }
    
    public void modificarMatricula(String matricula){

    }
}
    
    

