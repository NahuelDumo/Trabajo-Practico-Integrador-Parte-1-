package TrabajoPracticoIntegradorParte1;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class LectorArchivos {

    private static ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private static int contador = 0;
    public static final char SEPARATOR = ',';

    public void convertirPartidos(String[] args) throws IOException, CsvValidationException {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C://Users//nahue//OneDrive//Documentos//GitHub//Trabajo-Practico-Integrador-Parte-1-//demo//src//test//java//demo//partido.csv"));
            String[] nextLine = reader.readNext();
            Ronda rondaActual = null;
            while ((nextLine = reader.readNext()) != null) {
                String nroRonda = nextLine[0];
                if (rondaActual == null || !rondaActual.getNro().equals(nroRonda)) {
                    rondaActual = new Ronda(nroRonda, new ArrayList<Partido>(), new ArrayList<Pronostico>(), new ArrayList<Participante>());
                    rondas.add(rondaActual);
                }
                Equipo equipo1 = new Equipo(nextLine[1]);
                Integer golesEquipo1 = Integer.parseInt(nextLine[2]);
                Integer golesEquipo2 = Integer.parseInt(nextLine[3]);
                Equipo equipo2 = new Equipo(nextLine[4]);
                Partido partido = new Partido(++contador, equipo1, equipo2, golesEquipo1, golesEquipo2);
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
            Participante participanteActual = null;
            contador = 0;
            while ((nextLine = reader.readNext()) != null) {
                contador += 1;
                String nombreParticipante = nextLine[0];
                int idRonda = Integer.parseInt(nextLine[1]); 
                String equipo =  nextLine[2];
                int idPartido = Integer.parseInt(nextLine[3]);
                String resultado = nextLine[4];
                Pronostico pronostico = new Pronostico(nombreParticipante, idRonda,equipo, idPartido, resultado);

                // Agregar el pronóstico a la ronda correspondiente
                Ronda ronda = rondas.get(idRonda - 1); // las rondas se agregan en orden, por eso el -1
                ronda.getPronosticos().add(pronostico);
                
                if (participanteActual == null || !participanteActual.getNombre().equals(nombreParticipante)) {
                    participanteActual = new Participante(contador,nombreParticipante,0);
                    ronda.getParticipantes().add(participanteActual);
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
        return rondas;

    }
}



    


