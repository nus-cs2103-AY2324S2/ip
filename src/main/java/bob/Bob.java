package bob;

import bob.command.Command;
import bob.exception.BobException;

/**
 * Represents Bob itself. A <code>Bob</code> object corresponds to an instance of the program.
 */
public class Bob {
    private final Storage storage;
    private TaskList tasks;

    private String initialMessage;

    /**
     * Returns an instance of the program with its own storage, task list and UI.
     *
     * @param dataPath The file path of the storage.
     */
    public Bob(String dataPath) {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load(dataPath));
        } catch (BobException e) {
            initialMessage = Ui.getLoadingErrorResponse(e.getMessage());
            tasks = new TaskList();
        }

        initialMessage = Ui.getGreetResponse();
    }

    public String getInitialMessage() {
        return initialMessage;
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(storage, tasks);
        } catch (BobException e) {
            return e.getMessage();
        }
    }
}
