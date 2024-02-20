package goldbot;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private GoldBot goldBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image goldbotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setGoldBot(GoldBot g) {
        goldBot = g;
    }
    public void printBotMessage(String message) {
        dialogContainer.getChildren().add(
            DialogBox.getBotDialog(message, goldbotImage)
        );
    }

    public void printUserMessage(String message) {
        dialogContainer.getChildren().add(
            DialogBox.getUserDialog(message, userImage)
        );
    }

    public void closeWindow() {
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing GoldBot's 
     * reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        ArrayList<String> responses = goldBot.getResponse(input);
        printUserMessage(input);
        for (String response : responses) {
            printBotMessage(response);
        }
        if (goldBot.shouldExit()) {
            closeWindow();
        }
        userInput.clear();
    }
}
