package youdon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The main class that starts the Youdon chatbot application.
 */
public class Youdon {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a new instance of the Youdon class.
     * Initializes the user interface, storage, task list, and parser.
     */
    public Youdon() {
        // initialise ui, storage (filepath = "./data/save.txt") and tasklist
        this.ui = new Ui();
        this.storage = new Storage("./data/save.txt");
        this.tasks = new TaskList(storage.loadData());
        this.parser = new Parser(this.ui, this.tasks, this.storage);
    }

    /**
     * The main method of the application.
     * This method is currently empty as the Youdon application is not meant to be run via the main method directly.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {

    }

    /**
     * Gets the response from the Youdon chatbot for the given input.
     *
     * @param input The user input to process.
     * @return The response generated by the chatbot.
     */
    protected String getResponse(String input) {
        return parser.parse(input);
    }

}
