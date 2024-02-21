package skyler.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import skyler.exception.SkylerException;

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

    private Skyler skyler;

    private Image skylerImage = new Image(this.getClass().getResourceAsStream("/images/Skyler.jpg"));
    private Image teddyImage = new Image(this.getClass().getResourceAsStream("/images/Teddy.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    public void setSkyler(Skyler s) {
        skyler = s;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;

        try {
            response = skyler.getResponse(input);
        } catch (SkylerException e) {
            response = "Skyler: Woof, " + e.getMessage() + "\n";
        }

        if (response.trim().isBlank()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, skylerImage));
            userInput.clear();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input,
                        skylerImage),
                DialogBox.getSkylerDialog(response, teddyImage));
        userInput.clear();
    }

    @FXML
    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getSkylerDialog("Welcome to Skyler!", teddyImage));
    }
}