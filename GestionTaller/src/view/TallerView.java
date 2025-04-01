package view;

import java.sql.*;

import dao.ConexionBD;
import model.ClienteModel;

public class TallerView {
    Connection conexion = ConexionBD.conectar();
    
    // Atributos
    private List<ClienteModel> clientes = new ArrayList<>();
    private String nombreCliente;
    private Scanner scanner = new Scanner(System.in);

    public void iniciarSesion() {

        System.out.print("Introduce tu nombre: ");
        String cliente = scanner.nextLine(); 
        System.out.print("Introduce tu contraseña: ");
        String contraseña = scanner.nextLine();

        for (ClienteModel u : clientes) {
            if (u.getNombreCliente().equals(cliente) && u.getContraseña().equals(contraseña)) {
                nombreCliente = cliente; 
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + nombreCliente + "!");
                mostrarMenuCliente();
            }
        }

        System.out.println("\u001B[31mCredenciales incorrectas. Por favor, intenta de nuevo o crea una cuenta.\u001B[0m");
        // Aquí iría el método main
    }

    public void crearCuenta() {
        System.out.print("Introduce tu nombre: ");
        String nuevoNombre = scanner.nextLine(); 

        for (ClienteModel u : clientes) {
            if (u.getNombreCliente().equals(nuevoNombre)) {
                System.out.println("\u001B[31mEl nombre de usuario ya existe. Por favor, elige otro.\u001B[0m");
                crearCuenta();
                return; 
            }
        }

        System.out.print("Introduce tu contraseña: ");
        String nuevaContraseña = scanner.nextLine(); 
        System.out.print("Confirma tu contraseña: ");
        String confirmarContraseña = scanner.nextLine(); 

        if (!nuevaContraseña.equals(confirmarContraseña)) {
            System.out.println("\u001B[31mLas contraseñas no coinciden. Por favor, inténtalo de nuevo.\u001B[0m");
            crearCuenta(); 
            return; 
        }

        System.out.print("Introduce tu saldo inicial: ");
        int saldoInicial = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Cuenta creada con éxito. ¡Bienvenido, " + nuevoNombre + "!");
        mostrarMenuCliente();
    }


    public void registrarCliente(){

        Scanner scanner = new Scanner(System.in);

        String nombreCliente="";
        int telefono=0;
        String DNI ="";
        String query = "INSERT INTO clientes (nombre, telefono, DNI) VALUES ("+nombreCliente+"," +telefono+","+DNI+")";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
            System.out.println("Escribe tu nombre");
            nombreCliente = scanner.next();
            stmt.setString(1, nombreCliente); // Asigna el valor del
            System.out.println("Escribe tu telefono"); 
            telefono = scanner.nextInt();
            stmt.setInt(2, telefono); // Asigna el valor del 
            System.out.println("Escribe tu DNI");
            DNI = scanner.next();
            stmt.setString(3, DNI); // Asigna el valor del 
            stmt.executeUpdate(); // Ejecuta la consulta de inserción
            System.out.println("Cliente agregado exitosamente.");
                
        } catch (Exception e)  {
                System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

}
