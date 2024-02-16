package earl;

import earl.exceptions.EarlException;
import earl.exceptions.ParserException;
import earl.exceptions.StorageException;
import earl.logic.Handler;
import earl.util.Storage;
import earl.util.TaskList;
import earl.util.Ui;
import earl.util.parsers.InputParser;
import earl.util.parsers.TaskStorageParser;

/**
 * The main class of the Earl application.
 */
public class Earl {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor of the Earl class.
     *
     * @param filePath  a path to the text file storing past data
     */
    public Earl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(TaskStorageParser::parse));
        if (!storage.wasLoadSuccessful()) {
            ui.makeResponse("Storage hath succumb to corruption... ",
                    "initiating an unfortunate state of emptiness.");
        }
        ui.showGreeting();
    }

    /** Main function for TUI mode. */
    public static void main(String[] args) {
        new Earl("data/earl.txt").run();
    }

    /**
     * Executes main loop of the Earl class for TUI mode.
     * <p>
     * Contains main program loop and displaying of greeting
     * and goodbye messages. Attempts to save to storage on exit.
     */
    public void run() {
        String input = ui.getUserInput();
        while (!input.equals("bye")) {
            processUserInput(input);
            input = ui.getUserInput();
        }
        saveAndExit();
    }

    /**
     * Returns a response to an interaction in GUI mode.
     *
     * @param input  the user input text
     * @return       a {@code String} response
     */
    public String getResponse(String input) {
        if (input.equals("bye")) { // terminate execution
            saveAndExit();
            return ui.getResponse();
        }
        processUserInput(input);
        return ui.getResponse();
    }

    /** Returns the previous response. */
    public String getResponse() {
        return ui.getResponse();
    }

    private void processUserInput(String input) {
        try {
            Handler handler = InputParser.parse(input);
            handler.handle(tasks, ui);
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
        } catch (ParserException e) {
            ui.makeResponse("Input defies parsing:" + e.getMessage());
        }
    }

    private void saveAndExit() {
        try {
            storage.save(tasks.getAsStorageStringStream());
            ui.showGoodbye();
        } catch (StorageException e) {
            ui.makeResponse("Alas, a grievous misfortune occurred "
                    + "during the endeavour to save.");
        }
    }
}
