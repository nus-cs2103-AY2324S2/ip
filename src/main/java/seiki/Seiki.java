package seiki;

import seiki.commands.Command;
import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.parser.Parser;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Entry point of the Seiki chatbot.
 * Runs the bot and starts the interaction with the user.
 */
public class Seiki {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     * @param filePath
     */
    public Seiki(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (SeikiException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (SeikiException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Seiki("data/tasks.txt").run();
    }

}
