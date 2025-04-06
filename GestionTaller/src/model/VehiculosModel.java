package model;

public class VehiculosModel {

    //ATRIBUTOS
    int ano;
    String matricula;
    String modelo;
    String marca;
    String dni;

    //CONSTRUCTOR
    public VehiculosModel(String matricula, int ano, String marca, String modelo, String dni){
        this.matricula = matricula;
        this.ano = ano;
        this.marca = marca;
        this.modelo=modelo;
        this.dni=dni;
    }

    //DIVISIÓN DE GETTERS Y SETTERS
    //GETTERS
    public String getMatricula() {return matricula;}
    public int getAno() {return ano;}
    public String getMarca() {return marca;}
    public String getModelo() {return modelo;}
    public String getDni() {return dni;}
    
    //SETTERS
    public void setMatricula(String matricula) {this.matricula = matricula;}
    public void setAno(int ano) { this.ano = ano;}
    public void setMarca(String marca) {this.marca = marca;}
    public void setModelo(String modelo) {this.modelo = modelo;}
    public void setDni(String dni) {this.dni = dni;}

    @Override
    public String toString() {
        return "Vehiculos [matricula = " + matricula + ", ano =" + ano + ", marca =" + marca + ", modelo =" + modelo + ", dni =" + dni + "]";
    }

}
