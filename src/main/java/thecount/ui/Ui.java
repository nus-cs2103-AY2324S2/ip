package thecount.ui;

import thecount.parser.Parser;
import thecount.storage.Storage;
import thecount.task.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private Parser parser;
    private Storage loader;
    private Reply greeting = new Greeting();

    /**
     * Constructs a new UI instance.
     *
     * @param tasks The task list to be managed by the UI.
     * @param loader The storage component to interact with file storage.
     */
    public Ui(TaskList tasks, Storage loader) {
        this.parser = new Parser(tasks, loader);
        this.loader = loader;
    }

    /**
     * Prints the greeting message to the user.
     */
    public String printGreeting() {
        return greeting.displayMessage();
    }

    /**
     * Initiates the user interface and begins application execution.
     */
    public String run() {
        return printGreeting();
//        parser.parse();
    }
}
