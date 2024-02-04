import main.Parser;
import main.Storage;
import objects.TaskList;

import view.Exit;
import view.Greeting;

import java.util.Scanner;

/**
 * The Duke class serves as the main entry point for the Duke application.
 * It initializes the necessary components, interacts with the user through the command-line interface,
 * and manages the parsing and storage of tasks.
 */
public class Duke {
    /**
     * The main method of the Duke application.
     * It initializes the task list, scanner, and displays a greeting to the user.
     * The user can input commands through the command line until they type "bye" to exit.
     * All tasks are saved to storage after each command.
     */
    public static void main(String[] args) {
        TaskList tasks = Storage.load();
        Scanner scanner = new Scanner(System.in);

        Greeting.display();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            Parser.parse(input, tasks);
            Storage.save(tasks);
        }

        Exit.display();
    }
}