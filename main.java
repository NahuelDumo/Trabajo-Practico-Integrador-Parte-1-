import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class main {
    public List<Partido> leerArchPartido(String archivo) {

        List<Partido> partidos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Equipo equipo1 = new Equipo(datos[0]);
                int golesequipo1 = Integer.parseInt(datos[1]);
                int golesequipo2 = Integer.parseInt(datos[2]);
                Equipo equipo2 = new Equipo(datos[3]);
                Partido partido = new Partido(equipo1, equipo2 , golesequipo1,  golesequipo2);
                partidos.add(partido);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return partidos;
    }
    public List<Pronostico> leerArchPronostico(String archivo2) {

        List<Pronostico> pronosticos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo2))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Equipo equipo = new Equipo(datos[0]);
                String resultado = datos[2];
                Pronostico pronostico = new Pronostico(equipo, resultado);
                pronosticos.add(pronostico);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return pronosticos;

    }
}
