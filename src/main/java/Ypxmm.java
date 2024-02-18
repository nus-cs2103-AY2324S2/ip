import java.util.ArrayList;

import commands.Command;
import exceptions.YpxmmException;
import parsing.Parser;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

/**
 * Main class for the Ypxmm application.
 */
public class Ypxmm {
    /** The storage for tasks. */
    private Storage storage;
    /** The task list. */
    private TaskList tasks;
    /** The user interface. */
    private Ui ui;

    /**
     * Constructs a Ypxmm object. Initializes the user interface, storage, and tasks. It loads tasks from the
     * storage into the task list. If an exception occurs during the initialization process, it handles the
     * exception by displaying the error message in the user interface.
     */
    public Ypxmm() {
        ui = new Ui();
        storage = new Storage("/data/Ypxmm.txt");
        try {
            tasks = new TaskList(storage.loadTasksIntoTaskList());
        } catch (YpxmmException y) {
            getResponse(y.getMessage());
        }
    }

    /**
     * Retrieves a response based on the input provided.
     *
     * This method parses the input string, identifies the command to execute, and executes the command.
     * The method catches any YpxmmException that may occur during command execution and returns the error message.
     *
     * @param input the input string containing the command to be executed
     * @return a string representing the response to the input command
     */
    public String getResponse(String input) {
        try {
            ArrayList<String> parsed = Parser.parse(input);
            assert !parsed.isEmpty() : "Parser has failed";
            Command command = Command.valueOf(parsed.get(0).toUpperCase());
            return command.execute(tasks, ui, storage, parsed);
        } catch (YpxmmException y) {
            return y.getMessage();
        }
    }
}
