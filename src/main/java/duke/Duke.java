package duke;


import java.io.IOException;
import java.util.Scanner;

/**
 * Duke Class is the main class that will run based on the different commands given
 * by the user. Available commands include todo, event, deadline, mark, unmark, delete.
 */
public class Duke {
    protected Ui ui = new Ui();
    private Storage storage;
    private TaskList taskList;
    private Parser parser = new Parser();
    private Scanner scanner;
    private boolean isEnded = false;
    private final String PATH = "data/tasks.txt";

    /**
     * Constructor for the Duke Class.
     * */
    public Duke() {
        this.storage = new Storage(this.PATH);
        this.taskList = new TaskList();
    }

    /**
     * Terminates the Duke program.
     */
    public String save() {
        this.storage.saveFile(this.taskList);
        this.isEnded = true;
        return "Tasks Saved!";
    }

    /**
     * Starts up and runs the Duke program.
     */
    public void run() {
        try {
            this.taskList = storage.loadFile();
        } catch (IOException e) {
            System.out.println("Run failed.");
        }
    }

    public String getResponse(String commandInput) {
        return this.parser.checkInput(commandInput, this, this.taskList);
    }
}
