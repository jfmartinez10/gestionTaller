
public class Vehiculos {

    int matricula;
    int año;
    String marca;

    public Vehiculos(int matricula, int año, String marca){

        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    

}
