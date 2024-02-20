package chingu;

import chingu.command.ExitCommand;
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

    private Chingu chingu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/YOU.png"));
    private Image chinguImage = new Image(this.getClass().getResourceAsStream("/images/CHINGU.png"));

    private static final String GREET = "Hello! I'm your ChinGu\n" +
            "What can I do for you?";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(GREET, chinguImage));
    }

    public void setDoummi(Chingu d) {
        chingu = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chingu.getResponse(input);
        if (response.equals(Ui.BYE)) {
            System.exit(0);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, chinguImage)
        );
        userInput.clear();
    }
}

