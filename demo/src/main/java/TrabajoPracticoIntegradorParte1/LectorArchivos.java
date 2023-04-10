package TrabajoPracticoIntegradorParte1;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class LectorArchivos {

    private static ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private static int contador = 0;
    public static final char SEPARATOR = ',';

    public void convertirPartidos(String[] args) throws IOException, CsvValidationException {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C://Users//nahue//OneDrive//Documentos//GitHub//Trabajo-Practico-Integrador-Parte-1-//demo//src//test//java//demo//partidos.csv"));
            reader.readNext(); // Leemos y descartamos la primera línea
            String[] nextLine = null;
            Ronda rondaActual = null;
            int ultimoNroRonda = 0;
            while ((nextLine = reader.readNext()) != null) {
                int nroRonda = Integer.parseInt(nextLine[0]);
                if (rondas.size() < nroRonda) {
                    rondaActual = new Ronda(nroRonda, new ArrayList<Partido>(), new ArrayList<Pronostico>(), new ArrayList<Participante>());
                    rondas.add(rondaActual);
                    ultimoNroRonda = nroRonda;
                    contador = 0; // Reiniciamos el contador para cada nueva ronda
                } else if (nroRonda != ultimoNroRonda) {
                    System.err.println("Error: se encontró un partido de la ronda " + nroRonda + " después de la ronda " + ultimoNroRonda);
                    return;
                }
                Equipo equipo1 = new Equipo(nextLine[1]);
                Integer golesEquipo1 = Integer.parseInt(nextLine[2]);
                Integer golesEquipo2 = Integer.parseInt(nextLine[3]);
                Equipo equipo2 = new Equipo(nextLine[4]);
                Partido partido = new Partido(rondaActual,++contador, equipo1, equipo2, golesEquipo1, golesEquipo2);
                rondaActual.getPartidos().add(partido);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }
    
    
    public void ConvertirPronostico(String[] args) throws IOException{

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C://Users//nahue//OneDrive//Documentos//GitHub//Trabajo-Practico-Integrador-Parte-1-//demo//src//test//java//demo//Pronostico.csv"));
            reader.readNext();
            String[] nextLine=null;
            Map<String, Participante> participantesRondaActual = new HashMap<>(); // Mapa para almacenar los participantes ya agregados en la ronda actual
            int rondaActual = -1; // Para controlar si se cambió de ronda en el archivo
            contador = 0;
            while ((nextLine = reader.readNext()) != null) {
                String nombreParticipante = nextLine[0];
                int idRonda = Integer.parseInt(nextLine[1]); 
                String equipo =  nextLine[2];
                int idPartido = Integer.parseInt(nextLine[3]);
                String resultado = nextLine[4];
                Pronostico pronostico = new Pronostico(nombreParticipante, idRonda,equipo, idPartido, resultado);
    
                // Verificar si se cambió de ronda en el archivo y reiniciar el mapa de participantes
                if (rondaActual != idRonda) {
                    participantesRondaActual = new HashMap<>();
                    rondaActual = idRonda;
                }
    
                // Agregar el pronóstico a la ronda correspondiente
                Ronda ronda = rondas.get(idRonda - 1);// las rondas se agregan en orden, por eso el -1
                if (pronostico.getRonda() == ronda.getNro()){
                    ronda.getPronosticos().add(pronostico);
                };
                
                // Verificar si el participante ya fue agregado a la ronda actual
                if (!participantesRondaActual.containsKey(nombreParticipante)) {
                    contador += 1;
                    Participante participanteActual = new Participante(contador,nombreParticipante,0);
                    ronda.getParticipantes().add(participanteActual);
                    participantesRondaActual.put(nombreParticipante, participanteActual);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            } 
        }
    }

    public static ArrayList<Ronda> ObtenerRondas() {
        LectorArchivos lectorArchivos = new LectorArchivos();
        try {
            lectorArchivos.convertirPartidos(null);
            lectorArchivos.ConvertirPronostico(null) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rondas;
    }
}



    


