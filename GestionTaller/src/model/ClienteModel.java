package model;

public class ClienteModel {

    String dni;
    String nombre;
    String apellido;
    int telefono;
    String email;

    public ClienteModel (String dni, String nombre, String apellido, int telefono, String email){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }


    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
 
    @Override
    public String toString() {
        return "Cliente [dni = " + dni + ", nombre =" + nombre + ", apellido =" + apellido + ", telefono =" + telefono + ", email =" + email + "]";
    }
}