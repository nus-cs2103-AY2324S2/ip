package sylvia.ui;

import java.util.function.Function;
import java.util.function.Supplier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main window of the bot.
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

    private Function<String, Response> inputHandler;

    private Supplier<String> prevChatSupplier = () -> "up";
    private Supplier<String> nextChatSupplier = () -> "down";

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/image/userimg.jpg"));
    private final Image sylviaImage = new Image(this.getClass().getResourceAsStream("/image/sylviaimg.jpg"));
    private final Image sendIcon = new Image(this.getClass().getResourceAsStream("/icon/send.png"));

    /**
     * Initializes the main window.
     */
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.getStylesheets().add(getClass().getResource("/css/MainWindow.css").toExternalForm());
        sendButton.setGraphic(new ImageView(sendIcon));
    }

    /**
     * Sets the input handler for the main window.
     *
     * @param inputHandler The input handler for the main window.
     */
    public void setInputHandler(Function<String, Response> inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * Sets the chat history suppliers for the main window.
     *
     * @param prevChatSupplier The supplier for the previous chat message.
     * @param nextChatSupplier The supplier for the next chat message.
     */
    public void setChatHistorySuppliers(Supplier<String> prevChatSupplier, Supplier<String> nextChatSupplier) {
        this.prevChatSupplier = prevChatSupplier;
        this.nextChatSupplier = nextChatSupplier;
    }

    private String getUserInput() {
        String input = userInput.getText();
        // don't show empty input
        if (input.isEmpty()) {
            return "";
        }
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        return input;
    }

    @FXML
    private void handleUserInput() {
        String input = getUserInput();
        // don't send empty input
        if (input.isEmpty()) {
            return;
        }
        Response response = inputHandler.apply(input);
        showResponse(response);
        userInput.clear();
    }

    private void setNullableUserInput(String input) {
        if (input == null) {
            return;
        }
        userInput.setText(input);
        userInput.positionCaret(input.length()); // move caret to end
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            setNullableUserInput(prevChatSupplier.get());
            break;
        case DOWN:
            setNullableUserInput(nextChatSupplier.get());
            break;
        default:
            break;
        }
    }

    /**
     * Shows the response from the bot.
     *
     * @param response The response from the bot.
     */
    public void showResponse(Response response) {
        dialogContainer.getChildren().addAll(DialogBox.getSylviaDialog(response.toString(), sylviaImage));
    }
}
