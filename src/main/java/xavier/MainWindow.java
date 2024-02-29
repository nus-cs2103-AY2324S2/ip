package xavier;
import javafx.application.Platform;
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

    private Xavier xavier;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image xavierImage = new Image(this.getClass().getResourceAsStream("/images/DaXavier.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setXavier(Xavier x) {
        xavier = x;
    }

    /**
     * Creates a dialog box with the welcome message and appends it to the dialog container.
     */
    @FXML
    public void showWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getXavierDialog(xavier.showWelcome(), xavierImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing xavier's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = xavier.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getXavierDialog(response, xavierImage)
        );
        userInput.clear();
        Platform.runLater(() -> Main.exit(xavier.getIsExit()));
    }
}
