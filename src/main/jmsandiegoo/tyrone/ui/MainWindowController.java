package jmsandiegoo.tyrone.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jmsandiegoo.tyrone.Tyrone;

/**
 * Controller class for MainWindow fxml file. It handles the main layout of the controls.
 */
public class MainWindowController extends BorderPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatLog;
    @FXML
    private TextField userInputTextField;
    @FXML
    private Button userInputSendButton;
    private Tyrone tyrone;
    private final Image TYRONE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/TyroneAvatar.jpeg"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.chatLog.heightProperty());
    }

    public void setTyrone(Tyrone tyrone) {
        this.tyrone = tyrone;
    }

    /**
     * Handles the startup process of initializing the chat application.
     */
    public void start() {
        if (this.tyrone == null) return;
        this.appendBotChatDialog(this.tyrone.greetUser());
        this.tyrone.start();
    }


    private void appendUserChatDialog(String message) {
        this.chatLog.getChildren().add(ChatDialogController.makeUserChatDialog(message));
    }

    private void appendBotChatDialog(String message) {
        this.chatLog.getChildren().add(ChatDialogController.makeBotChatDialog(message, TYRONE_IMAGE));
    }

    /**
     * Handles the event from text field and send button from user input.
     * Creates two chat dialog one for the user's input and the bot response.
     * Exit application when tyrone's checkIfExit returns true.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInputTextField.getText();
        this.appendUserChatDialog(input);
        String response = this.tyrone.processUserCommand(input);
        this.appendBotChatDialog(response);
        this.userInputTextField.clear();

        if (this.tyrone.checkIfExit()) {
            this.userInputTextField.setEditable(false);
            this.userInputSendButton.setDisable(true);
            PauseTransition appPause = new PauseTransition(Duration.seconds(2));
            appPause.setOnFinished(event ->
                    Platform.exit());
            appPause.play();
        }
    }
}
