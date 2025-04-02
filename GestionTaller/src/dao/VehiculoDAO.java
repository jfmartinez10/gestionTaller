package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void mostrarVehiculos(VehiculosModel vehiculo){

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        ArrayList<VehiculosModel> vehiculos = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo";

            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String matricula = rs.getString("matricula");
                    int año = rs.getInt("año");
                    String marca = rs.getString("marca");
                    String modelo = rs.getString("modelo");
                    
                    Cliente titular = new ClienteDAO().getClienteDNI(rs.getString("DNI"));
                    vehiculos.add(new Vehiculo(matricula, año, marca, modelo));
                }
            } catch (SQLException e) {
                System.err.println("Error al listar vehiculos: " + e.getMessage());
                
            }
        }
    }
    
}
