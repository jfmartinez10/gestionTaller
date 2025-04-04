package dao;

import java.sql.*;
import model.EmpleadosModel;

public class EmpleadoDAO {

    public void insertarEmpleado(EmpleadosModel empleado) {
        
        int id = empleado.getId();
        String nombre = empleado.getNombre();
        String apellido = empleado.getApellido();
        int telefono = empleado.getTelefono();

        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("INSERT INTO Empleados (nombre, apellido, id, telefono) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setInt(3, empleado.getId());
            ps.setInt(4, empleado.getTelefono());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    public EmpleadosModel getIdEmpleado(int id) { // Corregido aquí
        EmpleadosModel empleado = null;
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Empleados WHERE id = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        empleado = new EmpleadosModel(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getInt("telefono")
                        );
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener empleado: " + e.getMessage());
            }
        }
        return empleado;
    }

    public void modificarNombreEmpleado(String nombre, int id) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("UPDATE Empleados SET nombre = ? WHERE id = ?")) {

            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar nombre del empleado: " + e.getMessage());
        }
    }

    public void modificarApellidoEmpleado(String apellido, int id) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement stmt = conexion.prepareStatement("UPDATE Empleados SET apellido = ? WHERE id = ?")) {

            stmt.setString(1, apellido);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar apellido del empleado: " + e.getMessage());
        }
    }

    public void modificarTlfEmpleado(int telefono, int id) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement stmt = conexion.prepareStatement("UPDATE Empleados SET telefono = ? WHERE id = ?")) {

            stmt.setInt(1, telefono);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar telefono del empleado: " + e.getMessage());
        }
    }


    public void eliminarEmpleado(int id) {
        ConexionBD bd = new ConexionBD();
        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("DELETE FROM Empleados WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }
}