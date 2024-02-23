package linus;

import java.util.ArrayList;

/**
 * Main class for running Linus chatbot.
 */
public class Linus {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList();

    /**
     * Constructs Linus object for Linus Application
     */
    public Linus() {
        taskList = new TaskList(storage.loadTasksFromFile()); // Load list of tasks from file
    }

    /**
     * Runs the Linus application.
     * Displays a welcome message and continuously waits for user input.
     * Processes user commands and performs corresponding actions.
     * Exits the application when the "bye" command is entered.
     */
    public String getResponse(String command) {
        Parser parser = new Parser(taskList, ui, storage);
        return parser.parseInputCommand(command);
    }
}
