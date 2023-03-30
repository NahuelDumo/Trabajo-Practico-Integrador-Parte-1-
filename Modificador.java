import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Modificador{

    public List<Partido> leerArchPartido(String archivo) {

        List<Partido> partidos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
            String[] linea;
            while ((linea = reader.readNext()) != null) {
                 Equipo equipo1 = new Equipo(linea[0]);
                int golesequipo1 = Integer.parseInt(linea[1]);
                int golesequipo2 = Integer.parseInt(linea[2]);
                Equipo equipo2 = new Equipo(linea[3]);
                Partido partido = new Partido(equipo1, equipo2, golesequipo1, golesequipo2);
                partidos.add(partido);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        System.out.print(partidos + "\n");
        return partidos;
    }

    public List<Pronostico> leerArchPronostico(String archivo2) {

        List<Pronostico> pronosticos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(archivo2))) {
            String[] linea;
            while ((linea = reader.readNext()) != null) {
                Equipo equipo = new Equipo(linea[0]);

                String resultado = linea[2];
                Pronostico pronostico = new Pronostico(equipo, resultado);
                pronosticos.add(pronostico);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        System.out.print(pronosticos);
        return pronosticos;
    }
}

