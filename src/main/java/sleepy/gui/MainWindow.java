package sleepy.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sleepy.Sleepy;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Jeffry Lum
 * @author kjw142857
 */
public class MainWindow extends HBox {
    @FXML
    private GridPane gridPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Sleepy sleepy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image sleepyImage = new Image(this.getClass().getResourceAsStream("/images/Sleepy.png"));

    /**
     * Initialises this window using FXML.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getSleepyDialog(Sleepy.WELCOME_TEXT, sleepyImage));
    }

    public void setSleepy(Sleepy s) {
        sleepy = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sleepy's reply,
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        String response = sleepy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSleepyDialog(response, sleepyImage)
        );
        userInput.clear();
        if (sleepy.isInExitState()) {
            PauseTransition closingDuration = new PauseTransition(Duration.seconds(2));
            closingDuration.setOnFinished(event -> Platform.exit());
            closingDuration.play();
        }
    }
}
