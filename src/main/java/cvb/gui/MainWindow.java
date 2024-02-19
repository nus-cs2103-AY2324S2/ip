package cvb.gui;

import cvb.ConvoBot;
import cvb.exceptions.ExitException;
import javafx.application.Platform;
import javafx.fxml.FXML;
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
    private void initialize() {
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
        addResponse(convo.getWelcome());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing ConvoBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        userInput.requestFocus(); // re-focus TextField
        String input = userInput.getText();
        userInput.clear();
        addInput(input);

        String response = "";
        try {
            response = convo.getResponse(input);
        } catch (ExitException e) {
            Platform.exit();
            return;
        }
        assert !response.isEmpty();
        addResponse(response);
    }

    private void addInput(String input) {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
    }

    private void addResponse(String response) {
        dialogContainer.getChildren().addAll(DialogBox.getConvoBotDialog(response, convoImage));
    }
}
