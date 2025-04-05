package model;

public class ProveedoresModel {
    int id; 
    int telefono;
    String nombre;
    String email;

    public ProveedoresModel(int id, int telefono, String nombre, String email) {
        this.id = id; 
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {this.telefono = telefono;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Proveedores: [id= " + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + "]";
    }
}