package Duke;

/**
 * Represents the main class of the Duke application.
 * <p>
 * This class is responsible for initializing and running the Duke application.
 * It integrates various classes in an OOP structure - Storage, TaskList, Parser, and Ui.
 */
public class Shirmin {
    private final TaskList taskList;

    /**
     * Initializes a new Duke application.
     * <p>
     * This constructor initializes the Ui, Parser, Storage, and TaskList components of the Duke application.
     */
    public Shirmin() {
        Parser parser = new Parser();
        Storage storage = new Storage(parser);
        this.taskList = new TaskList(parser, storage);
    }



    /**
     * The entry point of the Duke application.
     * <p>
     * This main method initializes a new Duke instance and starts its execution.
     *
     */

    public String runCommand(String command) {
        // run a single command in TaskList
        return taskList.runCommand(command);
    }


}

