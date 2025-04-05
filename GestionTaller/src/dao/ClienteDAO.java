package dao;

import java.sql.*;
import model.ClienteModel;

public class ClienteDAO {

 public void anadirCliente(ClienteModel cliente) {

        String nombre = cliente.getNombre();
        String apellido = cliente.getApellido();
        int telefono = cliente.getTelefono();
        String email = cliente.getEmail();
        String dni = cliente.getDni();

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        
        if (conexion != null) {
            String query = "INSERT INTO Clientes (dni, nombre, apellido, telefono, email) VALUES ( ?, ?, ?, ?, ?)";
            
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
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

    public ClienteModel getClienteDNI(String dni) {
        ClienteModel cliente = null;
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Clientes WHERE dni = ?"; 
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        cliente = new ClienteModel(
                                rs.getString("dni"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getInt("telefono"),
                                rs.getString("email"),
                                rs.getString("contrasena")
                        );
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
        }
        return cliente;
    }

    public void actualizarNombreCliente(String dni, String nombre) {

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "UPDATE Cliente SET nombre = ? WHERE dni = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nombre);
                ps.setString(2, dni);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar nombre del cliente: " + e.getMessage());
            }
        }
    }

}