package TrabajoPracticoIntegradorParte1;

import java.util.ArrayList;

public class Fase {
    private int id_fase;
    private String nombre_fase;
    private String descripcion_fase;
    private ArrayList<Participante> participantesFase;
    private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private Boolean acertado;


    public Fase(int id_fase, ArrayList<Ronda> rondas) {
        this.id_fase = id_fase;
        this.rondas = rondas;
        this.participantesFase = new ArrayList<Participante>();
        this.acertado = false;
        
    }
    public int getId_fase() {
        return id_fase;
    }

    public void setId_fase(int id_fase) {
        this.id_fase = id_fase;
    }

    public String getNombre_fase() {
        return nombre_fase;
    }

    public void setNombre_fase(String nombre_fase) {
        this.nombre_fase = nombre_fase;
    }

    public String getDescripcion_fase() {
        return descripcion_fase;
    }

    public void setDescripcion_fase(String descripcion_fase) {
        this.descripcion_fase = descripcion_fase;
    }

    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(ArrayList<Ronda> rondas) {
        this.rondas = rondas;
    }

    public ArrayList<Participante> getParticipantesFase() {
        return participantesFase;
    }

    public void setParticipantesFase(ArrayList<Participante> participantesFase) {
        this.participantesFase = participantesFase;
    }


  
    public void buscarRondas_Fase(ArrayList<Ronda> rondas_coleccion){
        for(Ronda ronda : rondas_coleccion){
            if (ronda.getFase() == this.id_fase){
                getRondas().add(ronda);
               
            }
        }

    }
}




