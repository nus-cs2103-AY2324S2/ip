package emis;

import emisCommand.Command;
import emisExceptions.EmisException;

/**
 * The main class of the EMIS application.
 * Duke handles the initialization of application components and the main application logic.
 */

public class Duke {

    /** The storage component for managing data. */
    private Storage storage;

    /** The task list component for managing tasks. */
    private TaskList tasklist;

    private String FILE_PATH = "./data/emis.txt";

    public Duke() {
        storage = new Storage(FILE_PATH);
        try {
            tasklist = new TaskList(storage.loadFromStorageFile());
        } catch (EmisException e) {
            tasklist = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasklist, storage);
        } catch (EmisException e) {
            return e.getMessage();
        }
    }

}
