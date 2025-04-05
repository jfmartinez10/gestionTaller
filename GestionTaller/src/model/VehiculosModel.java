package model;

public class VehiculosModel {

    String matricula;
    int ano;
    String modelo;
    String marca;
    String dni;

    public VehiculosModel(String matricula, int ano, String marca, String modelo, String dni){
        this.matricula = matricula;
        this.ano = ano;
        this.marca = marca;
        this.modelo=modelo;
        this.dni=dni;
    }

    public String getMatricula() {return matricula;}

    public void setMatricula(String matricula) {this.matricula = matricula;}

    public int getAno() {return ano;}

    public void setAno(int ano) { this.ano = ano;}

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return modelo;}

    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni; }

    

}
