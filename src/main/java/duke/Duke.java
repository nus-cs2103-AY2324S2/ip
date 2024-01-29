/*
 * Duke.java
 * This is the main class of the Duke application, responsible for handling user input and managing tasks.
 */

package duke;

import duke.task.Parser;
import duke.task.Storage;
import duke.task.TaskList;
import duke.command.Command;

import java.io.IOException;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final Storage storage = new Storage(FILE_PATH);
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new Duke instance, initializes the UI, and loads tasks from storage.
     */
    public Duke() {
        ui = new Ui();
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application and runs the command loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Main method to start the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
