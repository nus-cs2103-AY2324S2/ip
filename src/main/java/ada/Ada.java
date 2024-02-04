package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke program implements a chatbot that keeps track of tasks for the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns an instance of the program which loads the tasks from the file found at the provided filepath.
     *
     * @param filePath The filepath of the file to load/saves the tasks from/to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program by accepting text input from the user in the command line.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method to start the program.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
