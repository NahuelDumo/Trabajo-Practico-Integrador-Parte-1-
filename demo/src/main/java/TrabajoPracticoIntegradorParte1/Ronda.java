package TrabajoPracticoIntegradorParte1;

import java.util.ArrayList;

public class Ronda {
    private int fase;
    private int nro;
    private ArrayList<Partido> partidos;
    private ArrayList<Pronostico> pronosticos;
    private ArrayList<Participante> participantes;


    public Ronda(int fas, int nro, ArrayList<Partido> todos_partidos, ArrayList<Pronostico> pronostico,
            ArrayList<Participante> participante) {
        this.fase = fas;
        this.nro = nro;
        this.partidos = todos_partidos;
        this.pronosticos = pronostico;
        this.participantes = participante;

    }
    // metodos accesores y tomadores

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

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

    public void obtenerPuntajeRonda () {

        String equipoAnalizar;
        for (Participante participante : participantes) {
            int puntaje = 0;
            for (Pronostico pronostico : pronosticos) {

                if (pronostico.getParticipante().equals(participante.getNombre())) {

                    Partido partido = partidos.get(pronostico.getIdPartido() - 1);
                    equipoAnalizar = pronostico.getNombre_equipo();

                    if (pronostico.getIdPartido() == partido.getId()) {
                        puntaje += pronostico.ObtenerResutadoReal(partido, equipoAnalizar);


                    
                    }
                }

            }
            participante.sumarPuntajeRonda(puntaje);
            participante.SumarPuntajeFase(puntaje);

        }

    }

}
