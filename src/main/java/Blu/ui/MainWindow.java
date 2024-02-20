package blu.ui;

import blu.Blu;
import blu.exception.BluException;
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

    private Blu blu;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/account.png"));
    private Image bluImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    /**
     * Initializes the main window controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the reference to the main application class. This connection allows the UI to communicate with
     * the application logic.
     *
     * @param b The main application class.
     */
    public void setBlu(Blu b) {
        blu = b;
    }

    /**
     * Displays a greeting message in the dialog container at application startup.
     *
     * @param greeting The greeting message to display.
     */
    public void showGreeting(String greeting) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBluDialog(greeting, bluImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = blu.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBluDialog(response, bluImage)
            );
        } catch (BluException e) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.showErrorMessage(e.getMessage(), bluImage)
            );
        }

        userInput.clear();

        if (blu.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
