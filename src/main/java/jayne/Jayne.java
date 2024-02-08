package jayne;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import jayne.task.TaskList;

/**
 * Represents the main class for the Jayne application.
 * This class encapsulates the initialization of core components
 * such as Ui, TaskList, and Storage, and contains the main program loop.
 */
public class Jayne {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Scene scene;
    @FXML
    private Image user = new Image(this.getClass().getResourceAsStream("/images/SnowieeCrop.png"));
    @FXML
    private Image jayne = new Image(this.getClass().getResourceAsStream("/images/Jayne.png"));
    private boolean isEnd = true;
    private TaskList taskList;
    private Storage storage;
    /**
     * Constructs a new Jayne object.
     * Initializes the UI, storage, and task list components.
     *
     * @param filepath the path to the file where tasks are saved and loaded from.
     */
    public Jayne(String filepath) throws JayneException {
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage);
    }
    /**
     * Processes a user's input command, executes it, and returns a formatted response message.
     * It checks for empty input, throwing a {@link JayneException} if found. On successful command
     * execution, it prepends a customized greeting to the response. If the input indicates the end
     * of the session, it updates the application state accordingly. Errors during processing are
     * caught and returned as formatted error messages.
     *
     * @param input The user's command as a string.
     * @return A greeting followed by the command's execution result, or an error message.
     */
    public String run(String input) {
        Parser parser = new Parser(taskList);
        String msg = "";
        try {
            if (input.isEmpty()) {
                throw new JayneException("Input cannot be empty.");
            }
            msg = parser.parse(input);
            if (Objects.equals(msg, "Bye. Hope to see you again soon!")) {
                this.isEnd = false;
            }
            return "Hey, Snowieeee, " + msg; //HERE
        } catch (JayneException e) {
            return "\nHuh?!?!? " + e.getMessage() + "\n";
        }
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Jayne's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String jayneText = run(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getJayneDialog(jayneText, jayne)
        );
        userInput.clear();
    }
}
