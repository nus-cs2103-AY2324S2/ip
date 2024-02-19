package yue;
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

    private Yue yue;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dog.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage(); // Call method to show welcome message
    }

    /**
     * Displays the welcome message in the dialog container.
     */
    private void showWelcomeMessage() {
        String welcomeMsg = "Hello! I'm BotYue.\n" + " What can I do for you?";
        dialogContainer.getChildren().add(YueDialogBox.getDialog(welcomeMsg, dukeImage));
    }

    public void setDuke(Yue d) {
        yue = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws YueException {
        String input = userInput.getText();
        String response = yue.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getDialog(input, userImage),
                YueDialogBox.getDialog(response, dukeImage)
        );
        userInput.clear();
    }
}