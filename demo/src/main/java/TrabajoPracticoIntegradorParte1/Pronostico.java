package TrabajoPracticoIntegradorParte1;

public class Pronostico {
    private String participante;
    private int ronda;
    private String nombre_equipo;
    private int idPartido;
    private String resultado;

        //constructores
    public Pronostico(String participanteString, int Ronda, String eqipo1, int parti, String result){
        this.nombre_equipo= eqipo1;
        this.idPartido= parti;
        this.resultado = result;
        this.participante = participanteString;
        this.ronda = Ronda;
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
    public String getParticipante() {
        return participante;
    }
    public void setParticipante(String participante) {
        this.participante = participante;
    }
    public int ObtenerResutadoReal(Partido partido, String equipoAnalizar) {
       
        if (partido.resultado(equipoAnalizar).equals( this.resultado)){
            return 1;
        
    }
    else{
        return 0;
    }
    }
    public int getRonda() {
        return ronda;
    }
    public void setRonda(int ronda) {
        this.ronda = ronda;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

