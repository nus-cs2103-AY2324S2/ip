package Shirmin;

/**
 * Represents the main class of the Shirmin application.
 * <p>
 * This class is responsible for initializing and running the Shirmin application.
 * It integrates various classes in an OOP structure - Storage, TaskList, Parser, and Ui.
 */
public class Shirmin {
    private final TaskList taskList;

    /**
     * Initializes a new Shirmin application.
     * <p>
     * This constructor initializes the Ui, Parser, Storage, and TaskList components of the Shirmin application.
     */
    public Shirmin() {
        Parser parser = new Parser();
        Storage storage = new Storage(parser);
        this.taskList = new TaskList(parser, storage);
    }

    /**
     * The entry point of the Shirmin application.
     * <p>
     * This main method initializes a new Shirmin instance and starts its execution.
     *
     */
    public String runCommand(String command) {
        // run a single command in TaskList
        return taskList.runCommand(command);
    }
}

