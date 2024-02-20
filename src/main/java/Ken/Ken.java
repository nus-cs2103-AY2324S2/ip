package ken;

import javafx.fxml.FXML;
import ken.exception.KenException;
import ken.parser.Parser;
import ken.response.Response;
import ken.storage.Storage;
import ken.task.TaskList;
import ken.ui.Ui;

/**
 * The main class representing the Ken application.
 * Ken is a task management Chat-Bot application that allows users to
 * manage their tasks through a command-line interface.
 */
public class Ken {
    private static final String FILE_PATH = "./data/ken.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Ken object.
     *
     * //@param filePath The file path for storing tasks.
     */

    public Ken() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (KenException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    @FXML
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(tasks, storage);
            Response response = parser.processUserCommands(input);  // Call the processUserCommands method
            return response.getMessage();  // Return the response message
        } catch (KenException e) {
            return "Error processing user command: " + e.getMessage();
        }
    }


    /**
     * Runs the Ken application.
     * It displays a welcome message, initializes the parser, and
     * starts processing user commands until the user says goodbye.
     */
    public void run() {

    }



    /**
     * The entry point of the Ken application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Ken().run();
    }
}
