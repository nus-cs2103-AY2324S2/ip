package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for MainWindow.fxml. This class handles user interactions with the GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    // Images for the user and Duke. Adjust the paths as necessary.
    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window controller. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for this controller.
     *
     * @param d The Duke instance to be used.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates dialog boxes for both the user input and Duke's response, then
     * adds them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input); // Ensure you have a getResponse method in Duke

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(new Label(input), new ImageView(userImage)),
            DialogBox.getDukeDialog(new Label(response), new ImageView(dukeImage))
        );
        userInput.clear();
    }

    /**
     * Displays a welcome message to the user upon application start.
     */
    public void displayWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(welcomeMessage), new ImageView(dukeImage)));
    }
}
