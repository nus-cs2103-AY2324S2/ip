package toothless;

import toothless.exception.ToothlessException;
import toothless.parser.Parser;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * The main class for the Toothless TaskList chatbot.
 */
public class Toothless {
    private static final String FILE_PATH = "./data/tasklist.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * A private constructor to initialize the chatbot.
     */
    public Toothless() {}

    /**
     * Initializes Toothless.
     *
     * @return String message in case of initialization error.
     */
    public String initialize() {
        String message = "";
        ui = new Ui();
        parser = new Parser();

        try {
            this.storage = new Storage(FILE_PATH);
            this.tasks = new TaskList(this.storage.loadStorage());
        } catch (ToothlessException e) {
            message += e.getMessage();
            message += "\nSorry, tasklist.txt is corrupted. Starting a blank tasklist.";
            this.tasks = new TaskList();
        }
        return message;
    }

    /**
     * Gets response to user input.
     *
     * @param input User input from GUI.
     * @return String of Toothless' response.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            response += parser.parseInput(tasks, ui, input);
        } catch (ToothlessException e) {
            response += e.getMessage();
        }

        try {
            storage.saveToStorage(tasks.getTasks());
        } catch (ToothlessException e) {
            response += e.getMessage();
        }
        return response;
    }
}
