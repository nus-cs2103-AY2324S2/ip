package headcube;

import javafx.application.Application;
/**
 * The main class for the HeadCube application. This class sets up the application
 * and starts the user interaction process.
 */
public class HeadCube {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    /**
     * Constructs a HeadCube application instance. Initializes the UI, storage, task list and parser.
     */
    public HeadCube() {
        ui = new Ui();
        storage = new Storage(ui);
        tasks = new TaskList();
        storage.load(tasks);
        parser = new Parser(ui, tasks, storage);
    }

    /**
     * Retrieves the parser instance used for parsing user input or commands.
     *
     * @return The parser instance associated with this class.
     */
    public Parser getParser() {
        return this.parser;
    }


    /**
     * Launches the application
     *
     * @param args Input arguments.
     */
    public void run(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * The main entry point for the HeadCube application.
     *
     * @param args Input arguments.
     */
    public static void main(String[] args) {
        new HeadCube().run(args);
    }
}
