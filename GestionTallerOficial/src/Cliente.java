import java.util.*;
public class Cliente {

    //Atributos
    private String nombreCliente;
    private String contraseña; 

    //Constructor
    public Cliente(String nombreCliente, String contraseña) {
        this.nombreCliente = nombreCliente;
        this.contraseña = contraseña;
    }

    // Getters y setters para todos los atributos
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}