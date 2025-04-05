package dao;

import java.sql.*;
import model.VehiculosModel;
public class VehiculoDAO {

    public void anadirVehiculo(VehiculosModel vehiculo){
        String matricula = vehiculo.getMatricula();
        int ano = vehiculo.getAno();
        String marca = vehiculo.getMarca();
        String modelo = vehiculo.getModelo();
        String dni = vehiculo.getDni();
    
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if(conexion != null){
            String query = "INSERT INTO Vehiculo (matricula, ano, marca, modelo, dni) VALUES (?, ?, ?, ?, ?)";
        
            try (PreparedStatement ps = conexion.prepareStatement(query)){
                ps.setString(1, matricula);
                ps.setInt(2, ano);
                ps.setString(3, marca);
                ps.setString(4, modelo);
                ps.setString(5, dni);

                ps.executeUpdate();
             } catch (SQLException e) {
                System.err.println("Error al insertar cliente: " + e.getMessage());
            }
        
        }
    }

    public void mostrarVehiculos() { 
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo"; 

            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                System.out.println("--- Listado de Vehículos ---");
                while (rs.next()) {
                    System.out.println("Matrícula: " + rs.getString("matricula"));
                    System.out.println("Ano: " + rs.getInt("ano")); 
                    System.out.println("Marca: " + rs.getString("marca"));
                    System.out.println("Modelo: " + rs.getString("modelo"));
                    System.out.println("DNI Propietario: " + rs.getString("dni"));
                    System.out.println("-------------------------");
                }
            } catch (SQLException e) {
                System.err.println("Error al listar vehículos: " + e.getMessage());
            }
        }
    }

    public void eliminarVehiculo(VehiculosModel vehiculo){
        String matricula = vehiculo.getMatricula();
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "DELETE FROM Vehiculo WHERE matricula = ?"; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, matricula);
                stmt.executeUpdate();
                System.out.println("Vehículo eliminado."); 
            } catch (SQLException e) {
                System.out.println("Error al eliminar vehículo: " + e.getMessage());
            }
        }
    }
    
    public void modificarMatricula(String matricula){

    }
}
    
    

