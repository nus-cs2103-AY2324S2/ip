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
     * Gets Response for a String input.
     *
     * @param input provided.
     * @return String response message
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
     */
    public void run() {

    }
    public static void main(String[] args) {
        new Ken().run();
    }
}
