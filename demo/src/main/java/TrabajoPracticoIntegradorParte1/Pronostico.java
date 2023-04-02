package TrabajoPracticoIntegradorParte1;

public class Pronostico {
    private String nombre_equipo;
    int  idPartido;
    private String resultado;

    //constructores
   public Pronostico(String eqipo1, int parti, String result){
      this.nombre_equipo= eqipo1;
      this.idPartido= parti;
      this.resultado = result;
   }
    public Pronostico(String eqipo1, String result){
        this.nombre_equipo= eqipo1;
        this.resultado = result;
    }

   public String getNombre_equipo() {
      return nombre_equipo;
   }

   public void setNombre_equipo(String equipo) {
      this.nombre_equipo = equipo;
   }
   //metodos
   public int getIdPartido() {
    return idPartido;
    }
    public void setIdPartido(int idPartido) {
    this.idPartido = idPartido;
    }
    public String getResultado() {
        return resultado;
    }
    public int ObtenerResutadoReal(Partido partido, String equipoAnalizar) {
       
        if (partido.resultado(equipoAnalizar).equals( this.resultado)){
            return 1;
        
    }
    else{
        return 0;
    }
    }
}

