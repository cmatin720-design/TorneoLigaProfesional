
package vista;

import java.util.Scanner;

import controlador.ControladorTorneo;
import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

public class VistaConsola {

    private Scanner scanner = new Scanner(System.in);
    private ControladorTorneo controlador;

    public VistaConsola(ControladorTorneo controlador) {
        this.controlador = controlador;
    }

    public void iniciar() {

        int opcion = 0;

        do {
            System.out.println("\n===== LIGA PROFESIONAL =====");
            System.out.println("1. Crear torneo");
            System.out.println("2. Realizar sorteo");
            System.out.println("3. Guardar torneo");
            System.out.println("4. Cargar torneo");
            System.out.println("5. Salir");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        controlador.crearTorneo();
                        break;

                    case 2:
                        try {
                            controlador.realizarSorteo();
                        } catch (NumeroEquiposInvalidoException |
                                 SorteoInvalidoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        controlador.guardarTorneo();
                        break;

                    case 4:
                        controlador.cargarTorneo();
                        break;

                    case 5:
                        System.out.println("Programa finalizado.");
                        break;

                    default:
                        System.out.println("Opcion incorrecta.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un numero.");
            }

        } while (opcion != 5);
    }
}
