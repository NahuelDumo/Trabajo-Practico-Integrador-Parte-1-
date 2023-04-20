package TPI_AP;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils.Null;

import com.opencsv.CSVReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LectorArchivos {
    private static ArrayList<Fase> fases = new ArrayList<Fase>();
    private static ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private static int contador = 0;
    public static final char SEPARATOR = ',';
 

    public void convertirPartidos(String[] args,String DireccionBD) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DireccionBD);
            String query = "SELECT * FROM Partidos ORDER BY Ronda, idPartido";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
    
            Ronda rondaActual = null;
            int ultimoNroRonda = 0;
            while (resultSet.next()) {
                int fase_actual = resultSet.getInt("Fase");
                int nroRonda = resultSet.getInt("Ronda");
                if (rondas.size() < nroRonda) {
                    rondaActual = new Ronda(fase_actual ,nroRonda, new ArrayList<Partido>(), new ArrayList<Pronostico>(), new ArrayList<Participante>());
                    rondas.add(rondaActual);
                    ultimoNroRonda = nroRonda;
                    contador = 0; // Reiniciamos el contador para cada nueva ronda
                } else if (nroRonda != ultimoNroRonda) {
                    System.err.println("Error: se encontró un partido de la ronda " + nroRonda + " después de la ronda " + ultimoNroRonda);
                    return;
                }
                if (fases.size() < fase_actual) {
                    Fase fase = new Fase(fase_actual, new ArrayList<Ronda>());
                    fases.add(fase);
                }
                Equipo equipo1 = new Equipo(resultSet.getString("Equipo1"));
                Integer golesEquipo1 = resultSet.getInt("golesEquipo1");
                Integer golesEquipo2 = resultSet.getInt("golesEquipo2");
                Equipo equipo2 = new Equipo(resultSet.getString("Equipo2"));
                Partido partido = new Partido(rondaActual, ++contador, equipo1, equipo2, golesEquipo1, golesEquipo2);
                rondaActual.getPartidos().add(partido);
                ultimoNroRonda = nroRonda;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    public void convertirPronostico(String[] args) throws IOException {

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C://Users//nahue//OneDrive//Documentos//GitHub//Trabajo-Practico-Integrador-Parte-1-//demo//src//test//java//demo//Pronostico.csv"));
            reader.readNext();
            String[] nextLine = null;
            Map<String, Participante> participantesfase = new HashMap<>();
            Map<String, Participante> participantesRondaActual = new HashMap<>();
            int rondaActual = -1;
            int faseActual = -1;
            int contador = 0;
            while ((nextLine = reader.readNext()) != null) {
                int idFase = Integer.parseInt(nextLine[0]);
                String nombreParticipante = nextLine[1];
                int idRonda = Integer.parseInt(nextLine[2]);
                String equipo = nextLine[3];
                int idPartido = Integer.parseInt(nextLine[4]);
                String resultado = nextLine[5];
                Pronostico pronostico = new Pronostico(nombreParticipante, idRonda, equipo, idPartido, resultado);
    
                if (rondaActual != idRonda) {
                    participantesRondaActual = new HashMap<>();
                    rondaActual = idRonda;
                }
    
                if (faseActual != idFase) {
                    participantesfase = new HashMap<>();
                    faseActual = idFase;
                }
    
                Ronda ronda = rondas.get(idRonda - 1);
                if (pronostico.getRonda() == ronda.getNro()) {
                    ronda.getPronosticos().add(pronostico);
                }
    
                Fase fase = fases.get(idFase - 1);
                if (!participantesfase.containsKey(nombreParticipante)) {
                    if (!participantesRondaActual.containsKey(nombreParticipante)) {
                        contador += 1;
                        Participante participanteActual = new Participante(contador, nombreParticipante, 0);
                        ronda.getParticipantes().add(participanteActual);
                        fase.getParticipantesFase().add(participanteActual);
                        participantesRondaActual.put(nombreParticipante, participanteActual);
                        participantesfase.put(nombreParticipante, participanteActual);
                    }
    
                } else {
                    if (!participantesRondaActual.containsKey(nombreParticipante)) {
                        Participante participanteActual = participantesfase.get(nombreParticipante);
                        participantesRondaActual.put(nombreParticipante, participanteActual);
                        ronda.getParticipantes().add(participanteActual);
                    }
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

    public static ArrayList<Ronda> ObtenerRondas(String DireccionDeBD) {
        LectorArchivos lectorArchivos = new LectorArchivos();
        try {
            lectorArchivos.convertirPartidos(null, DireccionDeBD);
            lectorArchivos.convertirPronostico(null) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rondas;
    }
    public static ArrayList<Fase> ObtenerFases(String DireccionDeDB) {
        ArrayList<Ronda> lista_rondas = ObtenerRondas(DireccionDeDB);
        try {
            for(Fase fase: fases){
                fase.buscarRondas_Fase(lista_rondas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fases;
    }
    
    public static ArrayList<Resultado> leerArchivoPuntos() {
        ArrayList<Resultado> resultados = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C://Users//nahue//OneDrive//Documentos//GitHub//Trabajo-Practico-Integrador-Parte-1-//demo//src//test//java//demo//AsginacionDePuntos.csv"));
            reader.readNext();
            String[] nextLine=null;
            int id = 0;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    String nombre = nextLine[0];
                    int puntaje = Integer.parseInt(nextLine[1]);
                    Resultado resultado = new Resultado(id, nombre, puntaje);
                    resultados.add(resultado);
                    id++;
                } catch (NumberFormatException e) {
                    // no hago nada
                }
            }
        } catch (Exception e) {
            // no hago nada
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // no hago nada
            }
        }
        return resultados;
    }
}