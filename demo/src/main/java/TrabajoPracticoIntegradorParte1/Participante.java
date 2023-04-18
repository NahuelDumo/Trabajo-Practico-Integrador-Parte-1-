package TrabajoPracticoIntegradorParte1;

public class Participante {
    private int id; 
    private String nombre;
    private int puntaje;
    private int puntaje_fase;
    private int rondas_acertadas;

    public int getRondas_acertadas() {
        return rondas_acertadas;
    }
    public void setRondas_acertadas(int rondas_acertadas) {
        this.rondas_acertadas = rondas_acertadas;
    }
    public int getPuntaje_fase() {
        return puntaje_fase;
    }
    public void setPuntaje_fase(int puntaje_fase) {
        this.puntaje_fase = puntaje_fase;
    }
    public Participante(int ide,String nombre, int puntaje) {
        this.id = ide;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.rondas_acertadas=0;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void SumarPuntajeFase(int puntaje_ronda){
        this.puntaje_fase += puntaje_ronda;
    }
    public void ResetearPuntajeRonda(){
        this.puntaje = 0;
    }
    public void sumarPuntajeRonda(int puntaje_ronda){
        this.puntaje += puntaje_ronda;
    }
    public void acertofase(Fase fase ){
        
        if (fase.getRondas().size() == rondas_acertadas){

            System.out.println("\n"+nombre + " Felicides acerto todos los resultados de esta Fase\n");
            puntaje_fase += Partido.getResultados().get(3).getPuntaje();
        }
    }
    public void acertoRonda(Ronda ronda ){

        if ((this.puntaje / Partido.getResultados().get(0).getPuntaje()) == ronda.getPartidos().size()){
            System.out.println("\n"+nombre + " Felicides acerto todos los resultados de esta Ronda \n");
            sumarPuntajeRonda(Partido.getResultados().get(4).getPuntaje());
            rondas_acertadas+=1;
        }
    
    }

}
