package zack;

import java.io.IOException;

import zack.commands.Command;
import zack.util.Parser;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;


/**
 * The main class of the Zack program that handles user interactions and task management.
 */
public class Zack {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Zack instance without specifying a custom file path for data storage.
     * This constructor initializes Zack with default data storage settings, loading tasks
     * from the default file path "data/tasks.txt" if available, or starting with an empty task list
     * if the file is not found or an error occurs during loading.
     *
     * @throws ZackException If there is an error during data loading, a ZackException is thrown
     *                      and an error message is displayed through the user interface (UI).
     */
    public Zack() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList();
        try {
            tasks = new TaskList(storage.load());
        } catch (ZackException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public TaskList getTasks() {
        return this.tasks;
    }



    /**
     * Processes a given string input and returns a response. The method parses the
     * input to identify the command it represents, executes the command, and then
     * returns the result of the command execution. If an error occurs during parsing
     * or execution, an appropriate error message is returned. This method centralizes
     * the handling of all commands and their responses within the application.
     *
     * @param input The user input string to be processed.
     * @return A string representing the response to the input command. If the command
     *         execution is successful, the response of the command is returned. If an
     *         error occurs, an error message is returned.
     * @throws ZackException If an error specific to the application's logic occurs.
     * @throws RuntimeException If an I/O error occurs, encapsulated within a RuntimeException.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (ZackException e) {
            return e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Enumeration representing different types of tasks.
     */
    public enum TaskType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE, DATE, FIND, SORT
    }
}
