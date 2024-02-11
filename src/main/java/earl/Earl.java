package earl;

import earl.exceptions.EarlException;
import earl.logic.Handler;
import earl.util.Storage;
import earl.util.TaskList;
import earl.util.Ui;
import earl.util.parsers.InputParser;

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
     * @param filePath a path to the text file storing past data
     */
    public Earl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        if (!storage.wasLoadSuccessful()) {
            ui.makeResponse("Failed to read from storage.",
                    "Starting with empty file...");
        }
        ui.showGreeting();
    }

    /**
     * Main function for TUI mode.
     */
    public static void main(String[] args) {
        new Earl("data/earl.txt").run();
    }

    /**
     * Main program execution of the Earl class for TUI mode.
     * <p>
     * Contains main program loop and displaying of greeting
     * and goodbye messages. Attempts to save to storage on exit.
     */
    public void run() {
        // main loop
        String input = ui.getUserInput();
        while (!input.equals("bye")) {
            try {
                Handler handler = InputParser.parse(input);
                handler.handle(tasks, ui);
            } catch (EarlException e) {
                ui.makeResponse(e.getMessage());
            } finally {
                input = ui.getUserInput();
            }
        }
        // save to file
        try {
            storage.save(tasks.getAsStream());
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
        }
        ui.showGoodbye();
    }

    /** Returns greeting for GUI startup. */
    public String getResponse() {
        return ui.getResponse();
    }

    /** Returns response to interaction with the GUI. */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) { // terminate execution
                storage.save(tasks.getAsStream());
                ui.showGoodbye();
                return ui.getResponse();
            }
            Handler handler = InputParser.parse(input);
            handler.handle(tasks, ui);
            return ui.getResponse();
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
            return ui.getResponse();
        }
    }
}
