package model;

public class ProveedoresModel {

    //ATRIBUTOS
    int id; 
    int telefono;
    String nombre;
    String email;

    //CONSTRUCTOR
    public ProveedoresModel(int id, int telefono, String nombre, String email) {
        this.id = id; 
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    //DIVISIÓN DE GETTERS Y SETTERS
    //GETTERS
    public String getNombre() {return nombre;}
    public String getEmail() {return email;}
    public int getTelefono() {return telefono;}
    public int getId() {return id;}

    //SETTERS
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setEmail(String email) {this.email = email;}
    public void setTelefono(int telefono) {this.telefono = telefono;}
    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Proveedores: [id = " + id + ", nombre = " + nombre + ", email = " + email + ", telefono = " + telefono + "]";
    }
}