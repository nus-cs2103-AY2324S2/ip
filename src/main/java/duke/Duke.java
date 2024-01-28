package duke;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Duke instance with a specified file path for storage.
     *
     * @param filePath The file path for storing task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
                Parser.parseAndExecute(command, tasks, ui, storage);
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
        new Duke("./duke.txt").run();
    }
}
