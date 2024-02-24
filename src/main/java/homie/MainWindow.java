package homie;

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

    private Homie homie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gojo.png"));
    private Image homieImage = new Image(this.getClass().getResourceAsStream("/images/homie.png"));

    /**
     * Initializes the chatbot GUI and also instantly shows a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Ui ui = new Ui();
        String welcomeMessage = ui.showWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getHomieDialog(welcomeMessage, homieImage));

    }

    public void setHomie(Homie h) {
        homie = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Homie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws HomieException {
        String input = userInput.getText();
        String response = homie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHomieDialog(response, homieImage)
        );
        userInput.clear();
    }
}
