package dao;

import java.sql.*;
import model.EmpleadosModel;

public class EmpleadoDAO {

    public void insertarEmpleado(EmpleadosModel empleado) {
        String nombre = empleado.getNombre();
        String apellido = empleado.getApellido();
        int telefono = empleado.getTelefono();

        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("INSERT INTO Empleado (nombre, apellido, idEmpleado, telefono) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setInt(3, empleado.getIdEmpleado());
            ps.setInt(4, empleado.getTelefono());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    public EmpleadosModel getIdEmpleado(int idEmpleado) { // Corregido aquí
        EmpleadosModel empleado = null;
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Empleados WHERE idEmpleado = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idEmpleado);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        empleado = new EmpleadosModel(
                            rs.getInt("idEmpleado"),
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

    public void modificarNombreEmpleado(String nombre, int idEmpleado) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("UPDATE Empleado SET nombre = ? WHERE idEmpleado = ?")) {

            ps.setString(1, nombre);
            ps.setInt(2, idEmpleado);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar nombre del empleado: " + e.getMessage());
        }
    }

    public void modificarApellidoEmpleado(String apellido, int idEmpleado) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement stmt = conexion.prepareStatement("UPDATE Empleado SET apellido = ? WHERE idEmpleado = ?")) {

            stmt.setString(1, apellido);
            stmt.setInt(2, idEmpleado);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar apellido del empleado: " + e.getMessage());
        }
    }

    public void modificarTlfEmpleado(int telefono, int idEmpleado) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement stmt = conexion.prepareStatement("UPDATE Empleado SET telefono = ? WHERE idEmpleado = ?")) {

            stmt.setInt(1, telefono);
            stmt.setInt(2, idEmpleado);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar telefono del empleado: " + e.getMessage());
        }
    }

    public void modificarIdEmpleado(int id, int idEmpleado) {
        ConexionBD bd = new ConexionBD();

        try (Connection conexion = bd.conectar();
             PreparedStatement stmt = conexion.prepareStatement("UPDATE Empleado SET idEmpleado = ? WHERE idEmpleado = ?")) {

            stmt.setInt(1, id);
            stmt.setInt(2, idEmpleado);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar ID del empleado: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(int idEmpleado) {
        ConexionBD bd = new ConexionBD();
        try (Connection conexion = bd.conectar();
             PreparedStatement ps = conexion.prepareStatement("DELETE FROM Empleado WHERE idEmpleado = ?")) {
            ps.setInt(1, idEmpleado);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }
}