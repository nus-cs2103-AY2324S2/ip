package shon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shon.component.DialogBox;

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
        String response = shon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getShonDialog(response, shonImage)
        );
        userInput.clear();
    }
}
