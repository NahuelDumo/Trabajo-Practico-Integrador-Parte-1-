import java.util.List;

public class Ronda {
    private String nro;
    private List<Partido> partidos;

    public Ronda(String nro, List<Partido> todos_partidos) {
        this.nro = nro;
        this.partidos = todos_partidos;

    }
    //metodos accesores y tomadores


    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getNro() {
        return nro;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
}
