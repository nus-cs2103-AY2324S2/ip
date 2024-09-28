package linus;

/**
 * Represents the Linus chatbot class.
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
     * Returns response parsed from the specified command.
     *
     * @param command Command to be parsed into a response.
     * @return Response parsed from the specified command.
     */
    public String getResponse(String command) {
        Parser parser = new Parser(taskList, ui, storage);
        return parser.parseInputCommand(command);
    }
}
