package sn.gerardo.demoConsumoApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.gerardo.demoConsumoApi.model.Episode;
import sn.gerardo.demoConsumoApi.model.SeasonResponse;
import sn.gerardo.demoConsumoApi.service.OmdbService;

import java.util.Scanner;

@Component
public class ConsoleController implements CommandLineRunner {
    @Autowired
    private OmdbService omdbService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    realizarBusqueda(scanner);
                    scanner.nextLine(); // Consumir el ENTER
                    break;
                case 2:
                    continuar = false;
                    System.out.println("¡Gracias por usar la aplicación!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }

        scanner.close();
    }

    private void realizarBusqueda(Scanner scanner) {
        System.out.println("\n=== NUEVA BÚSQUEDA ===");
        System.out.println("Ingrese el nombre de la serie:");
        String seriesName = scanner.nextLine();

        System.out.println("Ingrese el número de temporada:");
        int season = scanner.nextInt();

        try {
            SeasonResponse response = omdbService.getSeasonEpisodes(seriesName, season);

            if (response != null && response.getEpisodes() != null) {
                System.out.println("\nEpisodios de la temporada " + season + ":");
                for (Episode episode : response.getEpisodes()) {
                    System.out.println(episode);
                }
            } else {
                System.out.println("No se encontraron episodios para esta serie y temporada.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los episodios: " + e.getMessage());
        }
    }

    private int obtenerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1.- Nueva búsqueda");
        System.out.println("2.- Salir");
        System.out.print("Ingrese su opción: ");
    }
}
