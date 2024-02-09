package duke.ui.gui;

import duke.Duke;
import duke.exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class is the controller for the main window of the Duke GUI application.
 * It provides the layout for the user interface components such as the scroll pane, dialog container, user input field,
 * and send button.
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window.
     * Binds the scroll pane's vertical value property to the height property of the dialog container
     * to ensure automatic scrolling as new dialog boxes are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for the main window.
     *
     * @param duke The Duke instance to be used by the main window.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Handles user input by processing it with Duke and displaying the corresponding dialog boxes
     * in the dialog container.
     * Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response;

        try {
            response = duke.getResponse(input);
        } catch (DukeException dukeException) {
            response = String.format("Error: %s", dukeException.getMessage());
        }

        // Display user and Duke dialog boxes
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );

        // Clear the user input field
        userInput.clear();
    }
}
