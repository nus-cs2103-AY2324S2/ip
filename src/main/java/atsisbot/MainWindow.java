package atsisbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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

    private AtsisBot atsisBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window of the application.
     * Binds the scroll pane to the dialog container to automatically scroll to the bottom.
     * Sets fonts for the send button and user input field.
     * Displays a welcome message from Duke when the application starts.
     */
    @FXML
    public void initialize() {
        // Bind scroll pane to dialog container to automatically scroll to the bottom
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Set fonts for send button and user input field
        sendButton.setFont(Font.font("Helvetica"));
        userInput.setFont(Font.font("Helvetica"));

        // Display a welcome message from Duke when the application starts
        String greeting = "Welcome! I'm ATSISBot.\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, dukeImage));
    }

    public void setAtsisBot(AtsisBot atsisBot) {
        this.atsisBot = atsisBot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String param = Parser.parseArgs(input);
        CommandEnum command = Parser.parseCommand(input);
        String response = atsisBot.getResponse(command, param);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
