package SamuelBot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

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

    private Samuelbot samuelbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Mario.png"));
    private Image samuelImage = new Image(this.getClass().getResourceAsStream("/images/Luigi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Initialize Samuelbot instance
        samuelbot = new Samuelbot();

        // Display welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Hello from SamuelBot! What can I do for you?", samuelImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing SamuelBot's reply.
     * Appends them to the dialog container and clears the user input field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = samuelbot.getResponse(input);

        // Create DialogBox for user input
        DialogBox userDialog = DialogBox.getUserDialog("User: " + input, userImage);

        // Create DialogBox for Samuel's response
        DialogBox samuelDialog = DialogBox.getDukeDialog("SamuelBot: " + response, samuelImage);

        // Add both DialogBoxes to the dialog container
        dialogContainer.getChildren().addAll(userDialog, samuelDialog);

        // Clear the user input field
        userInput.clear();
    }

}
