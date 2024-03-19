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
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/trainer.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/snorlax.png")));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        String greetMessage = Greeting.display();

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        dialogContainer.getChildren().addAll(
                DialogBox.getDialog(greetMessage, dukeImage, false)
        );
    }

    /**
     * Sets the Duke object.
     *
     * @param d The Duke object to set.
     */
    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Handles user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Objects.equals(input, "bye") ? exitApp() : duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getDialog(input, userImage, true),
                DialogBox.getDialog(response, dukeImage, false)
        );

        userInput.clear();
    }

    private String exitApp() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();

        return "Closing application...";
    }
}
