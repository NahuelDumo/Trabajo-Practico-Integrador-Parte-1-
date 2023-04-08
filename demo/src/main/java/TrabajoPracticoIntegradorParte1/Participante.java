package TrabajoPracticoIntegradorParte1;

public class Participante {
    private int id; 
    private String nombre;
    private int puntaje;

    public Participante(int ide,String nombre, int puntaje) {
        this.id = ide;
        this.nombre = nombre;
        this.puntaje = puntaje;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
}
