package TrabajoPracticoIntegradorParte1;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LectorArchivos {

    private static ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private static int contador = 0;
    public static final char SEPARATOR = ',';

    public void convertirPartidos(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nahue\\OneDrive\\Documentos\\GitHub\\Trabajo-Practico-Integrador-Parte-1-\\demo\\src\\Base de datos\\BDTP.db3");
            String query = "SELECT idPartido, Ronda, Equipo1, golesEquipo1, golesEquipo2, Equipo2 FROM Partidos ORDER BY Ronda, idPartido";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
    
            Ronda rondaActual = null;
            int ultimoNroRonda = 0;
            while (resultSet.next()) {
                int nroRonda = resultSet.getInt("Ronda");
                if (rondas.size() < nroRonda) {
                    rondaActual = new Ronda(nroRonda, new ArrayList<Partido>(), new ArrayList<Pronostico>(), new ArrayList<Participante>());
                    rondas.add(rondaActual);
                    ultimoNroRonda = nroRonda;
                    contador = 0; // Reiniciamos el contador para cada nueva ronda
                } else if (nroRonda != ultimoNroRonda) {
                    System.err.println("Error: se encontró un partido de la ronda " + nroRonda + " después de la ronda " + ultimoNroRonda);
                    return;
                }
                Equipo equipo1 = new Equipo(resultSet.getString("Equipo1"));
                Integer golesEquipo1 = resultSet.getInt("golesEquipo1");
                Integer golesEquipo2 = resultSet.getInt("golesEquipo2");
                Equipo equipo2 = new Equipo(resultSet.getString("Equipo2"));
                Partido partido = new Partido(rondaActual, ++contador, equipo1, equipo2, golesEquipo1, golesEquipo2);
                rondaActual.getPartidos().add(partido);
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