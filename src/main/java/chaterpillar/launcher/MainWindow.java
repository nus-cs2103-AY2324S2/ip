package chaterpillar.launcher;

import chaterpillar.Chaterpillar;
import chaterpillar.exceptions.ChaterpillarException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

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
    private Chaterpillar chaterpillar;
    private static final String CHATERPILLAR_IMAGE = "/images/chaterpillar.png";
    private static final String USER_IMAGE = "/images/user.png";
    private final Image chaterpillarImage = new Image(this.getClass().getResourceAsStream(CHATERPILLAR_IMAGE));
    private final Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE));

    /**
     * Initialises the main window, binds the scroll pane to be dynamic
     * and adds the first dialog box with the greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getInitialiseChaterpillarDialog(Chaterpillar.greet(), chaterpillarImage)
        );
    }

    /**
     * Sets the chaterpillar object.
     *
     * @param chaterpillar the chatbot.
     */
    public void setChaterpillar(Chaterpillar chaterpillar) {
        this.chaterpillar = chaterpillar;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Chaterpillar's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            Pair<String, Boolean> pair = chaterpillar.getResponse(input);
            String response = pair.getKey();
            boolean hasExited = pair.getValue();
            assert !response.isBlank() : "Response should not be blank";

            if (!hasExited) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getChaterpillarDialog(response, chaterpillarImage));
            }
        } catch (ChaterpillarException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getExceptionDialog(e.getMessage(), chaterpillarImage));
        }
        userInput.clear();
    }
}


