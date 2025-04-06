package view;

import dao.ClienteDAO;
import dao.VehiculoDAO;
import java.util.*;
import model.ClienteModel;
import model.VehiculosModel;

public class VehiculosView {
    private Scanner scanner = new Scanner(System.in);
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ClienteView clienteView;

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public VehiculosView(ClienteView clienteView) {
        this.clienteView = clienteView;
    }

    public void agregarVehiculos() {
        System.out.println("\n" + "+".repeat(35));
        System.out.println("   ** Añadir Nuevo Vehículo **");
        System.out.println("+".repeat(35));

        System.out.print("   Matrícula (7 caracteres sin espacios): ");
        String matricula = scanner.nextLine();

        System.out.print("   Año de fabricación: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("   Marca: ");
        String marca = scanner.nextLine();

        System.out.print("   Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("   DNI del cliente propietario: ");
        String dniCliente = scanner.nextLine();

        ClienteModel cliente = clienteDAO.getClienteDNI(dniCliente);

        if (cliente != null) {
            VehiculosModel nuevoVehiculo = new VehiculosModel(matricula, ano, marca, modelo, dniCliente);
            vehiculoDAO.anadirVehiculo(nuevoVehiculo);
            System.out.println("\n   " + GREEN + "¡Vehículo agregado correctamente!" + RESET);
        } else {
            System.out.println("   " + RED + "Error al agregar vehículo:" + RESET + " No existe ningún cliente registrado con el DNI: " + dniCliente + ". Por favor, registre al cliente primero.");
        }
        System.out.println("-".repeat(35));
    }

    public VehiculosModel getVehiculoMatricula() {
        String matricula;
        do {
            System.out.println("\n   ** Buscar Vehículo por Matrícula **");
            System.out.print("   Introduce la matrícula (7 caracteres): ");
            matricula = scanner.nextLine();
            if (matricula.length() != 7) {
                System.out.println("   " + RED + "Error:" + RESET + " La matrícula debe tener exactamente 7 caracteres.");
            }
        } while (matricula.length() != 7);
        VehiculosModel vehiculo = vehiculoDAO.getVehiculoMatricula(matricula);
        if (vehiculo == null) {
            System.out.println("   " + RED + "Error:" + RESET + " No se encontró ningún vehículo con la matrícula: " + matricula + ".");
        }
        return vehiculo;
    }

    public void modificarVehiculos() {
        System.out.println("\n" + "*".repeat(35));
        System.out.println("   ** Modificar Datos del Vehículo **");
        System.out.println("*".repeat(35));

        VehiculosModel vehiculo = this.getVehiculoMatricula();
        if (vehiculo == null) {
            return; 
        }

        String matriculaOriginal = vehiculo.getMatricula();
        int opcion;
        do {
            System.out.println("\n   --- ¿Qué desea modificar del vehículo con matrícula " + matriculaOriginal + "? ---");
            System.out.println("   1. Matrícula");
            System.out.println("   2. Marca");
            System.out.println("   3. Modelo");
            System.out.println("   4. Año de fabricación");
            System.out.println("   5. [VOLVER] al menú anterior");
            System.out.print("   Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            System.out.println("-".repeat(35));

            switch (opcion) {
                case 1 -> {
                    String nuevaMatricula;
                    do {
                        System.out.println("   Introduce la nueva matrícula (7 caracteres): ");
                        nuevaMatricula = scanner.nextLine();
                        if (nuevaMatricula.length() != 7) {
                            System.out.println("   " + RED + "Error:" + RESET + " La matrícula debe tener exactamente 7 caracteres.");
                        } else if (vehiculoDAO.getVehiculoMatricula(nuevaMatricula) != null && !nuevaMatricula.equals(matriculaOriginal)) {
                            System.out.println("   " + RED + "Error:" + RESET + " Ya existe un vehículo registrado con la matrícula: " + nuevaMatricula + ".");
                        } else {
                            break;
                        }

                    } while (true);
                    vehiculoDAO.modificarMatriculaVehiculo(nuevaMatricula);
                    System.out.println("   " + GREEN + "Matrícula modificada correctamente." + RESET);
                    matriculaOriginal = nuevaMatricula; 
                }

                case 2 -> {
                    System.out.println("   Introduce la nueva marca: ");
                    String marca = scanner.nextLine();
                    vehiculoDAO.modificarMarcaVehiculo(matriculaOriginal, marca);
                    System.out.println("   " + GREEN + "Marca modificada correctamente." + RESET);
                }

                case 3 -> {
                    System.out.println("   Introduce el nuevo modelo: ");
                    String modelo = scanner.nextLine();
                    vehiculoDAO.modificarModeloVehiculo(matriculaOriginal, modelo);
                    System.out.println("   " + GREEN + "Modelo modificado correctamente." + RESET);
                }

                case 4 -> {
                    int anio;
                    do {
                        System.out.println("   Introduce el nuevo año de fabricación: ");
                        anio = scanner.nextInt();
                        if (anio < 1769 || anio > 2025) {
                            System.out.println("   " + RED + "Error:" + RESET + " Año inválido. Debe estar entre 1769 y 2025.");
                        } else {
                            break;
                        }
                    } while (anio < 1769 || anio > 2025);
                    vehiculoDAO.modificarAnoVehiculo(matriculaOriginal, anio);
                    System.out.println("   " + GREEN + "Año modificado correctamente." + RESET);
                    scanner.nextLine(); 
                }

                default -> System.out.println("   " + RED + "Opción no válida. Intente nuevamente." + RESET);
            }

        } while (opcion != 5);
        System.out.println("   Volviendo al menú anterior...");
        System.out.println("-".repeat(35));
    }

    public void mostrarVehiculos() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Listado de Vehículos **");
        System.out.println("-".repeat(35));

        ArrayList<VehiculosModel> listaVehiculos = vehiculoDAO.listaVehiculos();
        if (listaVehiculos.isEmpty()) {
            System.out.println("   No hay vehículos registrados en el sistema.");
        } else {
            for (VehiculosModel vehiculo : listaVehiculos) {
                System.out.println("   " + vehiculo);
                System.out.println("   " + "-".repeat(35));
            }
        }
        System.out.println("-".repeat(35));
    }

    public void eliminarVehiculos() {
        System.out.println("\n" + "-".repeat(35));
        System.out.println("   ** Eliminar Vehículo **");
        System.out.println("-".repeat(35));

        VehiculosModel vehiculo = this.getVehiculoMatricula();
        if (vehiculo != null) {
            System.out.print("   ¿Está seguro de que desea eliminar el vehículo con matrícula " + vehiculo.getMatricula() + "? (s/n): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();
            if (confirmacion.equals("s")) {
                vehiculoDAO.eliminarVehiculo(vehiculo);
                System.out.println("   " + GREEN + "Vehículo eliminado correctamente." + RESET);
            } else {
                System.out.println("   Operación de eliminación cancelada.");
            }
        }
        System.out.println("-".repeat(35));
    }
}