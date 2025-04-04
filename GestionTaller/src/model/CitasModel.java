//CREACIÓN DE CITASMODEL.JAVA
package model;

public class CitasModel {
    private int idCita;
    private ClienteModel cliente;
    private EmpleadosModel empleado;
    private String fecha;
    private String hora;
    private String descripcion;

    // Constructor
    public CitasModel(ClienteModel cliente, EmpleadosModel empleado, String fecha, String hora, String descripcion) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    // Getters
    public int getIdCita() {
        return idCita;
    }
    public ClienteModel getCliente() {
        return cliente;
    }
    public EmpleadosModel getEmpleado() {
        return empleado;
    }
    public String getFecha() {
        return fecha;
    }
    public String getHora() {
        return hora;
    }
    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }
    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
    public void setEmpleado(EmpleadosModel empleado) {
        this.empleado = empleado;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}