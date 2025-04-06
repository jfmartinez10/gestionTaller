package model;

public class EmpleadosModel {

    //ATRIBUTOS
    int id; 
    int telefono;
    String nombre;
    String apellido;

    //CONSTRUCTOR
    public EmpleadosModel(int id, String nombre, String apellido, int telefono) {
        this.id = id; 
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    //DIVISIÓN DE GETTERS Y SETTERS
    //GETTERS
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public int getTelefono() {return telefono;}
    public int getId() {return id;}

    //SETTERS
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setTelefono(int telefono) {this.telefono = telefono;}
    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Empleado [id= " + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + "]";
    }
}