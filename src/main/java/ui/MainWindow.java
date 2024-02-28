package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Messages;

/**
 * This is a controller for MainWindow. Provides the layout for the other controls.
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

    private BobBot bobBot;
    private Image userImage = new Image(Main.class.getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(Main.class.getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the MainWindow for the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.showWelcomeMessage();
    }

    public void setBot(BobBot bobBotObject) {
        this.bobBot = bobBotObject;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates one dialog box, to welcome the user.
     */
    @FXML
    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Messages.MESSAGE_START_BOT, dukeImage)
        );
        userInput.clear();
    }
}
