package dao;

import java.sql.*;
import java.util.ArrayList;
import model.VehiculosModel;
public class VehiculoDAO {

    public void anadirVehiculo(VehiculosModel vehiculo){
        String matricula = vehiculo.getMatricula();
        int ano = vehiculo.getAno();
        String marca = vehiculo.getMarca();
        String modelo = vehiculo.getModelo();
        String dni = vehiculo.getDni();
    
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if(conexion != null){
            String query = "INSERT INTO Vehiculo (matricula, ano, marca, modelo, dni) VALUES (?, ?, ?, ?, ?)";
        
            try (PreparedStatement ps = conexion.prepareStatement(query)){
                ps.setString(1, matricula);
                ps.setInt(2, ano);
                ps.setString(3, marca);
                ps.setString(4, modelo);
                ps.setString(5, dni);

                ps.executeUpdate();
             } catch (SQLException e) {
                System.err.println("Error al insertar cliente: " + e.getMessage());
            }
        
        }
    }

    public void mostrarVehiculos() { 
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo"; 

            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                System.out.println("Lista de Vehículos");
                while (rs.next()) {
                    System.out.println("Matrícula: " + rs.getString("matricula"));
                    System.out.println("Ano: " + rs.getInt("ano")); 
                    System.out.println("Marca: " + rs.getString("marca"));
                    System.out.println("Modelo: " + rs.getString("modelo"));
                    System.out.println("DNI Propietario: " + rs.getString("dni"));
                }
            } catch (SQLException e) {
                System.err.println("Error al listar vehículos: " + e.getMessage());
            }
        }
    }
    
    public void modificarMatriculaVehiculo(String matricula) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        if (conexion != null) {
            String query = "UPDATE Vehiculo SET matricula = ? WHERE matricula = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, matricula);
                ps.setString(2, matricula);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar matricula: " + e.getMessage());
            }
        }
    }

    public void modificarMarcaVehiculo(String matricula, String marca) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        if (conexion != null) {
            String query = "UPDATE Vehiculo SET marca = ? WHERE matricula = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, marca);
                ps.setString(2, matricula);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar marca: " + e.getMessage());
            }
        }
    }

    public void modificarModeloVehiculo(String matricula, String modelo) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        if (conexion != null) {
            String query = "UPDATE Vehiculo SET modelo = ? WHERE matricula = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, modelo);
                ps.setString(2, matricula);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar modelo: " + e.getMessage());
            }
        }
    }

    public void modificarAnoVehiculo(String matricula, int ano) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        if (conexion != null) {
            String query = "UPDATE Vehiculo SET ano = ? WHERE matricula = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, ano);
                ps.setString(2, matricula);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al modificar año: " + e.getMessage());
            }
        }
    } 

    public VehiculosModel getVehiculoMatricula(String matricula) {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();

        VehiculosModel vehiculo = null;

        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo WHERE matricula = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, matricula);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int ano = rs.getInt("ano");
                        String marca = rs.getString("marca");
                        String modelo = rs.getString("modelo");
                        String dni = rs.getString("dni");

                        vehiculo = new VehiculosModel(matricula, ano, marca, modelo, dni);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener vehiculo: " + e.getMessage());
            }
        }
        return vehiculo;
    }

    public ArrayList<VehiculosModel> listaVehiculos() {
        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();        
        ArrayList<VehiculosModel> vehiculos = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo";
            try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String matricula = rs.getString("matricula");
                int ano = rs.getInt("ano");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String dni = rs.getString("dni");

                vehiculos.add(new VehiculosModel(matricula, ano, marca, modelo, dni));
            }
            } catch (SQLException e) {
                System.err.println("Error al listar vehiculos: " + e.getMessage());
                
            }
            return vehiculos;
        }
        return null;
    }

    public void eliminarVehiculo(VehiculosModel vehiculo) {
        String matricula = vehiculo.getMatricula();

        ConexionBD bd = new ConexionBD();
        Connection conexion = bd.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Vehiculo WHERE matricula = ?";
            try (var ps = conexion.prepareStatement(query)) {
                ps.setString(1, matricula);
                ps.executeUpdate();
            } catch (Exception e) {
                System.err.println("Error al eliminar vehiculo: " + e.getMessage());
            }
        }
    }
}
    
    

