package headcube;
import java.util.Scanner;

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
     * Constructs a HeadCube application instance. Initializes the UI, storage, and task list.
     */
    public HeadCube() {
        ui = new Ui();
        storage = new Storage(ui);
        tasks = new TaskList();
    }

    /**
     * Starts the application. Loads tasks from storage, displays the greeting message,
     * and enters the command processing loop.
     */
    public void run() {
        storage.load(tasks);
        ui.greet();
        this.parser = new Parser(ui, tasks, storage);
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        while (!string.equals("bye")) {
            parser.parse(string);
            string = scanner.nextLine();
        }
        ui.exit();
    }

    /**
     * The main entry point for the HeadCube application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new HeadCube().run();
    }
}