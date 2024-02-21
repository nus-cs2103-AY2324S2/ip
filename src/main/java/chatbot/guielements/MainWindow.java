package chatbot.guielements;
import chatbot.Alfred;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Alfred alfred;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/batman.png"));
    private Image alfredImage = new Image(this.getClass().getResourceAsStream("/images/alfred.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAlfred(Alfred a) {
        alfred = a;
    }

    /**
     * Shows the greeting message from Alfred.
     */
    public void showGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getAlfredDialog(alfred.greet(), alfredImage)
        );
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = getUserInput();
        String response = getResponse(input);
        checkForExit(response);
        displayDialog(input, response);
        clearUserInput();
    }

    private String getUserInput() {
        return userInput.getText();
    }

    private String getResponse(String input) {
        return alfred.getResponse(input);
    }

    private void checkForExit(String response) {
        if (response.equals("Bye. Hope to see you again soon!")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    private void displayDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlfredDialog(response, alfredImage)
        );
    }

    private void clearUserInput() {
        userInput.clear();
    }
}

