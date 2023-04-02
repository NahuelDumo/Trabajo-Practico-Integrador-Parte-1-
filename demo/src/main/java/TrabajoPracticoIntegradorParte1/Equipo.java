package TrabajoPracticoIntegradorParte1;

public class Equipo {
    private String nombre;
    private String descripcion;
    //constructor
 
    public Equipo(String nom, String desc){
       this.nombre = nom;
       this.descripcion = desc;

    }
    public Equipo(String nom){
       this.nombre = nom;
    }
    public String getNombre() {
       return nombre;
    }
 
    public void setNombre(String nombre) {
       this.nombre = nombre;
 
    }
 
    public String getDescripcion() {
       return descripcion;
    }
 
    public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
    }
 
 
 }
 
 