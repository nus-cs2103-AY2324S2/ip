package cvb.gui;

import cvb.ConvoBot;
import cvb.exceptions.ExitException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Provides the main layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private final ConvoBot convo = new ConvoBot("./data/tasks.txt");
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image convoImage = new Image(getClass().getResourceAsStream("/images/DaConvo.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        userInput.requestFocus(); // re-focus TextField
        String input = userInput.getText();
        userInput.clear();
        String response = "";
        try {
            response = convo.getResponse(input);
        } catch (ExitException e) {
            Platform.exit();
            return;
        }
        assert !response.isEmpty();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, convoImage)
        );
    }
}
