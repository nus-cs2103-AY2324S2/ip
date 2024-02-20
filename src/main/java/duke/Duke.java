package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A chatbot programme named Homie that helps you keep track
 * of to-do tasks, deadlines and events. Date and time can be specified for deadlines and events.
 * Other functions include adding tasks, finding tasks, marking or un-marking tasks as done,
 * deleting tasks and listing tasks.
 */
public class Duke {
    private static final String CURRENT_DIR = System.getProperty("user.dir");
    public static final Path FILE_PATH = Paths.get(CURRENT_DIR, "src", "main", "java", "duke", "data", "data.txt");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructor for Duke Application
     */
    public Duke() {
        // ...
    }

    /**
     * Constructor for Duke class
     * @param filePath File path of storage text file
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasksFromFile());
        ui.showLoadingError();
    }
    /**
     * Runs the chatbot application.
     */
    public void run() throws DukeException {
        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();

        Parser parser = new Parser(this.tasks, this.ui, this.storage);
        while (!parser.isExit()) {
            parser.parse(command);
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke(FILE_PATH).run();
    }
    /**
     * Returns a String response based on user input to the GUI.
     */
    public String getResponse(String input) throws DukeException {
        Parser parser = new Parser(this.tasks, this.ui, this.storage);
        String outputResponse = parser.parse(input);
        if (parser.isExit()) {
            return outputResponse;
        }
        return outputResponse;
    }
}
