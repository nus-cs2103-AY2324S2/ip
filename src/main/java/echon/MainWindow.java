package echon;

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
public class MainWindow extends AnchorPane implements EchonUi {
    // @@author benson1029-reused
    // Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Echon echon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image echonImage = new Image(this.getClass().getResourceAsStream("/images/DaEchon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    // @@author

    public void startEchon() {
        echon = new Echon(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Echon's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        echon.processCommand(input);
        userInput.clear();
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
    */
    public void displayEchonMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getEchonDialog(message, echonImage));
    }

    /**
     * Prints a multi-line message to the user.
     *
     * @param messages The list of message lines to be printed.
     */
    public void displayEchonMessages(ArrayList<String> messages) {
        dialogContainer.getChildren().add(DialogBox.getEchonDialog(
                String.join("\n", messages), echonImage));
    }
}
