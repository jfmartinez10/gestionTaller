package view;

import dao.ClienteDAO;
import java.util.Scanner;
import model.ClienteModel;

public class TallerView {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private String nombreClienteLogueado;
    private Scanner scanner = new Scanner(System.in);

    public void iniciarSesion() {
        System.out.print("Introduce tu DNI: "); // Usaremos el DNI como identificador único
        String dni = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contraseña = scanner.nextLine();

        ClienteModel cliente = clienteDAO.getClienteDNI(dni);

        if (cliente != null && cliente.getContraseña().equals(contraseña)) {
            nombreClienteLogueado = cliente.getNombre();
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + nombreClienteLogueado + "!");
            ClienteView clienteView = new ClienteView();
            clienteView.mostrarMenuCliente();
        } else {
            System.out.println("\u001B[31mCredenciales incorrectas. Por favor, intenta de nuevo o crea una cuenta.\u001B[0m");
        }
    }

    public void crearCuenta() {
        System.out.println("Creando una nueva cuenta de cliente:");
        System.out.print("Introduce tu DNI: ");
        String nuevoDni = scanner.nextLine();

        if (clienteDAO.getClienteDNI(nuevoDni) != null) {
            System.out.println("\u001B[31mYa existe un cliente con ese DNI. Por favor, utiliza otro o inicia sesión.\u001B[0m");
            return;
        }

        System.out.print("Introduce tu nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Introduce tu apellido: ");
        String nuevoApellido = scanner.nextLine();
        System.out.print("Introduce tu teléfono: ");
        int nuevoTelefono = scanner.nextInt();
        scanner.nextLine(); // Consumir el carácter de nueva línea
        System.out.print("Introduce tu email: ");
        String nuevoEmail = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String nuevaContraseña = scanner.nextLine();
        System.out.print("Confirma tu contraseña: ");
        String confirmarContraseña = scanner.nextLine();

        if (!nuevaContraseña.equals(confirmarContraseña)) {
            System.out.println("\u001B[31mLas contraseñas no coinciden. Por favor, inténtalo de nuevo.\u001B[0m");
            crearCuenta(); // Llamada recursiva para intentarlo de nuevo
            return;
        }

        ClienteModel nuevoCliente = new ClienteModel(nuevoDni, nuevoNombre, nuevoApellido, nuevoTelefono, nuevoEmail, nuevaContraseña);
        clienteDAO.añadirCliente(nuevoCliente);
        System.out.println("Cuenta creada con éxito. ¡Bienvenido, " + nuevoNombre + "!");
        ClienteView clienteView = new ClienteView();
        clienteView.mostrarMenuCliente();
    }
}