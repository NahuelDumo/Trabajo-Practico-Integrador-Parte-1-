package TrabajoPracticoIntegradorParte1;

public class Resultado {
    private int id_resultado;
    private String nombre;
    private int puntaje;
    public Resultado(int id_resultado, String nombre, int puntaje) {
        this.id_resultado = id_resultado;
        this.nombre = nombre;
        this.puntaje = puntaje;
    }
    public int getId_resultado() {
        return id_resultado;
    }
    public void setId_resultado(int id_resultado) {
        this.id_resultado = id_resultado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
}
