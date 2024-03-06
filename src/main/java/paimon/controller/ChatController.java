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

    private Image travellerImage = new Image(this.getClass().getResourceAsStream("/images/Traveller.png"));
    private Image paimonImage = new Image(this.getClass().getResourceAsStream("/images/Paimon.png"));
    /**
     * Initializes the chat controller.
     * Makes the view automatically jump to latest text.
     */
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
                DialogBoxController.getUserDialog(input, travellerImage),
                DialogBoxController.getPaimonDialog(response, paimonImage)
        );
        userInput.clear();
    }
}
