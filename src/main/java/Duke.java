import Duke.Parser;
import Duke.Storage;
import Duke.Ui;
import Task.TaskList;


import java.io.FileNotFoundException;

/**
 *  This class contains the main method for the chatbot, ChrisP Bacon.
 *  ChrisP Bacon is a chatbot that manages the user's list of tasks.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialises new ui and storage objects, loads tasks into a new task list object.
     *
     * @param filePath of the saved task list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printError("Oink! File not found :(");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot program.
     */
    public void run() {
        Parser parser = new Parser(this.tasks);
        parser.parse();
        storage.save(this.tasks);
    }

    /**
     * Initialises and run duke program.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }
}
