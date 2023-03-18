import java.util.Objects;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
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