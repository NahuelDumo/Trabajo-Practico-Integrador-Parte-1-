import java.util.Objects;

public class Partido {
    private Equipo equipo1;

    private Equipo equipo2;
    private final Integer golesEquipo1;
    private final Integer golesEquipo2;


    public Partido(Equipo equio1, Equipo equio2, Integer golesEuipo1, Integer golesEuipo2){

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
    public String resultado(Equipo equipo){
        if(Objects.equals(equipo, this.equipo1)){
            if (this.golesEquipo1 > this.golesEquipo2){
                return "Gana";
            }
            else if (this.golesEquipo1 < this.golesEquipo2){
                return "Pierde";
            }
            else{
                return "Empata";
            }
        }
        else{
                if (this.golesEquipo1 < this.golesEquipo2){
                    return "Gana";
                }
                else if (this.golesEquipo1 > this.golesEquipo2){
                    return "Pierde";
                }
                else{
                    return "Empata";
                }

        }
    }


}