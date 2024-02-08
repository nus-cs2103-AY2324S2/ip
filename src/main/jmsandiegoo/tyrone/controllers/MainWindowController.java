package jmsandiegoo.tyrone.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    private Tyrone tyrone;
    private Image tyroneImage = new Image(this.getClass().getResourceAsStream("/images/Tyrone.jpeg"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.chatLog.heightProperty());
    }

    public void setTyrone(Tyrone tyrone) {
        this.tyrone = tyrone;
    }

    /**
     * Handles the event from textfield and send button from user input.
     * Creates two chat dialog one for the user's input and the bot response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInputTextField.getText();
        chatLog.getChildren().addAll(
                ChatDialogController.makeUserChatDialog(input),
                ChatDialogController.makeBotChatDialog(input, tyroneImage)
        );
    }
}
