package arc.ui.gui;

import arc.Arc;
import arc.commands.Command;
import arc.exceptions.ArcException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class is the controller for the main window of the Arc GUI application.
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

    private Arc arc;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image arcImage = new Image(this.getClass().getResourceAsStream("/images/DaArc.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() throws ArcException {
        // Ensure automatic scrolling as new dialog boxes are added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Add initial help message
        dialogContainer.getChildren().addAll(
            DialogBox.getArcDialog(Command.HELP_MESSAGE, arcImage)
        );
    }

    /**
     * Sets the Arc instance for the main window.
     *
     * @param arc The Arc instance to be used by the main window.
     */
    public void setArc(Arc arc) {
        this.arc = arc;
    }

    /**
     * Handles user input by processing it with Arc and displaying the corresponding dialog boxes
     * in the dialog container.
     * Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() throws ArcException {
        String input = userInput.getText();
        String response;

        try {
            response = arc.getResponse(input);
        } catch (ArcException arcException) {
            response = String.format("Error: %s", arcException.getMessage());
        }

        // Display user and Arc dialog boxes
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getArcDialog(response, arcImage)
        );

        // Clear the user input field
        userInput.clear();
    }
}
