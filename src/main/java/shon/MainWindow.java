package shon;

import java.time.format.DateTimeParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shon.component.ShonDialogBox;
import shon.component.UserDialogBox;
import shon.exception.CommandException;
import shon.exception.ParameterException;

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

    private Shon shon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image shonImage = new Image(this.getClass().getResourceAsStream("/images/botImage.png"));

    /**
     * Sets auto scroll, and displays greeting message.
     */
    @FXML
    public void initialize() {
        // set auto scroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the chatbot to the Shon instance provided.
     * @param s The Shon instance to be used.
     */
    public void setShon(Shon s) {
        shon = s;
        dialogContainer.getChildren().addAll(new ShonDialogBox(this.shon.greet(), shonImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Shon's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        dialogContainer.getChildren().addAll(new UserDialogBox(input, userImage));
        try {
            dialogContainer.getChildren().addAll(new ShonDialogBox(shon.getResponse(input), shonImage));
        } catch (ParameterException | CommandException | DateTimeParseException e) {
            dialogContainer.getChildren().addAll(ShonDialogBox.getErrorDialog(e.getMessage(), shonImage));
        }
        userInput.clear();
    }
}
