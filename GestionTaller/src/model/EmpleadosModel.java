package model;

public class EmpleadosModel {

    int id; 
    int telefono;

    String nombre;
    String apellido;

    public EmpleadosModel(int id, String nombre, String apellido, int telefono) {
        this.id = id; 
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {this.telefono = telefono;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Empleado [id= " + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + "]";
    }
}