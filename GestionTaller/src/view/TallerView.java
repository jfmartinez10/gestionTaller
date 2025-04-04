package view;

import dao.ClienteDAO;
import java.util.Scanner;
import model.ClienteModel;

public class TallerView {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ClienteModel clienteLogueado;
    private Scanner scanner = new Scanner(System.in);

    public void iniciarSesion() {
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contraseña = scanner.nextLine();

        ClienteModel cliente = clienteDAO.getClienteDNI(dni);

        if (cliente != null && cliente.getContraseña().equals(contraseña)) {
            clienteLogueado = cliente;
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + clienteLogueado.getNombre() + "!");
            ClienteView clienteView = new ClienteView();
            clienteView.mostrarMenuCliente();
        } else {
            System.out.println("\u001B[31mError al iniciar sesión: DNI o contraseña incorrectos.\u001B[0m");
        }
    }

    public void crearCuenta() {
        System.out.println("Creando una nueva cuenta de cliente:");
        System.out.print("Introduce tu DNI: ");
        String nuevoDni = scanner.nextLine();

        if (clienteDAO.getClienteDNI(nuevoDni) != null) {
            System.out.println("\u001B[31mError al crear cuenta: Ya existe un cliente con ese DNI. Por favor, utiliza otro o inicia sesión.\u001B[0m");
            return;
        }

        System.out.print("Introduce tu contraseña: ");
        String nuevaContraseña = scanner.nextLine();
        System.out.print("Confirma tu contraseña: ");
        String confirmarContraseña = scanner.nextLine();

        if (!nuevaContraseña.equals(confirmarContraseña)) {
            System.out.println("\u001B[31mError al crear cuenta: Las contraseñas no coinciden. Por favor, inténtalo de nuevo.\u001B[0m");
            crearCuenta(); // Llamada recursiva para intentarlo de nuevo
            return;
        }

        // Si llegamos aquí, el DNI no existe y las contraseñas coinciden.
        // Ahora creamos un ClienteModel con la información básica y lo guardamos.
        ClienteModel nuevoCliente = new ClienteModel(nuevoDni, "", "", 0, "", nuevaContraseña);
        clienteDAO.añadirCliente(nuevoCliente);
        System.out.println("Cuenta creada con éxito. Ahora puedes iniciar sesión e introducir tus datos en el menú de cliente.");
    }

    public ClienteModel getClienteLogueado() {
        return clienteLogueado;
    }
}