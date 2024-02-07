package earl;

import earl.exceptions.EarlException;
import earl.logic.HandlerFactory;
import earl.util.Parser;
import earl.util.Storage;
import earl.util.TaskList;
import earl.util.Ui;

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
        TaskList temp;
        try {
            temp = new TaskList(storage.load());
            ui.showGreeting();
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
            temp = new TaskList(); // empty list when fail to read
        }
        tasks = temp;
    }

    /**
     * Main program execution of the Earl class.
     * <p>
     * Contains main program loop and displaying of greeting
     * and goodbye messages. Attempts to save to storage on exit.
     */
    public void run() {
        // main loop
        String input = ui.getUserInput();
        String[] command;
        while (!input.equals("bye")) {
            try {
                command = Parser.parseUserInput(input);
                HandlerFactory.of(command).handle(tasks, ui);
            } catch (EarlException e) {
                ui.makeResponse(e.getMessage());
            } finally {
                input = ui.getUserInput();
            }
        }
        // save to file
        try {
            storage.save(tasks.getList());
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
            if (!input.equals("bye")) {
                String[] command = Parser.parseUserInput(input);
                HandlerFactory.of(command).handle(tasks, ui);
            } else {
                storage.save(tasks.getList());
                ui.showGoodbye();
            }
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
        }
        return ui.getResponse();
    }

    /**
     * Entry point of the Earl class. File path is decided here.
     */
    public static void main(String[] args) {
        new Earl("data/earl.txt").run();
    }
}
