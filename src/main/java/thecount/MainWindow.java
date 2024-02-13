package thecount;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import thecount.ui.Greeting;
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

    private TheCount theCount;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/elmo.png"));
    private Image theCountImage = new Image(this.getClass().getResourceAsStream("/images/thecount.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String response = new Greeting().displayMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getCountDialog(response, theCountImage)
        );
    }

    public void setTheCount(TheCount tc) {
        theCount = tc;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = theCount.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCountDialog(response, theCountImage)
        );
        userInput.clear();
    }
}
