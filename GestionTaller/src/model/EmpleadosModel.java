package model;

public class EmpleadosModel {

    int id_empleado;
    int telefono;

    String nombre;
    String apellido;
   
   public EmpleadosModel (int id_empleado, String nombre, String apellido, int telefono){
        this.id_empleado= id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;

    }

   public int getId_empleado() {return id_empleado;}

   public void setId_empleado(int id_empleado) {this.id_empleado = id_empleado;}

   public String getNombre() {return nombre;}

   public void setNombre(String nombre) {this.nombre = nombre;}

   public String getApellido() {return apellido;}

   public void setApellido(String apellido) {this.apellido = apellido;}

   public int getTelefono() {return telefono;}

   public void setTelefono(int telefono) {this.telefono = telefono;}
   
}
