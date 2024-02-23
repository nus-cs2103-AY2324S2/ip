package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import signal.Signal;

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

    private Signal signal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userBlueOct.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/signalPinkOct.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Signal d) {
        signal = d;
    }

    /**
     * Handles displaying the introduction message from Signal.
     */
    public void handleSignalIntro() {
        String introMessage = signal.getIntro(); // Assuming Signal has a method to retrieve the intro message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(introMessage, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Signal's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = signal.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
