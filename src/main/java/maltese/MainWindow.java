package maltese;

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

    private Maltese maltese;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/maltese1.png"));
    private Image maltese2 = new Image(this.getClass().getResourceAsStream("/images/maltese2.png"));
    /**
     * Initializes the controller after its root element has been completely processed.
     * This method is called automatically by JavaFX after the root element has been
     * processed, and it's used to perform initialization tasks such as binding properties
     * or setting initial values.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String message = "Hello I'm NoisyChatter! What can I do for you?";
        Image image = new Image(getClass().getResourceAsStream("/images/maltese2.png"));
        DialogBox dialogBox = DialogBox.getmalteseDialog(message, image);
        dialogContainer.getChildren().add(dialogBox);
    }


    public void setmaltese(Maltese d) {
        maltese = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing maltese's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = maltese.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getmalteseDialog(response, maltese2)
        );
        userInput.clear();
    }
}
