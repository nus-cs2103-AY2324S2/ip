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
    public static final String FILE_PATH = "data/tasks.txt";
    private boolean isExit;
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Seiki chatbot.
     * Sets up the required objects, loads up the data from the storage file.
     */
    public Seiki() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        isExit = false;
        try {
            tasks = storage.load();
            ui.showWelcome();
        } catch (SeikiException e) {
            tasks = new TaskList();
        }
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Main run method of the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                isExit = true;
            }
            return c.execute(storage, tasks, ui);
        } catch (SeikiException e) {
            return ui.showError(e.getMessage());
        }
    }
}
