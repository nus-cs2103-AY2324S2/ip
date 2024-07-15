package knight;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Makes a controller for MainWindow. Provides the layout for the other controls.
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

    private Knight knight;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image knightImage = new Image(this.getClass().getResourceAsStream("/images/knight.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String openingMessage = "Greetings, Your Excellency! Thy humble knight "
                + "doth stand before thee. How may I serve thee on this day?";
        dialogContainer.getChildren().add(
                DialogBox.getKnightDialog(openingMessage, knightImage)
        );
    }

    public void setKnight(Knight k) {
        knight = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Knight's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = knight.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKnightDialog(response, knightImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }
}

