
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Taller {
    Connection conexion = ConexionBD.conectar();
    public void registrarCliente(){

        Scanner scanner = new Scanner(System.in);

        String nombre="";
        int telefono=0;
        String DNI ="";
        String query = "INSERT INTO clientes (nombre, telefono, DNI) VALUES ("+nombre+"," +telefono+","+DNI+")";

            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                System.out.println("Escribe tu nombre");
                nombre = scanner.next();
                stmt.setString(1, nombre); // Asigna el valor del
                System.out.println("Escribe tu telefono"); 
                telefono = scanner.nextInt();
                stmt.setInt(2, telefono); // Asigna el valor del 
                System.out.println("Escribe tu DNI");
                nombre = scanner.next();
                stmt.setString(3, DNI); // Asigna el valor del 
                stmt.executeUpdate(); // Ejecuta la consulta de inserción
                System.out.println("Cliente agregado exitosamente.");
                
            } catch (Exception e)  {
                System.out.println("Error al agregar cliente: " + e.getMessage());
            }
        
    
        
    }

}
