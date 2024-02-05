import java.io.FileNotFoundException;

import commands.Command;
import exception.UncleBobException;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * javafx.Main class representing the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath the file path for loading and saving tasks
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (UncleBobException e) {
            return e.getMessage();
        }
    }
}
