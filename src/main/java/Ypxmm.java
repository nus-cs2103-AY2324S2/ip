import java.util.ArrayList;

import commands.Command;

import parsing.Parser;

import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

import exceptions.YpxmmException;

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
            // Parse the input string
            ArrayList<String> parsed = Parser.parse(input);

            // Identify the command to execute
            Command command = Command.valueOf(parsed.get(0).toUpperCase());

            // Execute the command and return the response
            return command.execute(tasks, ui, storage, parsed);
        } catch (YpxmmException y) {
            // Catch and handle YpxmmException
            return y.getMessage();
        }
    }
}