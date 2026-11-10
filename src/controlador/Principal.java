

package controlador;

import vista.VistaConsola;

public class Principal {

    public static void main(String[] args) {

        ControladorTorneo controlador = new ControladorTorneo();

        VistaConsola vista = new VistaConsola(controlador);

        vista.iniciar();
    }
}
