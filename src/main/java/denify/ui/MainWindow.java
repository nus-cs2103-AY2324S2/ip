package denify.ui;

import java.util.Objects;

import denify.core.Denify;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Denify denify;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/DaUser.png")));
    private final Image denifyImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/DaDenify.png")));
    /**
     * Initializes the main window.
     * Binds the scroll pane to the height of the dialog container.
     * Adds a welcome message from Denify to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String input = "Hello! I'm Denify\n" + "What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDenifyDialog(input, denifyImage)
        );
    }
    public void setDenify(Denify d) {
        denify = d;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Denify's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = denify.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDenifyDialog(response, denifyImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
