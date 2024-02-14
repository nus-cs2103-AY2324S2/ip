package friday.main;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller class for the main window layout of the Friday application.
 * Manages user input handling, message display, and application exit functionality.
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

    private Friday friday;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Master.png"));
    private Image fridayImage = new Image(this.getClass().getResourceAsStream("/images/Friday.png"));

    /**
     * Initializes the controller after FXML loading.
     * Binds the vertical scroll position of the scroll pane to the height of the dialog container.
     * Displays a welcome message from Friday.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayWelcomeMessage();
    }

    /**
     * Sets the Friday instance for the controller.
     * @param f The Friday instance to set.
     */
    public void setFriday(Friday f) {
        friday = f;
    }

    /**
     * Handles user input and displays the corresponding messages.
     * If the user inputs "bye", exits the application gracefully.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = friday.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFridayDialog(response, fridayImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            exitApplication();
        }
    }

    /**
     * Displays a welcome message from Friday.
     */
    private void displayWelcomeMessage() {
        String welcomeMessage = "Hello Master! I'm Friday. How can I assist you today?";
        dialogContainer.getChildren().add(DialogBox.getFridayDialog(welcomeMessage, fridayImage));
    }

    /**
     * Exits the application gracefully.
     * Waits for 2 seconds before closing the application window.
     */
    private void exitApplication() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close(); // This closes the stage
        });
        pause.play(); // Start the pause
    }
}
