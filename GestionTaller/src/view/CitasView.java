package view;

import dao.CitasDao;
import dao.ClienteDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import model.CitasModel;
import model.ClienteModel;

public class CitasView {
    private Scanner sc = new Scanner(System.in);
    private CitasDao citasDAO = new CitasDao();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ClienteView clienteView;
    private EmpleadoView empleadoView;

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public CitasView(ClienteView clienteView, EmpleadoView empleadoView) {
        this.clienteView = clienteView;
        this.empleadoView = empleadoView;
    }

    public void setClienteView(ClienteView clienteView) {
        this.clienteView = clienteView;
    }

    public void setEmpleadoView(EmpleadoView empleadoView) {
        this.empleadoView = empleadoView;
    }

    public void menuCitasCliente() {
        
        int opcion;
        do {
            System.out.println("\n" + "=".repeat(34));
            System.out.println("  ** Menú de Citas **");
            System.out.println("=".repeat(34));
            System.out.println("  1. [AGENDAR] Nueva cita");
            System.out.println("  2. [MODIFICAR] Cita existente");
            System.out.println("  3. [CANCELAR] Cita");
            System.out.println("  4. [VER] Mis citas");
            System.out.println("  5. [VOLVER] Al menú principal");
            System.out.print("  Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 
            System.out.println("-".repeat(34)); 

            switch (opcion) {
                case 1 -> agregarCita();
                case 2 -> modificarCita();
                case 3 -> eliminarCita();
                case 4 -> listarCitasCliente();
                case 5 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println(RED + "Opción no válida. Intente nuevamente." + RESET);
            }
        } while (opcion != 5);
    }

    public void agregarCita() {
        System.out.println("\n" + "+".repeat(34));
        System.out.println("  ** Agendar Nueva Cita **");
        System.out.println("+".repeat(34));

        String fecha;
        boolean fechaValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaHoy = LocalDate.now();

        do {
            System.out.println("  Ingrese la fecha de la cita (DIA-MES-AnO, formato dd-MM-yyyy): ");
            fecha = sc.nextLine();

            try {
                LocalDate fechaCita = LocalDate.parse(fecha, formatter);

                if (fechaCita.isBefore(fechaHoy)) {
                    System.out.println(RED + "Error:" + RESET + " La fecha no puede ser anterior a hoy ("
                            + fechaHoy.format(formatter) + ")");
                } else {
                    fechaValida = true;
                }

            } catch (DateTimeParseException e) {
                System.out.println(RED + "Error:" + RESET + " Formato de fecha incorrecto. Use dd-MM-yyyy (ej: 25-12-2023)");
            }
        } while (!fechaValida);

        int horas;
        int minutos;
        boolean horaValida = false;
        boolean minutoValido = false;

        System.out.println("\n  -- Ingrese la hora para la cita --");
        do {
            System.out.print("    Hora (formato 24H - SOLO la hora, ej: 14): ");
            horas = sc.nextInt();
            if (horas > 23 || horas < 0) {
                System.out.println(RED + "Error:" + RESET + " Hora inválida, introduce una hora válida (0-23).");
            } else {
                horaValida = true;
            }
        } while (!horaValida);

        do {
            System.out.print("    Minutos (formato xx - SOLO los minutos, ej: 30): ");
            minutos = sc.nextInt();
            if (minutos > 59 || minutos < 0) {
                System.out.println(RED + "Error:" + RESET + " Minutos inválidos, introduce minutos válidos (0-59).");
            } else {
                minutoValido = true;
            }
        } while (!minutoValido);

        String hora = String.format("%02d:%02d", horas, minutos);
        sc.nextLine(); 

        System.out.println("\n  -- Ingrese el DNI del cliente para la cita --");
        String dniCliente = sc.nextLine();

        ClienteModel cliente = clienteDAO.getClienteDNI(dniCliente);

        if (cliente == null) {
            System.out.println(RED + "Error:" + RESET + " No se encontró ningún cliente con el DNI proporcionado.");
            return;
        }

        System.out.println("\n  -- Agregue una descripción para la cita --");
        String descripcion = sc.nextLine();

        CitasModel cita = new CitasModel(cliente, fecha, hora, descripcion);
        citasDAO.insertarCita(cita);
        System.out.println("\n  " + GREEN + "¡Cita agendada correctamente!" + RESET);
        System.out.println("-".repeat(34));
    }

    public void eliminarCita() {
        System.out.println("\n" + "-".repeat(34));
        System.out.println("  ** Cancelar Cita **");
        System.out.println("-".repeat(34));

        System.out.println("  Ingrese su DNI para ver sus citas: ");
        String dniCliente = sc.nextLine();

        ArrayList<CitasModel> citas = citasDAO.listarCitasCliente(dniCliente);

        if (citas.isEmpty()) {
            System.out.println(RED + "Error:" + RESET + " No se encontraron citas para el DNI proporcionado.");
            return;
        }

        System.out.println("\n  --- Sus Citas Encontradas ---");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < citas.size(); i++) {
            CitasModel cita = citas.get(i);
            System.out.print("  " + (i + 1) + ". ID: " + cita.getIdCita() +
                               ", Fecha: ");
            try {
                LocalDate fechaCita = LocalDate.parse(cita.getFecha(), dateFormatter);
                System.out.print(fechaCita.format(dateFormatter));
            } catch (DateTimeParseException e) {
                System.err.println(RED + "Error al formatear fecha para ID " + cita.getIdCita() + ":" + RESET + " " + e.getMessage() + ". Fecha original: " + cita.getFecha());
                System.out.print(cita.getFecha() + " (formato no reconocido)");
            }
            System.out.println(", Hora: " + cita.getHora() +
                                ", Descripción: " + cita.getDescripcion());
        }

        System.out.print("\n  Seleccione el número de la cita que desea cancelar (o 0 para volver): ");
        int seleccion = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea

        if (seleccion == 0) {
            System.out.println("  Volviendo al menú de citas.");
            return;
        }

        if (seleccion > 0 && seleccion <= citas.size()) {
            CitasModel citaAEliminar = citas.get(seleccion - 1);
            citasDAO.eliminarCita(citaAEliminar.getIdCita());
            System.out.println("  " + GREEN + "Cita con ID " + citaAEliminar.getIdCita() + " cancelada correctamente." + RESET);
        } else {
            System.out.println(RED + "Selección no válida." + RESET);
        }
        System.out.println("-".repeat(34));
    }

    public CitasModel getCitaId() {
        System.out.println("  Introduzca el ID de la cita: ");
        int id = sc.nextInt();
        return citasDAO.getCitaId(id);
    }

    public void listarCitas() {
        citasDAO.listarCitas();
    }

    public void listarCitasCliente() {
        System.out.println("\n" + "-".repeat(34));
        System.out.println("  ** Mis Citas Agendadas **");
        System.out.println("-".repeat(34));

        System.out.println("  Ingrese su DNI para ver sus citas personales: ");
        String dni = sc.nextLine();
        ArrayList<CitasModel> citas = citasDAO.listarCitasCliente(dni);

        if (citas.isEmpty()) {
            System.out.println(RED + "No se encontraron citas para el DNI proporcionado." + RESET);
        } else {
            System.out.println("\n  --- Listado de sus Citas ---");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (CitasModel cita : citas) {
                System.out.println("  ID: " + cita.getIdCita());
                String fechaStr = cita.getFecha();
                if (fechaStr != null) {
                    try {
                        LocalDate fechaCita = LocalDate.parse(fechaStr, dateFormatter);
                        System.out.println("  Fecha: " + fechaCita.format(dateFormatter));
                    } catch (DateTimeParseException e) {
                        System.err.println(RED + "Error al formatear fecha para ID " + cita.getIdCita() + ":" + RESET + " " + e.getMessage() + ". Fecha original: " + fechaStr);
                        System.out.println("  Fecha: " + fechaStr + " (formato no reconocido)");
                    }
                } else {
                    System.out.println("  Fecha: No disponible");
                }
                System.out.println("  Hora: " + cita.getHora());
                System.out.println("  Descripción: " + cita.getDescripcion());
                System.out.println("  " + "-".repeat(34));
            }
        }
        System.out.println("-".repeat(34));
    }

    public void modificarCita() {
        System.out.println("\n" + "=".repeat(34));
        System.out.println("  ** Modificar Cita **");
        System.out.println("=".repeat(34));

        System.out.println("  Ingrese su DNI para ver las citas que puede modificar: ");
        String dniCliente = sc.nextLine();

        ArrayList<CitasModel> citas = citasDAO.listarCitasCliente(dniCliente);

        if (citas.isEmpty()) {
            System.out.println(RED + "Error:" + RESET + " No se encontraron citas para el DNI proporcionado.");
            return;
        }

        System.out.println("\n  --- Sus Citas Encontradas ---");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < citas.size(); i++) {
            CitasModel cita = citas.get(i);
            String fechaFormateada;
            try {
                LocalDate fechaCita = LocalDate.parse(cita.getFecha());
                fechaFormateada = fechaCita.format(dateFormatter);
            } catch (DateTimeParseException e) {
                System.err.println(RED + "Error al formatear fecha para ID " + cita.getIdCita() + ":" + RESET + " " + e.getMessage() + ". Fecha original: " + cita.getFecha());
                fechaFormateada = cita.getFecha() + " (formato no reconocido)";
            }

            System.out.println("  " + (i + 1) + ". " +
                                "ID: " + cita.getIdCita() + " | " +
                                "Fecha: " + fechaFormateada + " | " +
                                "Hora: " + cita.getHora() + " | " +
                                "Descripción: " + cita.getDescripcion());
        }
        System.out.print("\n  Seleccione el número de la cita que desea modificar (o 0 para volver): ");
        int seleccion = sc.nextInt();
        sc.nextLine(); 

        if (seleccion == 0) {
            System.out.println("  Volviendo al menú de citas.");
            return;
        }

        if (seleccion > 0 && seleccion <= citas.size()) {
            CitasModel citaAModificar = citas.get(seleccion - 1);
            menuModificarCitaIndividual(citaAModificar);
        } else {
            System.out.println(RED + "Selección no válida." + RESET);
        }
        System.out.println("-".repeat(34));
    }

    private void menuModificarCitaIndividual(CitasModel cita) {
        int opcion;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaHoy = LocalDate.now();

        do {
            System.out.println("\n" + "*".repeat(34));
            System.out.println("  ** Modificando Cita (ID: " + cita.getIdCita() + ") **");
            System.out.println("*".repeat(34));
            System.out.println("  1. [CAMBIAR] Cliente");
            System.out.println("  2. [CAMBIAR] Fecha");
            System.out.println("  3. [CAMBIAR] Hora");
            System.out.println("  4. [CAMBIAR] Descripción");
            System.out.println("  5. [GUARDAR] Cambios y volver");
            System.out.println("  6. [CANCELAR] Y volver");
            System.out.print("  Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 
            System.out.println("-".repeat(34));

            switch (opcion) {
                case 1 -> {
                    System.out.println("  Ingrese el DNI del nuevo cliente: ");
                    String nuevoDniCliente = sc.nextLine();
                    ClienteModel clienteNuevo = clienteDAO.getClienteDNI(nuevoDniCliente);
                    if (clienteNuevo != null) {
                        citasDAO.modificarClienteCita(clienteNuevo, cita);
                        cita.setCliente(clienteNuevo); 
                        System.out.println("  " + GREEN + "Cliente modificado correctamente." + RESET);
                    } else {
                        System.out.println(RED + "Error:" + RESET + " No se encontró el cliente con el DNI proporcionado.");
                    }
                }

                case 2 -> {
                    String nuevaFecha;
                    boolean fechaValida = false;
                    do {
                        System.out.println("  Ingrese la nueva fecha (dd-MM-yyyy): ");
                        nuevaFecha = sc.nextLine();
                        try {
                            LocalDate fechaCita = LocalDate.parse(nuevaFecha, dateFormatter);
                            if (fechaCita.isBefore(fechaHoy)) {
                                System.out.println(RED + "Error:" + RESET + " La fecha no puede ser anterior a hoy (" + fechaHoy.format(dateFormatter) + ")");
                            } else {
                                citasDAO.modificarFechaCita(cita, nuevaFecha);
                                cita.setFecha(nuevaFecha); 
                                System.out.println("  " + GREEN + "Fecha modificada correctamente." + RESET);
                                fechaValida = true;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println(RED + "Error:" + RESET + " Formato de fecha incorrecto. Use dd-MM-yyyy (ej: 25-12-2023)");
                        }
                    } while (!fechaValida);
                }

                case 3 -> {
                    int horas;
                    int minutos;
                    boolean horaValida = false;
                    boolean minutoValido = false;
                    String nuevaHora;

                    System.out.println("  Modificar hora de la cita:");
                    do {
                        System.out.print("    Nueva hora (0-23): ");
                        horas = sc.nextInt();
                        if (horas < 0 || horas > 23) {
                            System.out.println(RED + "Error:" + RESET + " Hora inválida. Debe ser entre 0 y 23.");
                        } else {
                            horaValida = true;
                        }
                    } while (!horaValida);

                    do {
                        System.out.print("    Nuevos minutos (0-59): ");
                        minutos = sc.nextInt();
                        if (minutos < 0 || minutos > 59) {
                            System.out.println(RED + "Error:" + RESET + " Minutos inválidos. Debe ser entre 0 y 59.");
                        } else {
                            minutoValido = true;
                        }
                    } while (!minutoValido);

                    nuevaHora = String.format("%02d:%02d", horas, minutos);
                    citasDAO.modificarHoraCita(cita, nuevaHora);
                    cita.setHora(nuevaHora); 
                    System.out.println("  " + GREEN + "Hora modificada correctamente a: " + nuevaHora + RESET);
                    sc.nextLine(); 
                }

                case 4 -> {
                    System.out.println("  Ingrese la nueva descripción: ");
                    String descripcion = sc.nextLine();
                    citasDAO.modificarDescripcionCita(cita, descripcion);
                    cita.setDescripcion(descripcion); 
                    System.out.println("  " + GREEN + "Descripción modificada correctamente." + RESET);
                }

                case 5 -> {
                    System.out.println("  " + GREEN + "Cambios guardados." + RESET);
                    opcion = 6; 
                }
                default -> System.out.println(RED + "Opción no válida. Intente nuevamente." + RESET);
            }
        } while (opcion != 6);
        System.out.println("  " + RED + "Modificación cancelada." + RESET);
        System.out.println("*".repeat(34));
    }
}