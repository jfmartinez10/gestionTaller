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
                            rs.getString("email")
                        );
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
        }
        return cliente;
    }

    public ClienteModel getClienteDni(String dni) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        ClienteModel cliente = null;

        if (conexion != null) {
            String query = "SELECT * FROM Clientes WHERE dni = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String apellido = rs.getString("apellido");
                        int telefono = rs.getInt("telefono");
                        String email = rs.getString("email");

                        cliente = new ClienteModel(dni, nombre, apellido, telefono, email);
                    }
                }

            } catch (SQLException e) {
                System.err.println("Error al obtener cliente por DNI: " + e.getMessage());
            }
            return cliente;
        }
        return null;
    }
    
    public void actualizarNombreCliente(String dni, String nombre) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "UPDATE Clientes SET nombre = ? WHERE dni = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, nombre);
                ps.setString(2, dni);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar nombre del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarApellidoCliente(String dni,String apellido) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        
        if (conexion != null) {
            String query = "UPDATE Clientes SET apellido = ? WHERE dni = ?";
            try(PreparedStatement stmt = conexion.prepareStatement(query)){
                stmt.setString(1, apellido);
                stmt.setString(2, dni);
                stmt.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al actualizar apellido del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarTlfCliente(String dni, int telefono) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        
        if (conexion != null) {
            String query = "UPDATE Clientes SET telefono = ? WHERE dni = ?";
            try(PreparedStatement stmt = conexion.prepareStatement(query)){
                stmt.setInt(1, telefono);
                stmt.setString(2, dni);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar telefono del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarEmailCliente(String dni, String email) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();       
        
        if (conexion != null) {
            String query = "UPDATE Clientes SET email = ? WHERE dni = ?";
            try(PreparedStatement stmt = conexion.prepareStatement(query)){
                stmt.setString(1, email);
                stmt.setString(2, dni);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar email del cliente: " + e.getMessage());
            }
        }
    }

    public void actualizarDniCliente(String dniActual, String dniNuevo) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();        
        
        if (conexion != null) {
            String query = "UPDATE Clientes SET dni = ? WHERE dni = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, dniNuevo);
                stmt.setString(2, dniActual);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar DNI del cliente: " + e.getMessage());
            }
        }
    }

    public boolean existeDni(String dni) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        boolean existe = false;

        if (conexion != null) {
            String query = "SELECT * FROM Clientes WHERE dni = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    existe = rs.next();
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar existencia de DNI: " + e.getMessage());
            }
        }
        return existe;
    }

    public boolean existeTelefono(int telefono) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        boolean existe = false;

        if (conexion != null) {
            String query = "SELECT * FROM Clientes WHERE telefono = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, telefono);
                try (ResultSet rs = ps.executeQuery()) {
                    existe = rs.next();
                }

            } catch (SQLException e) {
                System.err.println("Error al verificar existencia de telefono: " + e.getMessage());
            }
        }
        return existe;
    }

    public boolean existeEmail(String email) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        boolean existe = false;

        if (conexion != null) {
            String query = "SELECT * FROM Clientes WHERE email = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    existe = rs.next();
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar existencia de email: " + e.getMessage());
            }
        }
        return existe;
    }
    
    public void eliminarCliente(ClienteModel cliente) {
        String dni = cliente.getDni();

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "DELETE FROM Clientes WHERE dni = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al eliminar cliente: " + e.getMessage());
                
            }
        }
    }
}