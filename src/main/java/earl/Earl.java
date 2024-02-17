package earl;

import earl.exceptions.EarlException;
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

    private static final String[] GREETING_MESSAGE = {
        "Greetings, I, Earl, extend my esteemed salutations.",
        "Pray, how may I be of service to you on this auspicious day?"
    };
    private static final String[] GOODBYE_MESSAGE = {
        "Farewell, dear interlocutor!",
        "Until our paths intertwine again, I bid you adieu."
    };

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor of the Earl class.
     *
     * @param filePath  a path to the text file storing past data
     */
    public Earl(String filePath) {
        ui = new Ui(GREETING_MESSAGE);
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(TaskStorageParser::parse));
        if (!storage.wasLoadSuccessful()) {
            ui.buildResponse("Storage hath succumb to corruption... ",
                    "initiating an unfortunate state of emptiness.");
        }
        ui.completeResponse();
    }

    /** Main function for TUI mode. */
    public static void main(String[] args) {
        new Earl("data/earl.txt").run();
    }

    /**
     * Executes main loop of the Earl class for CLI mode.
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
     * Returns a response to an interaction in CLI mode.
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
        }
    }

    private void saveAndExit() {
        try {
            storage.save(tasks.getAsStorageStringStream());
            ui.makeResponse(GOODBYE_MESSAGE);
        } catch (StorageException e) {
            ui.makeResponse("Alas, a grievous misfortune occurred "
                    + "during the endeavour to save.");
        }
    }
}
