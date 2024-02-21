package duke;
/**
 * Duke is the main class for the Duke chatbot application.
 * It initializes the application and starts the interaction with the user.
 *
 * @author Qin Boan
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke object.
     */
    public Duke() {
        String filePath = "./data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(); // Start with an empty TaskList if loading fails
        }
    }

    /**
     * Runs the Duke application. This method reads and processes user input
     * until the application is exited.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String command = ui.readCommand();
                // Create a Parser instance and parse the command
                Parser parser = new Parser(tasks, ui, storage);
                parser.parse(command);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            assert !input.isEmpty() : "Input cannot be empty.";
            Parser parser = new Parser(tasks, ui, storage);
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * The main entry point for the Duke application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

