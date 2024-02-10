package edgar;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import commands.UserCommand;
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
    @FXML
    private Button sendButton;

    private EdgarChatBot edgar;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image edgarImage = new Image(this.getClass().getResourceAsStream("/images/timmy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("\tWelcome! What do you want to do today?", edgarImage)
        );
    }

    public void setEdgarChatBot(EdgarChatBot edgar) {
        this.edgar = edgar;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zack's reply and then appends
     * them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        UserCommand userCommand = null;
        try {
            userCommand = this.edgar.getResult(input);
            if (userCommand == null) {
                handleExit();
                return;
            } else {
                String response = userCommand.getMessageToUser();
                userInput.clear();
                if (userCommand == null) {
                    dialogContainer.getChildren().add(
                            DialogBox.getUserDialog(input, edgarImage)
                    );
                    handleExit();
                    return;
                }
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, edgarImage),
                        DialogBox.getDukeDialog(response, edgarImage)
                );
            }
        } catch (Exception e) {
            DialogBox.getDukeDialog(e.getMessage(), edgarImage);
        }
    }

    @FXML
    private void handleExit() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("\tGoodbye for now :)", edgarImage)
        );
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.play();
    }
}