package thecount;

import thecount.exception.TheCountException;
import thecount.storage.Storage;
import thecount.task.TaskList;
import thecount.ui.Ui;

/**
 * Represents TheCount application.
 * Initialises and runs the application.
 */
public class TheCount {

    private Storage loader;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of TheCount.
     * Initialises the task list, storage, and user interface.
     */
    public TheCount() {
        this.tasks = new TaskList();
        this.loader = new Storage(this.tasks);
//        this.ui = new Ui(this.tasks, this.loader);
    }

//    public String getResponse(String input) {
//        try {
//            Command c = Parser.parse(input);
//            return c.execute(tasks, storage);
//        } catch (TheCountException e) {
//            return e.getMessage();
//        }
//    }

    public String getResponse(String input) {
        return "Duke heard " + input;
    }

    /**
     * Runs the application.
     * Starts the user interface.
     */
    public void run() {
        ui.run();
    }

    /**
     * Starts the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new TheCount().run();
    }
}
