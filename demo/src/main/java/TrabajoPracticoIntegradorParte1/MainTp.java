package TrabajoPracticoIntegradorParte1;

import java.util.ArrayList;

public class MainTp {
    public static void main(String[] args) {
        // Obtener los datos de los archivos CSV
        
        ArrayList<Fase> fases = LectorArchivos.ObtenerFases();

        for(Fase fase: fases){
            
            System.out.println("\n*****\nFase: " 
            + fase.getId_fase()+"\n*****\n");

            ArrayList<Ronda> rondas = fase.getRondas();
            
            // Calcular los puntos de cada participante en cada ronda
            for (Ronda ronda: rondas){
               
                System.out.println("------------------------------------\nPara la ronda Numero "+ronda.getNro()+"\nLos puntos son:\n");
                ArrayList<Participante> participantes = ronda.getParticipantes();
                ronda.obtenerPuntajeRonda();


                for (Participante participante : participantes){
                    participante.acertoRonda(ronda);
                    if( participante.getPuntaje() > (ronda.getPartidos().size()*Partido.getResultados().get(0).getPuntaje())){
                        participante.SumarPuntajeFase(Partido.getResultados().get(4).getPuntaje());
                    };    
                    System.out.println(participante.getNombre()+":"+participante.getPuntaje());  
                    participante.ResetearPuntajeRonda();
                        
                }
                
                
            
            }
            System.out.println("\n------------------------------------\n");
            for(Participante participante : fase.getParticipantesFase())
            {
                participante.acertofase(fase);
                System.out.println("El puntaje de la fase para el participante "+participante.getNombre()+" fue de: "+participante.getPuntaje_fase()+" puntos\n"
                );
            }
            System.out.println("\n------------------------------------\n");

        }

    }
}

