import java.util.Objects;
import java.util.Scanner;

import commands.Command;
import exception.*;
import main.Parser;
import main.Storage;
import objects.TaskList;
import view.Exit;
import view.Greeting;

/**
 * The Duke class serves as the main entry point for the Duke application.
 * It initializes the necessary components, interacts with the user through the command-line interface,
 * and manages the parsing and storage of tasks.
 */
public class Duke {
    public Duke() {

    }
    /**
     * The main method of the Duke application.
     * It initializes the task list, scanner, and displays a greeting to the user.
     * The user can input commands through the command line until they type "bye" to exit.
     * All tasks are saved to storage after each command.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }

    public void run() {
        TaskList tasks = Storage.load();
        Scanner scanner = new Scanner(System.in);

        Greeting.display();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            Command c = Parser.parse(input, tasks);
            try {
                c.execute();

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            Storage.save(tasks);
        }

        Exit.display();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        TaskList tasks = Storage.load();
        String response;
        Command c;

        try {
            c = Parser.parse(input, tasks);
            response = c.execute();
            Storage.save(tasks);

        } catch (DukeException e) {
            response = e.getMessage();

        }

        return response;
    }
}
