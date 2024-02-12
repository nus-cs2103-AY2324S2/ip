package duchess.controllers;

import duchess.Duchess;

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

    private Duchess duchess;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/candace.png"));
    private Image duchessImage = new Image(this.getClass().getResourceAsStream("/images/sharpay.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuchess(Duchess d) {
        duchess = d;

        dialogContainer.getChildren().addAll(
                DialogBox.getDuchessDialog(duchess.getWelcome(), duchessImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duchess's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duchess.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuchessDialog(response, duchessImage)
        );

        userInput.clear();
    }
}