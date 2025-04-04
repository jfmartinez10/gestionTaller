package dao;

import java.sql.*;
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

    //método añadido
    public ClienteModel getClienteDNI(String dni) {
        ClienteModel cliente = null;
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Cliente WHERE DNI = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        cliente = new ClienteModel(
                            rs.getString("DNI"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getInt("telefono"),
                            rs.getString("email"),
                            rs.getString("contraseña")
                        );
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
        }
        return cliente;
    }
}