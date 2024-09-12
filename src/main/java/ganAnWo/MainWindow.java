package ganAnWo;

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

    private GanAnWo ganAnWo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kuromi.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

    /**
     * Creates first scene and welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    public void setDuke(GanAnWo d) {
        ganAnWo = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ganAnWo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates a welcome message using the dialog box for bot.
     */
    @FXML
    private void showWelcome() {
        String dukeText = "Hello! I'm GanAnWo\n"
                + "What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }
}
