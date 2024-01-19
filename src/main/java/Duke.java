import java.util.Objects;
import java.util.Scanner;


public class Duke {
    private static String LINE_BREAK = "---------------------------------------------\n";
    public static void hello() {
        String message = "Squid: HMm human. What do you want again?\n\n";
        System.out.println(LINE_BREAK + message + LINE_BREAK);
    }

    public static void bye() {
        String message = "\nYou're done. Time for my food.\n";
        System.out.println(LINE_BREAK + message);
    }

    public static void echo(String message) {
        System.out.println("Squid: " + message);
    }

    public static void main(String[] args) {
        hello();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                bye();
                break;
            }
            echo(input);

        }

    }
}
