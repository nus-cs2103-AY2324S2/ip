package ellie;

import ellie.gui.DialogBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private Ellie ellie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up the initial dialog box with Duke's greeting message.
     * Binds the vertical scroll property of the scroll pane to the height property of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // bind the width of the dialog container to the width of the scroll pane
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showHelloMessage(), dukeImage)
        );
    }

    /**
     * Sets the Ellie object to be used by this controller.
     *
     * @param e The Ellie object to be used.
     */
    public void setEllie(Ellie e) {
        ellie = e;
    }

    /**
     * Handles the user input event.
     * Processes the user input, gets the response from Ellie,
     * updates the dialog container with the user input and Ellie's response.
     * Clears the user input field after processing.
     * Exits the application if the exit command is entered by the user.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ellie.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // Exit Command
        if (ellie.isExit()) {
            Platform.exit();
        }
    }
}
