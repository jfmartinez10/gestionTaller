package model;

public class CitasModel {
    private ClienteModel cliente;
    private int id;
    private String fecha;
    private String hora;
    private String descripcion;

    // Constructor sin ID (para cuando se crea una nueva cita)
    public CitasModel(ClienteModel cliente, String fecha, String hora, String descripcion) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    // Constructor con ID (para cuando se recupera una cita de la base de datos)
    public CitasModel(ClienteModel cliente, int id, String fecha, String hora, String descripcion) {
        this.cliente = cliente;
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    // Getters
    public int getIdCita() { return id; }
    public ClienteModel getCliente() { return cliente; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getDescripcion() { return descripcion; }

    // Setters
    public void setIdCita(int id) { this.id = id; }
    public void setCliente(ClienteModel cliente) { this.cliente = cliente; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}