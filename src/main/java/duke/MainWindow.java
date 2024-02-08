package duke;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Controller class for the main window of the Duke chat application.
 * This class handles user interaction, input processing, and displaying conversation dialogues.
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

    // Images for user and Duke (bot) avatars
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));

    /**
     * Initializes the main window controller.
     * Sets up scroll pane, button, and text field properties.
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
        String greeting = "Welcome! I'm FICIN.\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, dukeImage)
        );
    }

    /**
     * Sets the Duke instance for this MainWindow controller.
     * @param ficin The Duke instance to be set.
     */
    public void setDuke(Duke ficin) {
        duke = ficin;
    }

    /**
     * Handles user input processing.
     * Gets user input, passes it to Duke for response, and displays the conversation dialogue.
     * Clears the user input after processing.
     * If the user input is "bye", it triggers application exit after a short delay.
     *
     * @throws InterruptedException if interrupted while waiting for application exit.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        // Display user and Duke dialogue boxes
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        // Clear user input after processing
        userInput.clear();

        // If user input is "bye", exit the application after a short delay
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
