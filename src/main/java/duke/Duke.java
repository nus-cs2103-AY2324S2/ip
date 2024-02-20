
package duke;


/**
 * The Duke class represents the main entry point for the Duke program.
 * It initializes the user interface, storage, and task list, and runs the program.
 */
public class Duke {
    private Storage storage; // Deals with loading and saving
    private TaskList tasks; // Operations to add and delete
    private final Ui ui; // Deals with interactions from user


    /**
     * Constructs a Duke object with the specified file path for storing tasks.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructs a Duke object with the specified file path for storing tasks.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Duke program.
     * Displays the greeting message, initializes a scanner for user input,
     * and starts the parsing process.
     */
    /* public void run() {
        ui.greet();
        Parser parser = new Parser(tasks, storage);
        parser.read();
    }

    /**
     * The main method that initializes a Duke object and runs the program.
     *
     * @param args Command-line arguments (not used in this program).
     */
    /*
    public static void main(String[] args) {
        Duke bearducky = new Duke("./data/tasks.txt");
        bearducky.run();
    }
    /*

    /**
     * Method that reads user input and generates a response
     *
     * @param input entry from user (not used in this program).
     */
    public String getResponse(String input) {
        Parser parser = new Parser(tasks, storage);
        String bearduckyresponse = parser.read(input);
        return bearduckyresponse;
    }

}
