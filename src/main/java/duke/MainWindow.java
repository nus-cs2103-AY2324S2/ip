package duke;

import java.util.ArrayList;
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

    private GoldBot goldBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setGoldBot(GoldBot g) {
        goldBot = g;
    }

    // private Label getDialogLabel(String text) {
    //     // You will need to import `javafx.scene.control.Label`.
    //         Label textToAdd = new Label(text);
    //         textToAdd.setWrapText(true);

    //         return textToAdd;
    //     }

    public void printDukeMessage(String message) {
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(message, dukeImage)
        );
    }

    public void printUserMessage(String message) {
        dialogContainer.getChildren().add(
            DialogBox.getUserDialog(message, userImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        ArrayList<String> responses = goldBot.getResponse(input);
        printUserMessage(input);
        for (String response : responses) {
            printDukeMessage(response);
        }
        userInput.clear();
    }
}
