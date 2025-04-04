package model;

public class VehiculosModel {

    String matricula;
    int año;
    String modelo;
    String marca;
    String dni;

    public VehiculosModel(String matricula, int año, String marca, String modelo, String dni){
        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        this.modelo=modelo;
        this.dni=dni;
    }

    public String getMatricula() {return matricula;}

    public void setMatricula(String matricula) {this.matricula = matricula;}

    public int getAño() {return año;}

    public void setAño(int año) { this.año = año;}

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return modelo;}

    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni; }

    

}
