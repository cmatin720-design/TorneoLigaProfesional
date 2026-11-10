
package modelo;

import java.io.Serializable;

public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    private String equipo1;
    private String equipo2;
    private String etapa;

    public Partido(String equipo1, String equipo2, String etapa) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.etapa = etapa;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public String getEtapa() {
        return etapa;
    }

    @Override
    public String toString() {
        return equipo1 + " vs " + equipo2;
    }
}
