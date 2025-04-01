package model;

public class VehiculosModel {

    String matricula;
    int año;
    String modelo;
    String marca;

    public VehiculosModel(String matricula, int año, String marca, String modelo){
        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        this.modelo=modelo;
    }

    public String getMatricula() {return matricula;}

    public void setMatricula(String matricula) {this.matricula = matricula;}

    public int getAño() {return año;}

    public void setAño(int año) { this.año = año;}

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    

}
