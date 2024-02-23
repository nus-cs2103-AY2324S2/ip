package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls and handles user interaction.
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
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserPhoto.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/JamiePhoto.png"));

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        Ui ui = new Ui();
        String welcomeMessage = ui.showWelcome();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    /**
     * Sets the Duke instance for the controller.
     *
     * @param d The Duke instance to be used.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles the action of the user pressing the send button or entering input.
     * It creates dialog boxes for the user input and Duke's reply, then clears the input.
     * It will also close the application if the user inputs "bye".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim(); // Trim input to remove leading and trailing whitespaces
        if (!input.isEmpty()) { // Check if the input is not empty
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage));
            userInput.clear(); // Clear the input after processing
            if (input.equalsIgnoreCase("bye")) {
                closeMainWindow();
            }
        }
    }

    /**
     * Closes the main window and exits the application.
     */
    private void closeMainWindow() {
        Platform.exit(); // Close the platform and exit the application
    }
}
