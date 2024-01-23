import java.util.Objects;
import java.util.Scanner;

public class Campus {
    public static void main(String[] args) {
        Campus.greet();

        String userInput;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                scanner.close();
                break;
            }
            Campus.echo(userInput);
        }

        Campus.exit();
    }

    public static void echo(String input) {
        String message = "------------------------------\n"
                + String.format("%s\n", input)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void greet() {
        String message = "------------------------------\n"
                + "Hello! I'm Campus\n"
                + "What can I do for you?\n"
                + "\n"
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void exit() {
        String message = "------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "\n"
                + "------------------------------\n";
        System.out.println(message);
    }
}
