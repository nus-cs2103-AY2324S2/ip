package duke;



import javafx.application.Application;
import javafx.stage.Stage;



/**
 * Duke is a task management application that allows users to manage their tasks.
 * It provides a command-line interface and a JavaFX GUI for users to interact with their task list.
 */
public class Duke extends Application {
    private static final String FILE_PATH = "./data/duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path for loading and saving tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * JavaFX application entry point.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        // Create a JavaFX GUI instance and set up the stage
        // You can leave this method empty if you are not using JavaFX directly in Duke
    }




    /**
     * Gets the response for a given input.
     *
     * @param input The user input.
     * @return The response from Duke.
     */
    public String getResponse(String input) {

        try {


            String output = Parser.parseAndExecute(input, tasks, ui);

            storage.saveTasks(tasks.getTasks());
            return output;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * The main entry point for the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch(args);
    }
}

