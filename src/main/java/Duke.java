import java.util.Objects;
import java.util.Scanner;

public class Duke {
    // The scanner the chatbot uses to scan users' inputs.
    Scanner sc = new Scanner(System.in);

    // A horizontal line.
    private final String horizontalLine = "____________________________________________________________";

    // Prints with an indention.
    private void printWithIndent(String str) {
        System.out.println("    " + str);
    }

    // Prints a horizontal line.
    private void printHorizontalLine() {
        printWithIndent(horizontalLine);
    }

    // Greets the user.
    private void greet() {
        printHorizontalLine();
        printWithIndent("Hello! I'm Virtue \n    What can I do for you?");
        printHorizontalLine();
    }

    // Exits with a goodbye message.
    private void bye() {
        printHorizontalLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    // Echos the command.
    private void echo(String command) {
        printHorizontalLine();
        printWithIndent(command);
        printHorizontalLine();;
    }

    // Echos the users' commands until bye has been input.
    private void echoUntilBye() {
        // Waits for command from user
        String command = sc.nextLine();

        // While user hasn't input bye, echo command
        while (!Objects.equals(command, "bye")) {
            echo(command);
            command = sc.nextLine();
        }
    }

    // Runs the chatbot.
    private void run() {
        greet();
        echoUntilBye();
        bye();
    }

    public static void main(String[] args) {
        Duke virtue = new Duke();
        virtue.run();
    }
}
