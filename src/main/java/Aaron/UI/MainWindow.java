package aaron.ui;

import aaron.AaronBot;
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

    private AaronBot aaron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image aaronImage = new Image(this.getClass().getResourceAsStream("/images/Aaron-Tan.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAaron(AaronBot aaronBot) {
        aaron = aaronBot;
        start();
    }

    /**
     * Creates the initial dialogue when starting a chat with AaronBot
     */
    @FXML
    public void start() {
        String intro = aaron.startChat();
        dialogContainer.getChildren().addAll(
                DialogBox.getAaronDialog(intro, aaronImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aaron.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAaronDialog(response, aaronImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}