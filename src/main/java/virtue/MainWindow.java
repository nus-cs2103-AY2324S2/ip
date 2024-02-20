package virtue;

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

    private Virtue virtue;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaStoic.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/MrVirtue.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setVirtue(Virtue v) {
        virtue = v;
    }

    public void start() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(virtue.greet(), dukeImage));
        virtue.initialize();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Virtue's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = virtue.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (virtue.isExit) {
            userInput.setEditable(false);
            sendButton.setDisable(true);
            PauseTransition appPause = new PauseTransition(Duration.seconds(2));
            appPause.setOnFinished(event ->
                    Platform.exit());
            appPause.play();
        }
    }
}
