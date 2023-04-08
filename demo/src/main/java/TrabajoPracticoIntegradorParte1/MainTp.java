package TrabajoPracticoIntegradorParte1;


import java.util.ArrayList;

public class MainTp {
    public static void main(String[] args) {
        // Obtener los datos de los archivos CSV
        Participante participante_actual = null;
        String equipoAnalizar;
        ArrayList<Ronda> rondas = LectorArchivos.ObtenerRondas();

        // Calcular los puntos de cada participante en cada ronda
        for (Ronda ronda: rondas){
            ArrayList<Partido> partidos = ronda.getPartidos();
            ArrayList<Pronostico> pronosticos = ronda.getPronosticos();
            ArrayList<Participante> participantes = ronda.getParticipantes();
            for (int i = 0; i < pronosticos.size(); i++) {
                Pronostico pronostico = pronosticos.get(i);
                for (Participante participante : participantes){
                    if (pronostico.getParticipante().equals(participante.getNombre())){
                        participante_actual = participante;
                    }
                }
                int puntaje = participante_actual.getPuntaje();
                
                Partido partido = partidos.get(pronostico.getIdPartido()-1);
                equipoAnalizar = pronostico.getNombre_equipo();
                
                if (pronostico.getIdPartido() == partido.getId()){
                    if (partido.getEquipo1().getNombre().equals(equipoAnalizar) ) {
                        puntaje += pronostico.ObtenerResutadoReal(partido, equipoAnalizar);
                        participante_actual.setPuntaje(puntaje);
    
                    } else if (partido.getEquipo2().getNombre().equals(equipoAnalizar)) {
                        puntaje += pronostico.ObtenerResutadoReal(partido, equipoAnalizar);
                        participante_actual.setPuntaje(puntaje);
                        
                    }

                }
            }
            System.out.println("Para la ronda Numero"+ronda.getNro()+"\nLos puntos son:\n");
            for (Participante participante : participantes){
                System.out.println(participante.getNombre()+":"+participante.getPuntaje()+"/n");
            }
        }
    }
}