
package modelo;

import java.io.*;
import java.util.*;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

public class Torneo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Equipo> equipos;
    private List<Partido> historial;

    public Torneo(List<Equipo> equipos) {
        this.equipos = equipos;
        this.historial = new ArrayList<>();
    }

    public List<Partido> getHistorial() {
        return historial;
    }

    // Comprueba que el número de equipos sea potencia de dos
    private void validarEquipos()
            throws NumeroEquiposInvalidoException {

        int n = equipos.size();

        if (n < 2 || (n & (n - 1)) != 0) {
            throw new NumeroEquiposInvalidoException(
                "El numero de equipos debe ser 2, 4, 8, 16, etc."
            );
        }
    }

    // Sorteo recursivo de los partidos
    private void emparejarRecursivo(
            List<Equipo> participantes,
            List<Partido> partidos,
            String etapa)
            throws SorteoInvalidoException {

        if (participantes.isEmpty()) {
            return;
        }

        if (participantes.size() % 2 != 0) {
            throw new SorteoInvalidoException(
                "No se pueden emparejar los equipos."
            );
        }

        Random random = new Random();

        int i = random.nextInt(participantes.size());
        Equipo equipo1 = participantes.remove(i);

        int j = random.nextInt(participantes.size());
        Equipo equipo2 = participantes.remove(j);

        partidos.add(new Partido(
            equipo1.getNombre(),
            equipo2.getNombre(),
            etapa
        ));

        emparejarRecursivo(participantes, partidos, etapa);
    }

    // Organiza todas las etapas mediante recursividad
    private void sortearEtapas(
            List<Equipo> participantes,
            int etapa)
            throws SorteoInvalidoException {

        String[] nombres = {
            "OCTAVOS DE FINAL",
            "CUARTOS DE FINAL",
            "SEMIFINALES",
            "FINAL"
        };

        if (participantes.size() < 2) {
            return;
        }

        if (etapa >= nombres.length) {
            throw new SorteoInvalidoException(
                "Etapa de torneo no valida."
            );
        }

        List<Partido> partidosEtapa = new ArrayList<>();

        emparejarRecursivo(
            new ArrayList<>(participantes),
            partidosEtapa,
            nombres[etapa]
        );

        historial.addAll(partidosEtapa);

        System.out.println("\n========== " + nombres[etapa] + " ==========");

        List<Equipo> ganadores = new ArrayList<>();
        Random random = new Random();

        for (Partido partido : partidosEtapa) {

            System.out.println(partido);

            // Ganador simulado aleatoriamente
            String ganador = random.nextBoolean()
                ? partido.getEquipo1()
                : partido.getEquipo2();

            System.out.println("Ganador: " + ganador);

            ganadores.add(new Equipo(ganador));
        }

        sortearEtapas(ganadores, etapa + 1);
    }

    public void sortear()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        validarEquipos();

        historial.clear();

        sortearEtapas(new ArrayList<>(equipos), 0);
    }

    // Guardar torneo mediante serializacion
    public void guardar(String archivo) throws IOException {

        try (ObjectOutputStream salida =
                new ObjectOutputStream(
                    new FileOutputStream(archivo))) {

            salida.writeObject(this);
        }
    }

    // Recuperar torneo mediante deserializacion
    public static Torneo cargar(String archivo)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                    new FileInputStream(archivo))) {

            return (Torneo) entrada.readObject();
        }
    }
}
