
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
package duke.Ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
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
    @FXML
    private Scene scene;
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/shaunbot.jpeg"));

    @FXML
    public void initialize() {
        dialogContainer.setStyle("-fx-background-color: #FFE4C9;");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String result = "Hello! I'm Shaunbot\n\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(result, dukeImage));

        setupSendButtonAnimation();
    }

    private void setupSendButtonAnimation() {
        // Fade in animation for the send button
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), sendButton);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Scale animation for hover effect
        sendButton.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), sendButton);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        sendButton.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), sendButton);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
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
        String input = userInput.getText().strip();
        if (input.isBlank()) {
            return;
        }
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Thread delayThread = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            });
            delayThread.start();
        }
    }
}