import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    private ChatPal chatPal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/corgi.jpg"));
    private Image chatPalImage = new Image(this.getClass().getResourceAsStream("/images/golden_retriever.jpg"));

    /**
     * Initializes Dialog Container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    public void setChatPal(ChatPal d) {
        chatPal = d;
    }

    /**
     * Displays greeting message.
     */
    private void greet() {
        String text = "Hi Hi!! Your personal ChatPal here! How can I help you?\n";
        DialogBox greetDialog = DialogBox.getChatPalDialog(text, chatPalImage);
        dialogContainer.getChildren().add(greetDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChatPal's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        assert userText != null;

        String chatPalText = chatPal.getResponseMessage(userText);

        DialogBox userDialog = DialogBox.getUserDialog(userText, userImage);
        DialogBox chatPalDialog = DialogBox.getChatPalDialog(chatPalText, chatPalImage);

        updateDialogContainer(userDialog, chatPalDialog);
        handleChatPalError(chatPalText, chatPalDialog);

        userInput.clear();
        handleExit(userText);
    }

    /**
     * Updates the dialog container with the user and ChatPal dialog boxes.
     *
     * @param userDialog The dialog box containing the user's message.
     * @param chatPalDialog The dialog box containing ChatPal's response.
     */
    private void updateDialogContainer(DialogBox userDialog, DialogBox chatPalDialog) {
        dialogContainer.getChildren().addAll(
                userDialog,
                chatPalDialog
        );
        dialogContainer.setPadding(new Insets(10));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Handles ChatPal error messages by setting the background color of the ChatPal dialog box to indicate an error.
     *
     * @param chatPalText The text of ChatPal's response.
     * @param chatPalDialog The dialog box containing ChatPal's response.
     */
    private void handleChatPalError(String chatPalText, DialogBox chatPalDialog) {
        if (chatPalText.contains("Error:")) {
            chatPalDialog.setBackground(new Background(new BackgroundFill(Color.web("#ffbfbf"), null, null)));
        }
    }
    /**
     * Handles the exit command by terminating the application after a delay.
     *
     * @param userText The text entered by the user.
     */
    private void handleExit(String userText) {
        if (userText.equals("bye")) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> Platform.exit()));
            timeline.play();
        }
    }
}
