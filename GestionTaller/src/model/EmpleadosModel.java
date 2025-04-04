package model;

public class EmpleadosModel {

    int idEmpleado; // Cambiado a idEmpleado
    int telefono;

    String nombre;
    String apellido;

    public EmpleadosModel(int idEmpleado, String nombre, String apellido, int telefono) {
        this.idEmpleado = idEmpleado; // Cambiado a idEmpleado
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public int getIdEmpleado() { // Cambiado a getIdEmpleado
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) { // Cambiado a setIdEmpleado
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}