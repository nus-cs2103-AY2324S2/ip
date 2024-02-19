import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lery.LeryException;

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

    private Lery lery;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image leryImage = new Image(this.getClass().getResourceAsStream("/images/LeryDog.JPG"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLery(Lery l) {
        lery = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = lery.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLeryDialog(response, leryImage, false)
            );
            userInput.clear();
        } catch (LeryException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userInput.getText(), userImage),
                    DialogBox.getLeryDialog(e.getMessage(), leryImage, true)
            );
        }
    }

    /**
     * Greets the user by displaying a welcome message from Lery
     * or an error message if there's an issue with chat initialization.
     * Load tasks from the text file.
     * The method appends the greeting message to the dialog container.
     *
     */
    public void greet() {
        try {
            dialogContainer.getChildren().addAll(
                    DialogBox.getLeryDialog(lery.initialiseChat(), leryImage, false)
            );
        } catch (LeryException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getLeryDialog(e.getMessage(), leryImage, true)
            );
        }
    }
}
