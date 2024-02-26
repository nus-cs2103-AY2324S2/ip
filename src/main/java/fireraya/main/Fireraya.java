package fireraya.main;

import fireraya.command.Command;
import fireraya.exception.FirerayaException;
import javafx.application.Platform;

/**
 * @author Dexter-Wong
 *
 * A class which serves as an emtry point into the program.
 */
public class Fireraya {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private boolean isExit;

    /**
     * Initializer for main class. Loads previous saved file from memory.
     *
     * @param filePath The name of the file where outputs are saved.
     */
    public Fireraya(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FirerayaException e) {
            ui.displayLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * The command get a response from a string input.
     *
     * @param input input from the user.
     * @return output string of the program.
     */
    public String getResponse(String input) {
        try{
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            if (this.isExit == true) {
                Platform.exit();
            }
            return c.execute(tasks, ui, storage);
            } catch (FirerayaException e) {
            return e.getMessage();
        }
    }
}

