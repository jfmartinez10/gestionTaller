package view;

import dao.CitasDao;
import java.time.*;
import java.time.format.*;
import java.util.*;
import model.CitasModel;
import model.ClienteModel;
import model.EmpleadosModel;

public class CitasView {
    private Scanner sc = new Scanner(System.in);
    private CitasDao citasDAO = new CitasDao();
    private ClienteView clienteView;
    private EmpleadoView empleadoView;

    public CitasView() {}

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
            System.out.println("Insertar fecha de cita (DIA-MES-AÑO, formato dd-MM-yyyy): ");
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

        System.out.println("Agregue una descripcion a la cita: ");
        String descripcion = sc.nextLine();

        ClienteModel cliente = clienteView.getClienteDNI();
        System.out.println("Introduzca el ID del empleado: ");
        int idEmpleado = sc.nextInt();
        sc.nextLine();
        EmpleadosModel empleado = empleadoView.getIdEmpleado(idEmpleado);
        if (empleado == null) {
            System.out.println("El empleado con el id: " + idEmpleado + " no existe.");
            return;
        }

        CitasModel cita = new CitasModel(cliente, empleado, fecha, hora, descripcion);
        citasDAO.insertarCita(cita);
    }

    public void eliminarCita() {
        CitasModel cita = getCitaId();
        if (cita == null) {
            System.out.println("La cita no existe.");
            return;
        }
        citasDAO.eliminarCita(cita);
        System.out.println("Cita eliminada correctamente");
    }

    public CitasModel getCitaId() {
        System.out.println("Introduzca el ID de la cita: ");
        int idCita = sc.nextInt();
        return citasDAO.getCitaId(idCita);
    }

    public void listarCitas() {
        citasDAO.listarCitas();
    }

    public void listarCitasCliente() {
        System.out.println("Introduzca el dni del cliente: ");
        String dni = sc.nextLine();
        citasDAO.listarCitasCliente(dni);
    }

    public void modificarCita() {
        int opcion;
        CitasModel cita = getCitaId();
        if (cita == null) {
            System.out.println("La cita no existe.");
            return;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaHoy = LocalDate.now();

        do {
            System.out.println("Modificar cita");
            System.out.println("1. Modificar cliente");
            System.out.println("2. Modificar empleado");
            System.out.println("3. Modificar fecha");
            System.out.println("4. Modificar hora");
            System.out.println("5. Modificar descripción");
            System.out.println("6. Volver al menu anterior");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduzca el DNI del nuevo cliente: ");
                    ClienteModel cliente = clienteView.getClienteDNI();
                    citasDAO.modificarClienteCita(cliente, cita);
                    System.out.println("Cliente modificado correctamente");
                }
                case 2 -> {
                    System.out.println("Introduzca el ID del nuevo empleado: ");
                    int idEmpleado = sc.nextInt();
                    sc.nextLine();
                    EmpleadosModel empleado = empleadoView.getIdEmpleado(idEmpleado);
                    if (empleado == null) {
                        System.out.println("El empleado con el id: " + idEmpleado + " no existe.");
                        return;
                    }
                    citasDAO.modificarEmpleadoCita(empleado, cita);
                    System.out.println("Empleado modificado correctamente");
                }
                case 3 -> {
                    String nuevaFecha;
                    boolean fechaValida = false;

                    do {
                        System.out.println("Introduzca la nueva fecha (dd-MM-yyyy): ");
                        nuevaFecha = sc.nextLine();

                        try {
                            LocalDate fechaCita = LocalDate.parse(nuevaFecha, dateFormatter);

                            if (fechaCita.isBefore(fechaHoy)) {
                                System.out.println("Error: La fecha no puede ser anterior a hoy ("
                                        + fechaHoy.format(dateFormatter) + ")");
                            } else {
                                fechaValida = true;
                                citasDAO.modificarFechaCita(cita, nuevaFecha);
                                System.out.println("Fecha modificada correctamente");
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Use dd-MM-yyyy (ej: 25-12-2023)");
                        }
                    } while (!fechaValida);
                }
                case 4 -> {
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

                    sc.nextLine();
                    nuevaHora = String.format("%02d:%02d", horas, minutos);
                    citasDAO.modificarHoraCita(cita, nuevaHora);
                    System.out.println("Hora modificada correctamente a: " + nuevaHora);
                }
                case 5 -> {
                    System.out.println("Introduzca la nueva descripción: ");
                    String descripcion = sc.nextLine();
                    citasDAO.modificarDescripcionCita(cita, descripcion);
                    System.out.println("Descripción modificada correctamente");
                }
                case 6 -> System.out.println("Volviendo al menu anterior");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }
}