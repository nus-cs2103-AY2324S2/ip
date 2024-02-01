package duke;

import duke.command.Command;

import java.io.FileNotFoundException;

/**
 * Duke, a task manager bot. Also named "MR. WONG"
 * @author wongkj12
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of the bot.
     * @param filePath List of tasks are stored at / retrieved from specified filepath.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage, storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(storage);
        } catch (FileNotFoundException e) {
            // something went wrong
            ui.showError("File not found.");
        }
    }

    /**
     * Runs Duke.
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
            } finally {
                ui.showLine();
            }
        }
    }


    /**
     * Main method which starts the bot.
     * @param args Any command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}