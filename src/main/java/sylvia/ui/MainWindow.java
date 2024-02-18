package sylvia.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sylvia.Sylvia;

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

    private Sylvia sylvia;

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

    public void setSylvia(Sylvia d) {
        sylvia = d;
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
        Response response = sylvia.runCommand(input);
        showResponse(response);
        userInput.clear();
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
