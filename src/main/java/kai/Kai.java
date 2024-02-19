package kai;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Represents the main class for the Kai program, a task management application.
 */
public class Kai extends Application {
    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initializes Kai with storage and other components.
     */
    public Kai() {
        storage = new Storage("./data/kai.txt");
        tasks = Storage.loadTasks();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        ui = new Ui();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    }

    /**
     * Displays the welcome message when Kai starts.
     */
    public String displayWelcomeMessage() {
        return ui.message();
    }

    /**
     * Processes user input and returns a boolean indicating whether to continue accepting input.
     *
     *
     * @return The string based on the tasks.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input, tasks);
        String result = parser.parseCommand();
        Storage.saveTasks(tasks);

        return result;
    }
}
