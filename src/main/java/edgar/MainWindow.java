package edgar;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import commands.UserCommand;

/**
 * The MainWindow class serves as the controller for the main window of the chatbot application.
 * It provides the layout for the various controls and handles user input.
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

    private EdgarChatBot edgar;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Brawl_Leon.png"));
    private Image edgarImage = new Image(this.getClass().getResourceAsStream("/images/Edgar_Portrait.png"));

    /**
     * Initializes the main window controller.
     * Sets up the initial dialog in the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getEdgarDialog("\tWelcome! What do you want to do today?", edgarImage)
        );
    }

    /**
     * Sets the EdgarChatBot instance for the controller.
     *
     * @param edgar The EdgarChatBot instance to be set.
     */
    public void setEdgarChatBot(EdgarChatBot edgar) {
        this.edgar = edgar;
    }

    /**
     * Handles the user input by processing it and displaying the corresponding responses.
     * Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText() + " \t";
            if (input.trim().equalsIgnoreCase("bye")) {
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    Platform.exit();
                    System.exit(0);
                });
                pause.play();
            }
            UserCommand userCommand = this.edgar.commandResult(input);
            String response = userCommand.getMessageToUser();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getEdgarDialog(response, edgarImage)
            );
            userInput.clear();
        } catch (Exception e) {
            DialogBox.getEdgarDialog(e.getMessage(), edgarImage);
        }
    }
}
