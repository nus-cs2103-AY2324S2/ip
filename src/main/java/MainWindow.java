import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.Greeting;

/**
 * The main window controller for the chat application.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private Duke duke;

    // Images for the user and Duke
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/snorlaxFlipped.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/snorlax.png")));

    // Greeting message
    private final String greetMessage = Greeting.display();

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        // Bind scrollPane to dialogContainer height to auto-scroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Display greeting message
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetMessage, dukeImage)
        );
    }

    /**
     * Sets the Duke object.
     *
     * @param d The Duke object to set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;

        if (Objects.equals(input, "bye")) {
            // Delay the exit for 1 second
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            response = "Closing application...";
        } else {
            response = duke.getResponse(input);
        }

        // Add user and Duke dialog boxes
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear(); // Clear input field
    }
}
