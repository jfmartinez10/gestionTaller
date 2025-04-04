package dao;

import java.sql.*;
import java.util.*;
import model.CitasModel;
import model.ClienteModel;
import model.EmpleadosModel;

public class CitasDao {
    ClienteDAO clienteDAO = new ClienteDAO();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public void insertarCita(CitasModel cita) {
        String fecha = cita.getFecha();
        String hora = cita.getHora();
        String descripcion = cita.getDescripcion();
        ClienteModel cliente = cita.getCliente();
        EmpleadosModel empleado = cita.getEmpleado();

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Citas (fecha, hora, descripcion, clienteDNI, empleadoID) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, fecha);
                ps.setString(2, hora);
                ps.setString(3, descripcion);
                ps.setString(4, cliente.getDni());
                ps.setInt(5, empleado.getId());
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al insertar cita: " + e.getMessage());
            }
        }
    }

    public void eliminarCita(CitasModel cita) {
        int idCita = cita.getIdCita();
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "DELETE FROM Citas WHERE idCita = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idCita);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al eliminar cita: " + e.getMessage());
            }
        }
    }

    public ArrayList<CitasModel> listarCitas() {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        ArrayList<CitasModel> citas = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Citas";
            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String fecha = rs.getString("fecha");
                    String hora = rs.getString("hora");
                    String descripcion = rs.getString("descripcion");
                    ClienteModel cliente = clienteDAO.getClienteDNI(rs.getString("clienteDNI"));
                    EmpleadosModel empleado = empleadoDAO.getIdEmpleado(rs.getInt("empleadoID"));
                    CitasModel cita = new CitasModel(cliente, empleado, fecha, hora, descripcion);
                    cita.setIdCita(rs.getInt("idCita"));
                    citas.add(cita);
                }
            } catch (SQLException e) {
                System.err.println("Error al listar citas: " + e.getMessage());
            }
        }
        return citas;
    }

    public ArrayList<CitasModel> listarCitasCliente(String dni) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        ArrayList<CitasModel> citas = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Citas WHERE clienteDNI = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String fecha = rs.getString("fecha");
                        String hora = rs.getString("hora");
                        String descripcion = rs.getString("descripcion");
                        ClienteModel cliente = clienteDAO.getClienteDNI(rs.getString("clienteDNI"));
                        EmpleadosModel empleado = empleadoDAO.getIdEmpleado(rs.getInt("empleadoID"));
                        CitasModel cita = new CitasModel(cliente, empleado, fecha, hora, descripcion);
                        cita.setIdCita(rs.getInt("idCita"));
                        citas.add(cita);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al listar citas: " + e.getMessage());
            }
        }
        return citas;
    }

    public CitasModel getCitaId(int idCita) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        CitasModel cita = null;

        if (conexion != null) {
            String query = "SELECT * FROM Citas WHERE idCita = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, idCita);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String fecha = rs.getString("fecha");
                        String hora = rs.getString("hora");
                        String descripcion = rs.getString("descripcion");
                        ClienteModel cliente = clienteDAO.getClienteDNI(rs.getString("clienteDNI"));
                        EmpleadosModel empleado = empleadoDAO.getIdEmpleado(rs.getInt("empleadoID"));
                        cita = new CitasModel(cliente, empleado, fecha, hora, descripcion);
                        cita.setIdCita(idCita);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener cita: " + e.getMessage());
            }
        }
        return cita;
    }

    public void modificarClienteCita(ClienteModel cliente, CitasModel cita) {
        String dni = cliente.getDni();
        int idCita = cita.getIdCita();
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "UPDATE Citas SET clienteDNI = ? WHERE idCita = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, dni);
                ps.setInt(2, idCita);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar cita: " + e.getMessage());
            }
        }
    }

    public void modificarEmpleadoCita(EmpleadosModel empleado, CitasModel cita) {
        int empleadoId = empleado.getId();
        int idCita = cita.getIdCita();
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "UPDATE Citas SET empleadoID = ? WHERE idCita = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, empleadoId);
                ps.setInt(2, idCita);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar cita: " + e.getMessage());
            }
        }
    }

    public void modificarFechaCita(CitasModel cita, String fecha) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        int idCita = cita.getIdCita();
        if (conexion != null) {
            String query = "UPDATE Citas SET fecha = ? WHERE idCita = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, fecha);
                ps.setInt(2, idCita);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar cita: " + e.getMessage());
            }
        }
    }

    public void modificarHoraCita(CitasModel cita, String hora) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        int idCita = cita.getIdCita();
        if (conexion != null) {
            String query = "UPDATE Citas SET hora = ? WHERE idCita = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, hora);
                ps.setInt(2, idCita);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar cita: " + e.getMessage());
            }
        }
    }

    public void modificarDescripcionCita(CitasModel cita, String descripcion) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        int idCita = cita.getIdCita();
        if (conexion != null) {
            String query = "UPDATE Citas SET descripcion = ? WHERE idCita = ?";

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, descripcion);
                ps.setInt(2, idCita);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar cita: " + e.getMessage());
            }
        }
    }
}