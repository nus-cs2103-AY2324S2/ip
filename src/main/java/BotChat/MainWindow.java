package BotChat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the MainWindow FXML layout. Provides the layout for the main window
 * and handles user interactions.
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

    private BotChat botChat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userr.png"));
    private Image botchatImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    /**
     * Initializes the controller. Binds the scrollPane to the heightProperty of the dialogContainer,
     * and adds an initial greeting message to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBotChatDialog("Hello! I'm botChat :D\nWhat can I do for you?\n", botchatImage)
        );

        sendButton.setOnAction(event -> handleUserInput());
    }

    /**
     * Sets the associated BotChat instance for communication.
     *
     * @param b The BotChat instance to be associated with this controller.
     */
    public void setBotChat(BotChat b) {
        botChat = b;
    }

    /**
     * Handles user input. Processes the user input, generates a response using the associated BotChat instance,
     * and updates the dialog container with user and botChat dialog boxes.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = botChat.handleInput(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBotChatDialog(response, botchatImage)
            );
            userInput.clear();
        } catch (BotChatException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getBotChatDialog("Error: " + e.getMessage(), botchatImage)
            );
        }
    }
}
