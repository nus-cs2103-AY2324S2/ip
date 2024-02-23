package friendlytool.process;
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

    private FriendlyTool friendlytool;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image ftImage = new Image(this.getClass().getResourceAsStream("/images/friendlytool.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setFt(FriendlyTool ft) {
        friendlytool = ft;
    }

    /**
     * greets users when the program is first opened.
     */
    public void showGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.getInitMsg(), ftImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing FriendlyTool's response.
     * Clears the user input after receiving response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = friendlytool.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, ftImage)
        );
        userInput.clear();
    }
}
