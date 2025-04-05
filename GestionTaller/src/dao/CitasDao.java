package dao;

import java.sql.*;
import java.util.*;
import model.CitasModel;
import model.ClienteModel;

public class CitasDao {
    ClienteDAO clienteDAO = new ClienteDAO();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public void insertarCita(CitasModel cita) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "INSERT INTO Citas (fecha, hora, descripcion, clienteDNI) VALUES (?, ?, ?, ?)";
                ps = conexion.prepareStatement(query);
                ps.setString(1, cita.getFecha());
                ps.setString(2, cita.getHora());
                ps.setString(3, cita.getDescripcion());
                ps.setString(4, cita.getCliente().getDni());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void eliminarCita(int idCita) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "DELETE FROM Citas WHERE id = ?"; // Asumiendo que tu columna ID se llama 'id'
                ps = conexion.prepareStatement(query);
                ps.setInt(1, idCita);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public ArrayList<CitasModel> listarCitas() {
        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<CitasModel> citas = new ArrayList<>();
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "SELECT id, fecha, hora, descripcion, clienteDNI FROM Citas";
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id"); // Obtener el ID de la base de datos
                    String fecha = rs.getString("fecha");
                    String hora = rs.getString("hora");
                    String descripcion = rs.getString("descripcion");
                    String clienteDNI = rs.getString("clienteDNI");
                    ClienteModel cliente = clienteDAO.getClienteDNI(clienteDNI);
                    CitasModel cita = new CitasModel(cliente, id, fecha, hora, descripcion);
                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar citas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return citas;
    }

    public ArrayList<CitasModel> listarCitasCliente(String dni) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CitasModel> citas = new ArrayList<>();
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "SELECT id, fecha, hora, descripcion, clienteDNI FROM Citas WHERE clienteDNI = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, dni);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id"); // Obtener el ID de la base de datos
                    String fecha = rs.getString("fecha");
                    String hora = rs.getString("hora");
                    String descripcion = rs.getString("descripcion");
                    String clienteDNI = rs.getString("clienteDNI");
                    ClienteModel cliente = clienteDAO.getClienteDNI(clienteDNI);
                    CitasModel cita = new CitasModel(cliente, id, fecha, hora, descripcion);
                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar citas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return citas;
    }

    public CitasModel getCitaId(int id) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CitasModel cita = null;
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "SELECT id, fecha, hora, descripcion, clienteDNI FROM Citas WHERE id = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fecha = rs.getString("fecha");
                    String hora = rs.getString("hora");
                    String descripcion = rs.getString("descripcion");
                    ClienteModel cliente = clienteDAO.getClienteDNI(rs.getString("clienteDNI"));
                    cita = new CitasModel(cliente, id, fecha, hora, descripcion);
                    cita.setIdCita(id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cita: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return cita;
    }

    public void modificarClienteCita(ClienteModel cliente, CitasModel cita) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "UPDATE Citas SET clienteDNI = ? WHERE id = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, cliente.getDni());
                ps.setInt(2, cita.getIdCita());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void modificarFechaCita(CitasModel cita, String fecha) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "UPDATE Citas SET fecha = ? WHERE id = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, fecha);
                ps.setInt(2, cita.getIdCita());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void modificarHoraCita(CitasModel cita, String hora) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "UPDATE Citas SET hora = ? WHERE id = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, hora);
                ps.setInt(2, cita.getIdCita());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void modificarDescripcionCita(CitasModel cita, String descripcion) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ConexionBD bd = new ConexionBD();
        try {
            conexion = bd.conectar();
            if (conexion != null) {
                String query = "UPDATE Citas SET descripcion = ? WHERE id = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, descripcion);
                ps.setInt(2, cita.getIdCita());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}