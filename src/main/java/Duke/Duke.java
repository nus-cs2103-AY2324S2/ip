package Duke;

/**
 * Represents the main class of the Duke application.
 * <p>
 * This class is responsible for initializing and running the Duke application.
 * It integrates various classes in an OOP structure - Storage, TaskList, Parser, and Ui.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    /**
     * Initializes a new Duke application.
     * <p>
     * This constructor initializes the Ui, Parser, Storage, and TaskList components of the Duke application.
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser(this.ui);
        this.storage = new Storage(this.ui, this.parser);
        this.taskList = new TaskList(this.ui, this.parser, this.storage);
    }

    /**
     * Starts the execution of the Duke application.
     * <p>
     * This method triggers the main functionality of the application,
     * starting with the task list functionalities.
     */
    public void run() {
        this.taskList.listFunction();
    }

    /**
     * The entry point of the Duke application.
     * <p>
     * This main method initializes a new Duke instance and starts its execution.
     *
     * @param args The command-line arguments.
     */

    public String runCommand(String command) {
        // run a single command in TaskList
        return taskList.runCommand(command);
    }
    public static void main(String[] args){
        // new Duke().run(); // uncomment for CLI app
    }


}

