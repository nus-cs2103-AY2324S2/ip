package Duke;

import java.io.IOException;

/**
 * Represents the main class of the Duke program.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath The file path to store the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     * @throws DukeException If there is an error running the program.
     */
    public void run() throws DukeException {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            String result = Parser.parse(userInput, tasks, ui, storage);
            if (result.equals("1")) {
                isExit = true;
                ui.closeScanner();
            }
        }
    }

    /**
     * Main method to run the Duke program.
     * @param args The arguments to run the program.
     * @throws DukeException If there is an error running the program.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data/duke.txt").run();
    }
}