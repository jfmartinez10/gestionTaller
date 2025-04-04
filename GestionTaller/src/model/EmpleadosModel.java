package model;

public class EmpleadosModel {

    int id; // Cambiado a idEmpleado
    int telefono;

    String nombre;
    String apellido;

    public EmpleadosModel(int id, String nombre, String apellido, int telefono) {
        this.id = id; // Cambiado a idEmpleado
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
}