package earl.gui;

import earl.Earl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Earl earl;

    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/Johnson.png"));
    private Image earlImage = new Image(
            this.getClass().getResourceAsStream("/images/Coronet.png"));

    /** Configures the ability scroll correctly. */
    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));
    }

    public void setEarl(Earl e) {
        earl = e;
    }

    /** Displays the initial message on startup. */
    public void showGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getEarlDialog(earl.getResponse(), earlImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.Clears the
     * user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Platform.exit();
            return;
        }
        String response = earl.getResponse(input);
        displayDialog(input, response);
    }

    /** Signals the Earl object to terminate execution. */
    public void handleExit() {
        earl.getResponse("bye");
    }

    /**
     * Displays the dialog boxes on the GUI.
     *
     * @param input     text to display on the user's side
     * @param response  text to display on the application's side
     */
    private void displayDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEarlDialog(response, earlImage));
        userInput.clear();
    }
}
