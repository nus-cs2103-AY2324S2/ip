import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for javafx.MainWindow. Provides the layout for the other controls.
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

    private UncleBob uncleBob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image uncleBobImage = new Image(this.getClass().getResourceAsStream("/images/UncleBob.png"));

    /**
     * Display message on app initialization.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .add(DialogBox.getUncleBobDialog("Hello! I'm Uncle Bob \nWhat can uncle do for you?", uncleBobImage));
    }

    public void setUncleBob(UncleBob d) {
        uncleBob = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = uncleBob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getUncleBobDialog(response, uncleBobImage)
        );
        userInput.clear();
    }
}
