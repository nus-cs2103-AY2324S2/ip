package osiris;

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

    private Osiris osiris;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image osirisImage = new Image(this.getClass().getResourceAsStream("/images/osiris.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void initialiseOsiris(Osiris chatBot) {
        osiris = chatBot;
        String status = osiris.startChat();
        outputOsirisDialog(status);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInput = this.userInput.getText();
        String response = osiris.processInput(userInput);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput, userImage),
                DialogBox.getOsirisDialog(response, osirisImage)
        );
        this.userInput.clear();
    }

    private void outputOsirisDialog(String outputDialog) {
        dialogContainer.getChildren().addAll(DialogBox.getOsirisDialog(outputDialog, osirisImage));
    }
}

