package duke;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.Parser;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.LoadStorageException;
import duke.command.Command;

/**
 * The main class for the chatbot application.
 * */
public class Duke {
    /* The storage object used to load and save to disk. */
    private Storage storage;
    /* The taskList containing all current user-entered tasks. */
    private TaskList taskList;
    /* The user interaction object. */
    private Ui ui;

    /**
     * Generates a response to the user input.
     *
     * @param input The user-entered input.
     * @return The response to the input.
     */
    public String getResponse(String input) {
        assert input != null;
        String response;
        try {
            Command c = Parser.parseCommand(input);
            response = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    public Duke(String relativeFilePath) {
        this.ui = new Ui();

        this.storage = new Storage(relativeFilePath);

        try {
            this.taskList = new TaskList(storage.load());
        } catch (LoadStorageException e) {
            System.out.println(e.getMessage());
        }
    }
}
