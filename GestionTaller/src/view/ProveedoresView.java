package view;

import dao.ProveedoresDAO;
import java.util.*;
import model.ProveedoresModel;

public class ProveedoresView {
    private Scanner sc = new Scanner(System.in);
    private ProveedoresDAO proveedorDAO = new ProveedoresDAO();

    public void mostrarMenuProveedor() {
        int opcion;

        do {
            System.out.println("---------------------");
            System.out.println("¡Bienvenido al menú de proveedores!");
            System.out.println("1. Insertar Proveedores");
            System.out.println("2. Modificar Proveedores");
            System.out.println("3. Buscar Proveedor por ID");
            System.out.println("5. Eliminar proveedor");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Ingrese un número.");
                sc.nextLine();
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> insertarProveedor();
                case 2 -> modificarProveedor();
                case 3 -> buscarProveedorPorId();
                case 4 -> eliminarProveedor();
                case 5 -> System.out.println("Saliendo del programa.");
                default -> {
                    if (opcion != 6 && opcion != 0) {
                        System.out.println("Opción no válida.");
                    }
                }
            }
        } while (opcion != 5);
    }

    public void insertarProveedor() {
        try {
            System.out.print("Ingrese el nombre del proveedor: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el email del proveedor: ");
            String email = sc.nextLine();
            System.out.print("Ingrese el ID del proveedor: ");
            int id = sc.nextInt();
            System.out.print("Ingrese el teléfono del proveedor: ");
            int telefono = sc.nextInt();
            sc.nextLine();

            ProveedoresModel proveedor = new ProveedoresModel(id, telefono, nombre, email);
            proveedorDAO.insertarProveedor(proveedor);
            System.out.println("Proveedor insertado correctamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar datos. Asegúrese de que los datos sean correctos.");
            sc.nextLine();
        }
    }

    public void modificarProveedor() {
        System.out.print("Ingrese el ID del proveedor a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();

        ProveedoresModel proveedor = proveedorDAO.getIdProveedores(id);
        if (proveedor == null) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        int opcion;
        do {
            System.out.println("Modificar proveedor");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar email");
            System.out.println("3. Modificar teléfono");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Introducir nombre: ");
                    String nombre = sc.nextLine();
                    proveedorDAO.modificarNombreProveedores(nombre, id);
                    System.out.println("Nombre modificado correctamente");
                }
                case 2 -> {
                    System.out.print("Introducir email: ");
                    String email = sc.nextLine();
                    proveedorDAO.modificarEmailProveedor(email, id);
                    System.out.println("Email modificado correctamente");
                }
                case 3 -> {
                    System.out.print("Introducir teléfono: ");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    proveedorDAO.modificarTlfProveedor(telefono, id);
                    System.out.println("Teléfono modificado correctamente");
                }
            
            }
        } while (opcion != 4);
        System.out.println("Saliendo del menú de modificación");
    }

    public void buscarProveedorPorId() {
        try {
            System.out.print("Ingrese el ID del proveedor a buscar: ");
            int id = sc.nextInt();
            sc.nextLine();

            ProveedoresModel proveedor = proveedorDAO.getIdProveedores(id);
            if (proveedor == null) {
                System.out.println("Proveedor no encontrado.");
            } else {
                System.out.println("Proveedor encontrado:");
                System.out.println(proveedor);
            }
        } catch (InputMismatchException e) {
            System.out.println("ID no válido. Ingrese un número.");
            sc.nextLine();
        }
    }

    public void eliminarProveedor() {
        try {
            System.out.print("Ingrese el ID del proveedor a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();

            ProveedoresModel proveedor = proveedorDAO.getIdProveedores(id); // Corregido aquí
            if (proveedor == null) {
                System.out.println("Proveedor no encontrado.");
            } else {
                System.out.print("¿Está seguro de que desea eliminar a este proveedor? (s/n): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("s")) {
                    proveedorDAO.eliminarProveedor(id);
                    System.out.println("Proveedor eliminado correctamente.");
                } else {
                    System.out.println("Eliminación cancelada.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("ID no válido. Ingrese un número.");
            sc.nextLine();
        }
    }

    public ProveedoresModel getIdProveedores(int id) {
        return proveedorDAO.getIdProveedores(id);
    }
}
