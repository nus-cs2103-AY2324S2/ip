import java.util.Objects;
import java.util.Scanner;

public class Duke {
    // A horizontal line.
    private final String horizontalLine = "____________________________________________________________";

    // Prints a horizontal line.
    private void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

    // Greets the user.
    private void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm Virtue \nWhat can I do for you?");
        printHorizontalLine();
    }

    // Exits with a goodbye message.
    private void bye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    // Echos the command.
    private void echo(String command) {
        printHorizontalLine();
        System.out.println(command);
        printHorizontalLine();;
    }

    // Runs the chatbot.
    private void run() {
        // User greeted
        greet();
        bye();
    }

    public static void main(String[] args) {
        Duke virtue = new Duke();
        virtue.run();
    }
}
