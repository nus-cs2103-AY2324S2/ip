package gui;

import commands.ExitUserCommand;
import commands.UserCommand;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import roebot.RoeBot;

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
    private Stage stage;
    private RoeBot roeBot;
    private final Image ROEBOT_IMAGE = new Image(this.getClass().getResourceAsStream("/images/roebot.png"));
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    /**
     * Initializes dialogContainer and scrollPane
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getRoeBotDialog("\tWelcome! What do you want to do today?", ROEBOT_IMAGE)
        );
    }

    public void setRoeBot(RoeBot d, Stage stage) {
        this.roeBot = d;
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        UserCommand userCommand = null;
        try {
            userCommand = this.roeBot.getResult(input);
            if (userCommand instanceof ExitUserCommand) {
                handleExit();
                return;
            } else {
                String response = userCommand.getMessageToUser();
                userInput.clear();
                if (userCommand == null) {
                    dialogContainer.getChildren().add(
                            DialogBox.getUserDialog(input, USER_IMAGE)
                    );
                    handleExit();
                    return;
                }
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, USER_IMAGE),
                        DialogBox.getRoeBotDialog(response, ROEBOT_IMAGE)
                );
            }
        } catch (Exception e) {
            DialogBox.getRoeBotDialog(e.getMessage(), ROEBOT_IMAGE);
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        dialogContainer.getChildren().add(
                DialogBox.getRoeBotDialog("\tGoodbye for now :)", ROEBOT_IMAGE)
        );
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> this.stage.close());
        delay.play();
    }
}
