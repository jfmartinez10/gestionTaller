package view;

import dao.ConexionBD;
import java.sql.*;
import java.util.*;
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
            if (u.getNombre().equals(cliente) && u.getContraseña().equals(contraseña)) {
                nombreCliente = cliente;
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + nombreCliente + "!");
                ClienteView clienteView = new ClienteView(); 
                clienteView.mostrarMenuCliente(); 
                return; // Salir del método iniciarSesion()
            }
        }

        System.out.println("\u001B[31mCredenciales incorrectas. Por favor, intenta de nuevo o crea una cuenta.\u001B[0m");
        // Aquí iría el método main
    }

    public void crearCuenta() {
        System.out.print("Introduce tu nombre: ");
        String nuevoNombre = scanner.nextLine();

        for (ClienteModel u : clientes) {
            if (u.getNombre().equals(nuevoNombre)) {
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
        ClienteView clienteView = new ClienteView(); 
        clienteView.mostrarMenuCliente(); 
    }
}
