package henry;

import java.io.IOException;

import henry.command.Command;

/**
 * Represents the Henry application.
 */
public class Henry {
    private Storage storage;
    private TaskList tasks;

    /**
     * Represents a Henry chatbot that stores and manages tasks.
     *
     * @param filePath The file path to store the tasks.
     */
    public Henry(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | HenryException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                System.exit(0);
            }
            return c.execute(tasks, storage);
        } catch (HenryException e) {
            return e.getMessage();
        }
    }
}
