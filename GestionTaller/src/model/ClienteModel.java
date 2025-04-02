package model;

public class ClienteModel {

    String DNI;
    String nombre;
    String apellido;
    int telefono;
    String email;
    String contraseña;

    public ClienteModel (String DNI, String nombre, String apellido, int telefono, String email, String contraseña){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contraseña=contraseña;
        
    }


    public String getDNI() {return DNI; }

    public void setDNI(String DNI) {this.DNI = DNI; }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getContraseña() {return contraseña;}

    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
}