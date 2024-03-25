package bot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Bot duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayWelcomeMessage();
    }

    public void setDuke(Bot d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Handler handler = new Handler(duke.taskList);
        String input = userInput.getText();

        if (input.equals("bye")) {
            System.exit(0);
        }

        try {
            String response = handler.handle(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage));
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }

        userInput.clear();
    }

    public void displayWelcomeMessage() {
        Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));
        DialogBox welcomeDialogBox = DialogBox.getDukeDialog("Welcome to the application!\nI am Bot! I can help you manage your tasks!", dukeImage);
        dialogContainer.getChildren().addAll(welcomeDialogBox);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
}
