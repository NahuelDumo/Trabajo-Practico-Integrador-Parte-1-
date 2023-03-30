package TrabajoPracticoIntegradorParte1;

import java.util.Objects;

public class Pronostico {
    private Equipo equipo;
    private  Partido partido;
    private final String resultado;

    //constructores
   public Pronostico(Equipo eqipo1, Partido parti, String result){
      this.equipo= eqipo1;
      this.partido= parti;
      this.resultado = result;
   }
    public Pronostico(Equipo eqipo1, String result){
        this.equipo= eqipo1;
        this.resultado = result;
    }

   public Equipo getEquipo() {
      return equipo;
   }

   public void setEquipo(Equipo equipo) {
      this.equipo = equipo;
   }
   //metodos
   public int puntos() {
       int puntos = 0;
       String resultado_real = this.partido.resultado(this.equipo);
       if (Objects.equals(resultado_real, this.resultado)){
           puntos = puntos +  1;
       }
       return puntos;
   }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public String getResultado() {
        return resultado;
    }
}

