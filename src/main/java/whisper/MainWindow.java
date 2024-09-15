package whisper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Whisper whisper;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().
            getResourceAsStream("/images/DinoU.png")));
    private final Image whisperImage = new Image(Objects.requireNonNull(this.getClass().
            getResourceAsStream("/images/DinoBot.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getWhisperDialog("Hello! I'm Whisper, your personal chat bot!\n" +
                        "What can I do for you?\n", whisperImage));
        sendButton.setOnMouseClicked((event -> handleUserInput()));
        userInput.setOnAction((event -> {
            handleUserInput();
        }));
    }

    public void setWhisper(Whisper w) {
        whisper = w;
    }

    public void updateDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getWhisperDialog(message, whisperImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = whisper.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWhisperDialog(response, whisperImage)
        );
        userInput.clear();
    }
}