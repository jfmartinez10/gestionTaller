package view;

import dao.ProveedoresDAO;
import java.util.*;
import model.ProveedoresModel;

public class ProveedoresView {

    private Scanner sc = new Scanner(System.in);
    private ProveedoresDAO proveedorDAO = new ProveedoresDAO();

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public void mostrarMenuProveedor() {
        
        int opcion;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("   ** Menú de Proveedores **");
            System.out.println("=".repeat(35));
            System.out.println("   1. [AGREGAR] Nuevo Proveedor");
            System.out.println("   2. [EDITAR] Datos de Proveedor");
            System.out.println("   3. [BUSCAR] Proveedor por ID");
            System.out.println("   4. [MOSTRAR] Lista de Proveedores");
            System.out.println("   5. [BORRAR] Proveedor");
            System.out.println("   6. [SALIR] del Menú de Proveedores");
            System.out.print("   Ingrese una opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("   " + RED + "Error:" + RESET + " Opción no válida. Ingrese un número.");
                sc.nextLine(); 
                opcion = 0; 
            }
            System.out.println("-".repeat(35)); 

            switch (opcion) {
                case 1 -> insertarProveedor();
                case 2 -> modificarProveedor();
                case 3 -> buscarProveedorPorId();
                case 4 -> mostrarProveedores();
                case 5 -> eliminarProveedor();
                default -> {
                    if (opcion != 0) {
                        System.out.println("   Opción no válida.");
                    }
                }
            }

        } while (opcion != 6);
        System.out.println("Saliendo del menú de proveedores...");
    }

    public void insertarProveedor() {
        System.out.println("\n" + "+".repeat(35));
        System.out.println("   ** Agregar Nuevo Proveedor **");
        System.out.println("+".repeat(35));

        try {
            System.out.print("   Ingrese el nombre del proveedor: ");
            String nombre = sc.nextLine();
            System.out.print("   Ingrese el email del proveedor: ");
            String email = sc.nextLine();
            System.out.print("   Ingrese el ID del proveedor: ");
            int id = sc.nextInt();
            System.out.print("   Ingrese el teléfono del proveedor: ");
            int telefono = sc.nextInt();
            sc.nextLine(); 

            ProveedoresModel proveedor = new ProveedoresModel(id, telefono, nombre, email);
            proveedorDAO.insertarProveedor(proveedor);
            System.out.println("\n   " + GREEN + "Proveedor agregado correctamente." + RESET);

        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " Al ingresar datos. Asegúrese de que el ID y el teléfono sean números.");
            sc.nextLine(); 
        }
        System.out.println("-".repeat(35));
    }

    public void modificarProveedor() {
        System.out.println("\n" + "*".repeat(35));
        System.out.println("   ** Editar Datos de Proveedor **");
        System.out.println("*".repeat(35));

        System.out.print("   Ingrese el ID del proveedor a editar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); 

            ProveedoresModel proveedor = proveedorDAO.getIdProveedores(id);
            if (proveedor == null) {
                System.out.println("   " + RED + "Error:" + RESET + " Proveedor no encontrado con el ID: " + id + ".");
                return;
            }

            int opcion;
            do {
                System.out.println("\n   --- ¿Qué desea modificar del proveedor con ID " + id + "? ---");
                System.out.println("   1. Nombre");
                System.out.println("   2. Email");
                System.out.println("   3. Teléfono");
                System.out.println("   4. [VOLVER] al menú de modificación");
                System.out.print("   Ingrese una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); 

                System.out.println("-".repeat(55));

                switch (opcion) {
                    case 1 -> {
                        System.out.print("   Introducir nuevo nombre: ");
                        String nombre = sc.nextLine();
                        proveedorDAO.modificarNombreProveedores(nombre, id);
                        System.out.println("   " + GREEN + "Nombre modificado correctamente." + RESET);
                    }

                    case 2 -> {
                        System.out.print("   Introducir nuevo email: ");
                        String email = sc.nextLine();
                        proveedorDAO.modificarEmailProveedor(email, id);
                        System.out.println("   " + GREEN + "Email modificado correctamente." + RESET);
                    }

                    case 3 -> {
                        System.out.print("   Introducir nuevo teléfono: ");
                        try {
                            int telefono = sc.nextInt();
                            sc.nextLine(); // Consume la nueva línea
                            proveedorDAO.modificarTlfProveedor(telefono, id);
                            System.out.println("   " + GREEN + "Teléfono modificado correctamente." + RESET);
                        } catch (InputMismatchException e) {
                            System.out.println("   " + RED + "Error:" + RESET + " Teléfono debe ser un número.");
                            sc.nextLine(); // Limpiar el buffer
                        }
                    }
                    default -> System.out.println("   Opción no válida.");
                }

            } while (opcion != 4);
            System.out.println("Volviendo al menú de modificación de proveedor...");

        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " ID de proveedor no válido. Debe ser un número.");
            sc.nextLine(); 
        }
        System.out.println("-".repeat(35));
    }

    public void buscarProveedorPorId() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Buscar Proveedor por ID **");
        System.out.println("-".repeat(35));

        try {
            System.out.print("   Ingrese el ID del proveedor a buscar: ");
            int id = sc.nextInt();
            sc.nextLine(); 

            ProveedoresModel proveedor = proveedorDAO.getIdProveedores(id);
            if (proveedor == null) {
                System.out.println("   " + RED + "Error:" + RESET + " Proveedor no encontrado con el ID: " + id + ".");
            } else {
                System.out.println("   Proveedor encontrado:");
                System.out.println("   " + proveedor);
            }
        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " ID no válido. Ingrese un número.");
            sc.nextLine(); 
        }
        System.out.println("-".repeat(35));
    }

    public void mostrarProveedores() {
        ArrayList<ProveedoresModel> proveedores = proveedorDAO.listarProveedores();
        System.out.println("Lista de Proveedores: ");
        for (ProveedoresModel proveedor : proveedores) {
            System.out.println(proveedor.toString());
        }
    }
    
    public void eliminarProveedor() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Borrar Proveedor **");
        System.out.println("-".repeat(35));

        try {
            System.out.print("   Ingrese el ID del proveedor a borrar: ");
            int id = sc.nextInt();
            sc.nextLine(); 

            ProveedoresModel proveedor = proveedorDAO.getIdProveedores(id);
            if (proveedor == null) {
                System.out.println("   " + RED + "Error:" + RESET + " Proveedor no encontrado con el ID: " + id + ".");
            } else {
                System.out.print("   ¿Está seguro de que desea borrar a este proveedor? (s/n): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("s")) {
                    proveedorDAO.eliminarProveedor(id);
                    System.out.println("   " + GREEN + "Proveedor borrado correctamente." + RESET);
                } else {
                    System.out.println("   Borrado cancelado.");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("   " + RED + "Error:" + RESET + " ID no válido. Ingrese un número.");
            sc.nextLine(); // Limpiar el buffer
        }
        System.out.println("-".repeat(35));
    }

    public ProveedoresModel getIdProveedores(int id) {
        return proveedorDAO.getIdProveedores(id);
    }
}