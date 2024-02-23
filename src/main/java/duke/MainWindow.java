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

    private Duke duke;

    private boolean isWelcomeSent = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/img_5562.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/img_7470.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendWelcome();
    }

    /**
     * Creates and sends the welcome message if it hasn't been sent yet.
     */
    private void sendWelcome() {
        if (!isWelcomeSent) {
            String welcomeMsg = "Hi! I'm BernardBot\nWhat can I do for you?";
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(welcomeMsg, dukeImage)
            );
            isWelcomeSent = true; // Set the flag to true
        }
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.isEmpty() : "User input cannot be null or empty.";

        String response = duke.getResponse(input);

        if (response.equalsIgnoreCase("Bye. Hope to see you again soon!")) {
            Platform.exit();
            System.exit(0);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

    }
}
