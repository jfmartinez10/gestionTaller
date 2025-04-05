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
            System.out.println("Menu citas cliente");
            System.out.println("1. Agregar cita");
            System.out.println("2. Modificar cita");
            System.out.println("3. Eliminar cita");
            System.out.println("4. Listar citas personales");
            System.out.println("5. Volver al menu anterior");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarCita();
                case 2 -> modificarCita();
                case 3 -> eliminarCita();
                case 4 -> listarCitasCliente();
                case 5 -> System.out.println("Volviendo al menu anterior");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public void agregarCita() {
        System.out.println("Agregar Cita");

        String fecha;
        boolean fechaValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaHoy = LocalDate.now();

        do {
            System.out.println("Insertar fecha de cita (DIA-MES-AnO, formato dd-MM-yyyy): ");
            fecha = sc.nextLine();

            try {
                LocalDate fechaCita = LocalDate.parse(fecha, formatter);

                if (fechaCita.isBefore(fechaHoy)) {
                    System.out.println("Error: La fecha no puede ser anterior a hoy ("
                                       + fechaHoy.format(formatter) + ")");
                } else {
                    fechaValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use dd-MM-yyyy (ej: 25-12-2023)");
            }
        } while (!fechaValida);

        int horas;
        int minutos;
        boolean horaValida = false;
        boolean minutoValido = false;

        System.out.println("Insertar hora para la cita:");
        do {
            System.out.println("Insertar hora: (formato 24H - SOLO INTRODUCIR LA HORA: )");
            horas = sc.nextInt();
            if (horas > 23 || horas < 0) {
                System.out.println("Error: Hora invalida, introduce una hora valida");
            } else {
                horaValida = true;
            }
        } while (!horaValida);

        do {
            System.out.println("Insertar minutos: (xx) (SOLO LOS MINUTOS)");
            minutos = sc.nextInt();
            if (minutos > 59 || minutos < 0) {
                System.out.println("Error: Minutos invalidos, introduce minutos validos");
            } else {
                minutoValido = true;
            }
        } while (!minutoValido);

        String hora = String.format("%02d:%02d", horas, minutos);
        sc.nextLine(); 

        System.out.println("Ingrese el DNI del cliente para la cita:");
        String dniCliente = sc.nextLine();

        ClienteModel cliente = clienteDAO.getClienteDNI(dniCliente);

        if (cliente == null) {
            System.out.println("Error: No se encontró ningún cliente con el DNI proporcionado.");
            return; 
        }

        System.out.println("Agregue una descripcion a la cita: ");
        String descripcion = sc.nextLine();

        CitasModel cita = new CitasModel(cliente, fecha, hora, descripcion); 
        citasDAO.insertarCita(cita);
        System.out.println("Cita agregada correctamente.");
    }

    public void eliminarCita() {
        System.out.println("Eliminar Cita");
        System.out.println("Introduzca su DNI para ver sus citas: ");
        String dniCliente = sc.nextLine();

        ArrayList<CitasModel> citas = citasDAO.listarCitasCliente(dniCliente);

        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas para el DNI proporcionado.");
            return;
        }

        System.out.println("\n--- Sus Citas ---");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
        for (int i = 0; i < citas.size(); i++) {
            CitasModel cita = citas.get(i);
            System.out.print((i + 1) + ". ID: " + cita.getIdCita() +
                             ", Fecha: ");
            try {
                LocalDate fechaCita = LocalDate.parse(cita.getFecha(), dateFormatter); 
                System.out.print(fechaCita.format(dateFormatter));
            } catch (DateTimeParseException e) {
                System.err.println("Error al formatear fecha para ID " + cita.getIdCita() + ": " + e.getMessage() + ". Fecha original: " + cita.getFecha());
                System.out.print(cita.getFecha() + " (formato no reconocido)");
            }
            System.out.println(", Hora: " + cita.getHora() +
                               ", Descripción: " + cita.getDescripcion());
        }

        System.out.print("Seleccione el número de la cita que desea eliminar (o 0 para volver): ");
        int seleccion = sc.nextInt();
        sc.nextLine(); 

        if (seleccion == 0) {
            System.out.println("Volviendo al menú de citas.");
            return;
        }

        if (seleccion > 0 && seleccion <= citas.size()) {
            CitasModel citaAEliminar = citas.get(seleccion - 1);
            citasDAO.eliminarCita(citaAEliminar.getIdCita());
            System.out.println("Cita con ID " + citaAEliminar.getIdCita() + " eliminada correctamente.");
        } else {
            System.out.println("Selección no válida.");
        }
    }

    public CitasModel getCitaId() {
        System.out.println("Introduzca el ID de la cita: ");
        int id = sc.nextInt();
        return citasDAO.getCitaId(id);
    }

    public void listarCitas() {
        citasDAO.listarCitas();
    }

    public void listarCitasCliente() {
        System.out.println("Introduzca el dni del cliente: ");
        String dni = sc.nextLine();
        ArrayList<CitasModel> citas = citasDAO.listarCitasCliente(dni);

        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas para el DNI proporcionado.");
        } else {
            System.out.println("\n--- Citas Personales ---");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (CitasModel cita : citas) {
                System.out.println("ID: " + cita.getIdCita());
                String fechaStr = cita.getFecha();
                if (fechaStr != null) {
                    try {
                        LocalDate fechaCita = LocalDate.parse(fechaStr, dateFormatter);
                        System.out.println("Fecha: " + fechaCita.format(dateFormatter));
                    } catch (DateTimeParseException e) {
                        System.err.println("Error al formatear fecha para ID " + cita.getIdCita() + ": " + e.getMessage() + ". Fecha original: " + fechaStr);
                        System.out.println("Fecha: " + fechaStr + " (formato no reconocido)"); 
                    }
                } else {
                    System.out.println("Fecha: No disponible");
                }
                System.out.println("Hora: " + cita.getHora());
                System.out.println("Descripción: " + cita.getDescripcion());
                System.out.println("---");
            }
        }
    }

    public void modificarCita() {
        System.out.println("Modificar Cita");
        System.out.println("Introduzca su DNI para ver sus citas: ");
        String dniCliente = sc.nextLine();

        ArrayList<CitasModel> citas = citasDAO.listarCitasCliente(dniCliente);

        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas para el DNI proporcionado.");
            return;
        }

        System.out.println("\n--- Sus Citas ---");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < citas.size(); i++) {
            CitasModel cita = citas.get(i);
            String fechaFormateada;
            try {
                LocalDate fechaCita = LocalDate.parse(cita.getFecha());
                fechaFormateada = fechaCita.format(dateFormatter);
            } catch (DateTimeParseException e) {
                System.err.println("Error al formatear fecha para ID " + cita.getIdCita() + ": " + e.getMessage() + ". Fecha original: " + cita.getFecha());
                fechaFormateada = cita.getFecha() + " (formato no reconocido)";
            }

            System.out.println((i + 1) + ". " +
                               "ID: " + cita.getIdCita() + " | " +
                               "Fecha: " + fechaFormateada + " | " +
                               "Hora: " + cita.getHora() + " | " +
                               "Descripción: " + cita.getDescripcion());
        }
        System.out.print("Seleccione el número de la cita que desea modificar (o 0 para volver): ");
        int seleccion = sc.nextInt();
        sc.nextLine(); 

        if (seleccion == 0) {
            System.out.println("Volviendo al menú de citas.");
            return;
        }

        if (seleccion > 0 && seleccion <= citas.size()) {
            CitasModel citaAModificar = citas.get(seleccion - 1);
            menuModificarCitaIndividual(citaAModificar);
        } else {
            System.out.println("Selección no válida.");
        }
    }

    private void menuModificarCitaIndividual(CitasModel cita) {
        int opcion;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaHoy = LocalDate.now();

        do {
            System.out.println("\n--- Modificar Cita (ID: " + cita.getIdCita() + ") ---");
            System.out.println("1. Modificar cliente");
            System.out.println("2. Modificar fecha");
            System.out.println("3. Modificar hora");
            System.out.println("4. Modificar descripción");
            System.out.println("5. Guardar cambios y volver");
            System.out.println("6. Cancelar y volver");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduzca el DNI del nuevo cliente: ");
                    String nuevoDniCliente = sc.nextLine();
                    ClienteModel clienteNuevo = clienteDAO.getClienteDNI(nuevoDniCliente);
                    if (clienteNuevo != null) {
                        citasDAO.modificarClienteCita(clienteNuevo, cita);
                        cita.setCliente(clienteNuevo); // Actualizar el objeto cita local
                        System.out.println("Cliente modificado correctamente.");
                    } else {
                        System.out.println("No se encontró el cliente con el DNI proporcionado.");
                    }
                }
                case 2 -> {
                    String nuevaFecha;
                    boolean fechaValida = false;
                    do {
                        System.out.println("Introduzca la nueva fecha (dd-MM-yyyy): ");
                        nuevaFecha = sc.nextLine();
                        try {
                            LocalDate fechaCita = LocalDate.parse(nuevaFecha, dateFormatter);
                            if (fechaCita.isBefore(fechaHoy)) {
                                System.out.println("Error: La fecha no puede ser anterior a hoy (" + fechaHoy.format(dateFormatter) + ")");
                            } else {
                                citasDAO.modificarFechaCita(cita, nuevaFecha);
                                cita.setFecha(nuevaFecha); // Actualizar el objeto cita local
                                System.out.println("Fecha modificada correctamente.");
                                fechaValida = true;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Use dd-MM-yyyy (ej: 25-12-2023)");
                        }
                    } while (!fechaValida);
                }
                case 3 -> {
                    int horas;
                    int minutos;
                    boolean horaValida = false;
                    boolean minutoValido = false;
                    String nuevaHora;

                    System.out.println("Modificar hora de la cita:");
                    do {
                        System.out.print("Ingrese la nueva hora (0-23): ");
                        horas = sc.nextInt();
                        if (horas < 0 || horas > 23) {
                            System.out.println("Hora inválida. Debe ser entre 0 y 23.");
                        } else {
                            horaValida = true;
                        }
                    } while (!horaValida);

                    do {
                        System.out.print("Ingrese los nuevos minutos (0-59): ");
                        minutos = sc.nextInt();
                        if (minutos < 0 || minutos > 59) {
                            System.out.println("Minutos inválidos. Debe ser entre 0 y 59.");
                        } else {
                            minutoValido = true;
                        }
                    } while (!minutoValido);

                    nuevaHora = String.format("%02d:%02d", horas, minutos);
                    citasDAO.modificarHoraCita(cita, nuevaHora);
                    cita.setHora(nuevaHora); // Actualizar el objeto cita local
                    System.out.println("Hora modificada correctamente a: " + nuevaHora);
                    sc.nextLine(); // Consumir la nueva línea
                }
                case 4 -> {
                    System.out.println("Introduzca la nueva descripción: ");
                    String descripcion = sc.nextLine();
                    citasDAO.modificarDescripcionCita(cita, descripcion);
                    cita.setDescripcion(descripcion); // Actualizar el objeto cita local
                    System.out.println("Descripción modificada correctamente.");
                }
                case 5 -> {
                    System.out.println("Cambios guardados.");
                    opcion = 6; // Para salir del bucle
                }
                case 6 -> System.out.println("Modificación cancelada.");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }
}