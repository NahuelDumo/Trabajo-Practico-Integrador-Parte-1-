package TrabajoPracticoIntegradorParte1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTp {
    public static void main(String[] args) throws IOException {
        // Leer resultados
        String equipoAnalizar;
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
        int puntos = 0;
        partidos = LectorArchivos.ConvertirPartidos(args);
        pronosticos = LectorArchivos.ConvertirPronostico(args);
        for (int i = 0; i < pronosticos.size(); i++) {
            
            Pronostico pronostico = pronosticos.get(i);
            Partido partido = partidos.get(pronostico.getIdPartido()-1);
            equipoAnalizar = pronostico.getNombre_equipo();
            
            if (pronostico.idPartido == partido.getId()){
                if (partido.getEquipo1().getNombre().equals(equipoAnalizar) ) {
                    puntos += pronostico.ObtenerResutadoReal(partido, equipoAnalizar);


                } else if (partido.getEquipo2().getNombre().equals(equipoAnalizar)) {
                    puntos += pronostico.ObtenerResutadoReal(partido, equipoAnalizar);


                }
                System.out.println("----------------------------------------\nEn la ronda numero " + (i + 1)
                        + " los puntos obtenidos fueron: " + puntos
                        + "\n----------------------------------------\n");
                System.out.println("Los equipos participantes fueron:\n-" + partido.getEquipo1().getNombre() + "\n-"
                        + partido.getEquipo2().getNombre() + "\nResultado Para " + equipoAnalizar + ": "
                        + partido.resultado(equipoAnalizar));
                Scanner scanner = new Scanner(System.in);
                System.out.println("Presiona Enter para continuar de Ronda");
                scanner.nextLine();
            }
        }

        // mostrar los puntos
        System.out.println("Los puntos obtenidos por el usuario fueron:");
        System.out.println(puntos);

    }
}
