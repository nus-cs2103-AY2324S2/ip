package duke;

import duke.command.Parser;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The Duke class represents the main application that interacts with the user.
 * It initializes the User Interface (UI), runs the conversation loop, and handles user input.
 */
public class Duke {

    private final Ui ui;

    /**
     * Constructs a Duke instance and initializes the UI.
     */
    public Duke() {
       ui = new Ui();
    }

    /**
     * Runs the main conversation loop where the application interacts with the user.
     * It takes user input, parses commands, and continues the conversation until the user exits.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.startConversation();
        String username = ui.getUsername();
        Parser parser = new Parser(username);

        while (true) {
            String input = scanner.nextLine();
            String[] cmd = input.split(" ");

            if (!cmd[0].equalsIgnoreCase("bye")) {
                parser.parse(input);
            } else {
                parser.saveAllTasks();
                ui.defaultExit();
                break;
            }
        }

        scanner.close();
    }

    /**
     * The entry point of the Duke application.
     *
     * @param args default command-line arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}