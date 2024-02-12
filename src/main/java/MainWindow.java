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

    private GeePeeTee geepeetee;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/geepeetee.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .add(DialogBox.getGeePeeTeeDialog("Hello! I'm GeePeeTee. How can I help you?", botImage));
        scrollPane.setStyle("-fx-background: #010101;");
    }

    public void setGeePeeTee(GeePeeTee geepeetee) {
        this.geepeetee = geepeetee;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = geepeetee.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGeePeeTeeDialog(response, botImage));
        userInput.clear();
    }

    public void handleInitializationError(String errorMessage) {
        dialogContainer.getChildren().add(DialogBox.getGeePeeTeeDialog(errorMessage, botImage));
    }
}
