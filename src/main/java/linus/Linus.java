package linus;

/**
 * Main class for running Linus chatbot.
 */
public class Linus {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList;

    /**
     * Constructs Linus object for Linus Application
     */
    public Linus() {
        taskList = new TaskList(storage.loadTasksFromFile()); // Load list of tasks from file
    }

    /**
     *
     * @param command
     * @return
     */
    public String getResponse(String command) {
        Parser parser = new Parser(taskList, ui, storage);
        return parser.parseInputCommand(command);
    }
}
