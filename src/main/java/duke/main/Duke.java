package duke.main;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the main class for the chat application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String filePath = "data/duke.txt";
    private boolean isExit = false;

    /**
     * Generates a response to user input.
     */
    String getResponse(String input) {
        if (!isExit) {
            try {
                ui.repeat();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                return ui.getAnswer();
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else {
            return "";
        }
    }

    /**
     * Constructs a Duke object.
     * Initialises the storage, user interface and task list.
     *
     * @throws DukeException If there is an error loading the task list from storage.
     */
    Duke() {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadList());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
}