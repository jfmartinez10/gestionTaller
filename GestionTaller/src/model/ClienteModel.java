package model;

public class ClienteModel {

    //ATRIBUTOS
    String dni;
    String nombre;
    String apellido;
    int telefono;
    String email;

    //CONSTRUCTOR
    public ClienteModel (String dni, String nombre, String apellido, int telefono, String email){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    //DIVISIÓN DE GETTERS Y SETTERS
    //GETTERS
    public String getDni() {return dni;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public int getTelefono() {return telefono;}
    public String getEmail() {return email;}

    //SETTERS
    public void setDni(String dni) {this.dni = dni;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setTelefono(int telefono) {this.telefono = telefono;}
    public void setEmail(String email) {this.email = email;}
 
    @Override
    public String toString() {
        return "Cliente [dni = " + dni + ", nombre =" + nombre + ", apellido =" + apellido + ", telefono =" + telefono + ", email =" + email + "]";
    }
}