package dao;

import java.sql.*;
import java.util.ArrayList;
import model.ProveedoresModel;

public class ProveedoresDAO {
    public void insertarProveedor(ProveedoresModel proveedores) {
        
        int id = proveedores.getId();
        String nombre = proveedores.getNombre();
        String email = proveedores.getEmail();
        int telefono = proveedores.getTelefono();

        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Proveedores (id, nombre, email, telefono) VALUES (?, ?, ?, ?)")) {

            ps.setInt(1, proveedores.getId());
            ps.setString(2, proveedores.getNombre());
            ps.setString(3, proveedores.getEmail());
            ps.setInt(4, proveedores.getTelefono());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    public ProveedoresModel getIdProveedores(int id) { 
        ProveedoresModel proveedor = null;
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Proveedores WHERE id = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        proveedor = new ProveedoresModel(
                            rs.getInt("id"), 
                            rs.getInt("telefono"), 
                            rs.getString("nombre"), 
                            rs.getString("email")
                        );
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener proveedor: " + e.getMessage());
            }
        }
        return proveedor;
    }

    public void modificarNombreProveedores(String nombre, int id) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("UPDATE Proveedores SET nombre = ? WHERE id = ?")) {

            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar nombre del proveedor: " + e.getMessage());
        }
    }

    public void modificarEmailProveedor(String email, int id) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement stmt = conexion.prepareStatement("UPDATE Proveedores SET email = ? WHERE id = ?")) {

            stmt.setString(1, email);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar email del proveedor: " + e.getMessage());
        }
    }

    public void modificarTlfProveedor(int telefono, int id) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement stmt = conexion.prepareStatement("UPDATE Proveedores SET telefono = ? WHERE id = ?")) {

            stmt.setInt(1, telefono);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar telefono del proveedor: " + e.getMessage());
        }
    }

    public ArrayList<ProveedoresModel> listarProveedores() {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        ArrayList<ProveedoresModel> proveedores = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Proveedores";
                
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int telefono = rs.getInt("telefono");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");

                    ProveedoresModel proveedor = new ProveedoresModel(id, telefono, nombre, email);
                    proveedores.add(proveedor);
                }
                } catch (SQLException e) {
                    System.err.println("Error al listar proveedores: " + e.getMessage());
                }
                return proveedores;
            }
            return null;
        }

    public void eliminarProveedor(int id) {
        ConexionBD bd = new ConexionBD();
        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("DELETE FROM Proveedores WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }
}