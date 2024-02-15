package seiki.ui;

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
import javafx.util.Duration;
import seiki.Seiki;
import seiki.common.Messages;


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

    private Seiki seiki;

    private final Image userImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image seikiImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/SeikiIcon.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSeiki(Seiki s) {
        seiki = s;
    }

    /**
     * Generates and prints the welcome message upon the start of the chatbot.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Messages.MESSAGE_GREETING, seikiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = seiki.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, seikiImage)
        );
        userInput.clear();

        if (seiki.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
