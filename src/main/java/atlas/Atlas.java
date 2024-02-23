package atlas;

import atlas.command.Command;
import atlas.exception.AtlasException;

import java.time.format.DateTimeParseException;

/**
 * The main class that drives the Atlas application.
 */
public class Atlas {
    private final String DATA_PATH = "./data/Atlas.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an Atlas application instance with UI, TaskList, and Storage initialized.
     */
    public Atlas() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(tasks, DATA_PATH);
        storage.load();
    }


    /**
     * Takes an input from user and returns the appropriate response.
     *
     * @param input The input from the user.
     * @return Response from the program based off the input.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input, tasks, ui, storage);
            return cmd.execute();
        } catch (AtlasException e) {
            return ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.showError("Date format should be: YYYY-MM-DD HHmm");
        }
    }
}
