package paimon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import paimon.DialogHandler;

/**
 * Controller for MainView. Provides the layout for the other controls.
 */
public class ChatController extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DialogHandler dialogHandler;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Traveller.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Paimon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDialogHandler(DialogHandler d) {
        this.dialogHandler = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dialogHandler.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBoxController.getUserDialog(input, userImage),
                DialogBoxController.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
