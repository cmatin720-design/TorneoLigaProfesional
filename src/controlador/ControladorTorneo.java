
package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Equipo;
import modelo.Torneo;
import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

public class ControladorTorneo {

    private Torneo torneo;

    public void crearTorneo() {

        List<Equipo> equipos = new ArrayList<>();

        String[] nombres = {
            "Barcelona SC",
            "Emelec",
            "Liga de Quito",
            "Independiente del Valle",
            "Aucas",
            "El Nacional",
            "Deportivo Cuenca",
            "Universidad Catolica",
            "Macara",
            "Mushuc Runa",
            "Delfin",
            "Orense",
            "Libertad FC",
            "Tecnico Universitario",
            "Cumbaya FC",
            "Imbabura SC"
        };

        for (String nombre : nombres) {
            equipos.add(new Equipo(nombre));
        }

        torneo = new Torneo(equipos);

        System.out.println("Torneo creado correctamente.");
    }

    public void realizarSorteo()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        if (torneo == null) {
            crearTorneo();
        }

        torneo.sortear();
    }

    public void guardarTorneo() {

        try {
            torneo.guardar("torneo.ser");
            System.out.println("Torneo guardado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargarTorneo() {

        try {
            torneo = Torneo.cargar("torneo.ser");
            System.out.println("Torneo recuperado correctamente.");

            for (modelo.Partido partido : torneo.getHistorial()) {
                System.out.println(
                    partido.getEtapa() + ": " + partido
                );
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}
