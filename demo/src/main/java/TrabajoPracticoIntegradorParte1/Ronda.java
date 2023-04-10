package TrabajoPracticoIntegradorParte1;

import java.util.ArrayList;

public class Ronda {
    private int nro;
    private ArrayList<Partido> partidos;
    private ArrayList<Pronostico> pronosticos;
    private ArrayList<Participante> participantes;


    public Ronda(int nro, ArrayList<Partido> todos_partidos, ArrayList<Pronostico> pronostico,  ArrayList<Participante> participante) {
        this.nro = nro;
        this.partidos = todos_partidos;
        this.pronosticos = pronostico;
        this.participantes = participante;

    }
    //metodos accesores y tomadores


    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getNro() {
        return nro;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }
    public ArrayList<Pronostico> getPronosticos() {
        return pronosticos;
    }


    public void setPronosticos(ArrayList<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }


    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }


    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }
    
}
