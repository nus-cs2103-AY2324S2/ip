package sylvia.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    /**
     * Initializes the main window.
     */
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSylvia(Sylvia d) {
        sylvia = d;
    }

    private String getUserInput() {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userInput.getText(), userImage));
        return userInput.getText();
    }

    @FXML
    private void handleUserInput() {
        String input = getUserInput();
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
