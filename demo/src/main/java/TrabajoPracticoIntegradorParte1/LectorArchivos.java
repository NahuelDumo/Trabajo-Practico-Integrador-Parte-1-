package TrabajoPracticoIntegradorParte1;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class LectorArchivos{
    private static  ArrayList<Partido> partidos = new ArrayList<Partido>();
        
    
    private static int contador =  0;
    public static final char SEPARATOR=',';
    public static ArrayList<Partido> ConvertirPartidos(String[] args) throws IOException{
    
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C://Users//nahue//OneDrive//Documentos//GitHub//Trabajo-Practico-Integrador-Parte-1-//demo//src//main//java//demo//partido.csv"));
            String[] nextLine=null;
            
            while ((nextLine = reader.readNext()) != null) {
                contador += 1;
                System.out.println(Arrays.toString(nextLine));
                Equipo equipo1 =  new Equipo(nextLine[0]);
                Integer golesEquipo1 =  Integer.parseInt(nextLine[1]);
                Integer golesEquipo2 =  Integer.parseInt(nextLine[2]);
                Equipo equipo2 =  new Equipo(nextLine[3]);
                Partido partido = new Partido(contador, equipo1, equipo2, golesEquipo1,golesEquipo2);
                partidos.add(partido);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            } 
        }
        
        return partidos;
    }
    public static void main(String[] args) {
        try {
            ArrayList<Partido> partidos = LectorArchivos.ConvertirPartidos(args);
            // hacer algo con la lista de partidos, por ejemplo imprimirlos
            for (Partido partido : partidos) {
                System.out.println(partido.getGolesEquipo2());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


    


