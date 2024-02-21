package paimon.gui;

import paimon.Paimon;
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

    private Paimon paimon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Traveller.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Paimon.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBot(Paimon paimon) {
        this.paimon = paimon;
    }

    public void showInitialGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(paimon.greet(false), botImage)
        );
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Paimon's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = paimon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        if (input.equals("bye")) {
            // Pause for 6s before close the programme
            PauseTransition pause = new PauseTransition(Duration.millis(6000));
            pause.setOnFinished(f -> Platform.exit());
            pause.play();

        }
        userInput.clear();
    }
}
