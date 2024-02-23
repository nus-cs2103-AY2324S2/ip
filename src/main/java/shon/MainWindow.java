package shon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shon.component.DialogBox;
import shon.exception.CommandException;
import shon.exception.ParameterException;

import java.time.format.DateTimeParseException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image shonImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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
        dialogContainer.getChildren().addAll(DialogBox.getShonDialog(this.shon.greet(), shonImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Shon's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        try {
            dialogContainer.getChildren().addAll(DialogBox.getShonDialog(shon.getResponse(input), shonImage));
        } catch (ParameterException | CommandException e) {
            dialogContainer.getChildren().addAll(DialogBox.getErrorDialog(e.getMessage(), shonImage));
        } catch (DateTimeParseException e) {
            String errorMsg = e.getParsedString() + " is not a valid date/time. "
                    + "Please enter the date/time in \"dd/mm/yyyy hhmm\" format with valid values.";
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(errorMsg, shonImage));
        }
        userInput.clear();
    }
}
