package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.ClienteModel;
public class ClienteDAO {
    
//metodo añardirCliente()
 public void añadirCliente(ClienteModel cliente) {

        String nombre = cliente.getNombre();
        String apellido = cliente.getApellido();
        int telefono = cliente.getTelefono();
        String email = cliente.getEmail();
        String DNI = cliente.getDNI();

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        
        if (conexion != null) {
            String query = "INSERT INTO Cliente (DNI, nombre, apellido, telefono, email) VALUES ( ?, ?, ?, ?, ?)";
            
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, DNI);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setInt(4, telefono);
                ps.setString(5, email);
                
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al insertar cliente: " + e.getMessage());
            }
        
        
        
        }
    }
}
