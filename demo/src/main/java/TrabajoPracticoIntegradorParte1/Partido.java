package TrabajoPracticoIntegradorParte1;

import java.util.ArrayList;
import java.util.Objects;

public class Partido {
    
    private Ronda ronda;
    private Integer id  ;
    private Equipo equipo1;
    private Equipo equipo2;
    private final Integer golesEquipo1;
    private final Integer golesEquipo2;
    private String resultado;
    private static ArrayList<Resultado> resultados = LectorArchivos.leerArchivoPuntos();

    public static ArrayList<Resultado> getResultados() {
        return resultados;
    }
    public void setResultados(ArrayList<Resultado> resultados) {
        Partido.resultados = resultados;
    }
    public Ronda getRonda() {
        return ronda;
    }
    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }
    public Equipo getEquipo1() {
        return equipo1;
    }
    public Equipo getEquipo2() {
        return equipo2;
    }
    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }
    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }
    public Integer getId() {
        return id;
    }
    public Partido(Ronda rond,Integer ide, Equipo equio1, Equipo equio2, Integer golesEuipo1, Integer golesEuipo2){
        this.ronda = rond;
        this.id = ide;
        this.equipo1 = equio1;
        this.equipo2 = equio2;
        this.golesEquipo1 = golesEuipo1;
        this.golesEquipo2 = golesEuipo2;

    }
    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;

    }
    
    //metodos
    public String resultado(String equipoAnalizar){
        if(Objects.equals(equipoAnalizar, this.equipo1.getNombre())){
            if (this.golesEquipo1 > this.golesEquipo2){
                this.resultado= "Ganador";
            }
            else if (this.golesEquipo1 < this.golesEquipo2){
                this.resultado= "Perdedor";
            }
            else{
                this.resultado= "Empata";
            }
        }
        else{
                if (this.golesEquipo1 < this.golesEquipo2){
                    this.resultado= "Ganador";
                }
                else if (this.golesEquipo1 > this.golesEquipo2){
                    this.resultado= "Perdedor";
                }
                else{
                    this.resultado= "Empata";
                }

        }
        return resultado;

    }

    public int obtenerResultado() {
        for (Resultado resultado_posible : resultados) {
            if (resultado_posible.getNombre().equals(resultado)) {
                int id_result = resultado_posible.getId_resultado();
                return resultados.get(id_result).getPuntaje();
            }
        }
        return 0;
    }

}