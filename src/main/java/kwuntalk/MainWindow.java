package kwuntalk;

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

    private KwunTalk kwuntalk;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image kwunTalkImage = new Image(this.getClass().getResourceAsStream("/images/DaKwun.jpg"));


    /**
     * Initializes the starting screen of the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /**
     * Sets the KwunTalk object for the main window.
     *
     * @param d The KwunTalk object to be set.
     */
    public void setKwunTalk(KwunTalk d) {
        kwuntalk = d;
        dialogContainer.getChildren().add(
                DialogBox.getKwunTalkDialog(kwuntalk.getGreeting(), kwunTalkImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the
     * other containing KwunTalk's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kwuntalk.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKwunTalkDialog(response, kwunTalkImage)
        );
        userInput.clear();
    }
}
