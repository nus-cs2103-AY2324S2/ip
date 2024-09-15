package chronos;

import java.io.IOException;

import exception.ChronosException;
import tool.Parser;
import tool.Storage;
import tool.TaskList;
import tool.Ui;

/**
 * Represents the main class of the Chronos Task Management System.
 */
public class Chronos {
    private static final String FILE_PATH = "./data/chronos.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Constructs a Chronos object with the given file path.
     */
    public Chronos() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates Chronos' response to the given user input.
     *
     * @param input Input provided by the user.
     * @return Chronos' response.
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public String getResponse(String input) throws IOException, ChronosException {
        return Parser.processCommand(input.toLowerCase(), ui, storage, tasks);
    }
}
