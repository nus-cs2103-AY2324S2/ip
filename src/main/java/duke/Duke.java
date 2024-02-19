package duke;

import java.util.Scanner;


/**
 * The main class for the Duke application.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object with a given file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        assert storage != null && ui != null : "Storage and UI must be initialized";
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /** Runs the Duke application. */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            String command = scanner.nextLine().trim();

            try {
                Parser.parseAndExecute(command, tasks, storage);
                isExit = command.equals("bye");
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Runs the Duke application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Gets the response from Duke.
     *
     * @param input The input command.
     * @return The response from Duke.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseAndExecute(input, tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
