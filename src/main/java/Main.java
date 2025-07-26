import dto.QuoteResponse;
import service.QuoteService;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Main {

    // --- CONSTANTES ---
    // Índice para el caracter ':' en el array BIG_DIGITS.
    private static final int COLON_INDEX = 10;
    private static final String[][] BIG_DIGITS = Number.BIG_DIGITS;

    /**
     * Imprime una cadena de tiempo (ej: "25:00") en la consola usando dígitos grandes.
     *
     * @param timeStr La cadena de tiempo a mostrar.
     */
    private static void printBigDigits(String timeStr) {
        // Prepara un array de Strings para cada línea de los dígitos grandes.
        int digitHeight = BIG_DIGITS[0].length;
        String[] lines = new String[digitHeight];
        Arrays.fill(lines, "|   ");

        for (char ch : timeStr.toCharArray()) {
            int index = (ch == ':') ? COLON_INDEX : Character.getNumericValue(ch);

            // Asegurarse de que el índice es válido para evitar errores.
            if (index >= 0 && index < BIG_DIGITS.length) {
                // Concatena cada línea del dígito actual a las líneas de la pantalla.
                for (int i = 0; i < digitHeight; i++) {
                    lines[i] += BIG_DIGITS[index][i] + "  ";
                }
            }
        }
        // Imprime el resultado final.
        for (String line : lines) {
            System.out.println(line + "  |");
        }
    }

    /**
     * Limpia la pantalla de la consola usando secuencias de escape ANSI.
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Inicia una cuenta regresiva, mostrando el título y un reloj gigante en cada segundo.
     *
     * @param minutes La cantidad de minutos para la cuenta regresiva.
     * @param title   Un título para mostrar sobre el reloj.
     * @throws InterruptedException Si el hilo es interrumpido.
     */
    private static void countdown(int minutes, String title) throws InterruptedException, ExecutionException {
        long totalSeconds = (long) minutes * 60;

        var quoteResponse = QuoteService.getQuote();
        String quote = quoteResponse.getQuote() + "\n" + "Autor: " + quoteResponse.getAuthor();

        for (long i = totalSeconds; i >= 0; i--) {
            long min = i / 60;
            long sec = i % 60;
            String time = String.format("%02d:%02d", min, sec);

            // 1. Limpiar la pantalla.
            clearScreen();
            // 2. Imprimir el título.
            System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------+\n\t\t\t\t\t\t\t"
                    + title
                    + "\n\\ +-----------------------------------------------------------------------------------------------------------------------------------------------+ /\n\n");
            // 3. Imprimir el reloj grande (sin que este vuelva a limpiar la pantalla).
            printBigDigits(time);
            System.out.println("\n\n+--------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println(quote);
            if(min % 4 == 0 && sec == 0) {
                quoteResponse = QuoteService.getQuote();
                quote = quoteResponse.getQuote() + "\n" + "Autor: " + quoteResponse.getAuthor();
            }
            System.out.println("\n\n+--------------------------------------------------------------------------------------------------------------------------------------------------+");
            Thread.sleep(1000);
        }
    }


    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Uso: java -jar clock_app.jar <minutosPomodoro> <minutosDescanso> <cantidadPomodoros> <titulo de la tarea>");
            System.out.println("Ejemplo: java -jar clock_app.jar 25 5 4 Estudiar patrones de diseño");
            return;
        }

        try {
            int minutosPomodoro = Integer.parseInt(args[0]);
            int minutosDescanso = Integer.parseInt(args[1]);
            int cantidad = Integer.parseInt(args[2]);

            StringBuilder tituloBuilder = new StringBuilder("Actual task: ");
            for (int i = 3; i < args.length; i++) {
                tituloBuilder.append(args[i]).append(" ");
            }
            String titulo = tituloBuilder.toString().trim();

            // Inicia el ciclo de Pomodoros.
            for (int i = 1; i <= cantidad; i++) {
                System.out.printf("Iniciando Pomodoro %d de %d...\n", i, cantidad);
                Player.playStart();
                countdown(minutosPomodoro, titulo + " (Pomodoro " + i + "/" + cantidad + ")");

                if (i < cantidad) {
                    // Usa la variable minutosDescanso y la muestra en el mensaje.
                    Player.playStop();
                    System.out.printf("\n☕ Descanso corto de %d minutos...\n", minutosDescanso);
                    countdown(minutosDescanso, "Descansando...");
                }
            }

            System.out.println("\n🎉 ¡Todos los Pomodoros completados! ¡Excelente trabajo!");

        } catch (NumberFormatException e) {
            System.err.println("Error: Los tres primeros argumentos deben ser números enteros.");
            System.err.println("Ejemplo: <minutosPomodoro>=25, <cantidadPomodoros>=4, <minutosDescanso>=5");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El temporizador fue interrumpido.");
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.err.println("Error al reproducir el audio. msg: " + e.getMessage());
        } catch (ExecutionException e) {
            System.err.println("Error al buscar frase motivacional");
        }
    }
}