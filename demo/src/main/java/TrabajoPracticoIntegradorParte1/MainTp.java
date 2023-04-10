package TrabajoPracticoIntegradorParte1;

import java.util.ArrayList;

public class MainTp {
    public static void main(String[] args) {
        // Obtener los datos de los archivos CSV
        String equipoAnalizar;
        ArrayList<Ronda> rondas = LectorArchivos.ObtenerRondas();

        // Calcular los puntos de cada participante en cada ronda
        for (Ronda ronda: rondas){
            System.out.println("Para la ronda Numero "+ronda.getNro()+"\nLos puntos son:\n");
            ArrayList<Partido> partidos = ronda.getPartidos();
            ArrayList<Pronostico> pronosticos = ronda.getPronosticos();
            ArrayList<Participante> participantes = ronda.getParticipantes();

            
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
                participante.setPuntaje(puntaje);
            }

            
            for (Participante participante : participantes){
                    System.out.println(participante.getNombre()+":"+participante.getPuntaje());
            }
            System.out.println();
        }
    }
}
