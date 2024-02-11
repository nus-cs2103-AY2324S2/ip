package duke;

import duke.command.CommandParser;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The Duke class represents the main application that interacts with the user.
 * It initializes the User Interface (UI), runs the conversation loop, and handles user input.
 */
public class Duke  {

    private final Ui ui;
    CommandParser parser = new CommandParser("tester");

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
        CommandParser parser = new CommandParser(username);

        while (true) {
            String input = scanner.nextLine();
            String[] cmd = input.split(" ");

            if (!cmd[0].equalsIgnoreCase("bye")) {
                parser.parseInput(input);
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return parser.parseInput(input);
    }

}