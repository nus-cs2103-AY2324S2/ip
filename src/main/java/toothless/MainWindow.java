package toothless;

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

    private Toothless toothless;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image toothlessImage = new Image(this.getClass().getResourceAsStream("/images/DaToothless.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setToothless(Toothless t) {
        toothless = t;
    }

    public void startUp(){
        dialogContainer.getChildren().addAll(
                DialogBox.getToothlessDialog(toothless.load(), toothlessImage),
                DialogBox.getToothlessDialog(toothless.greet(), toothlessImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Toothless' reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = toothless.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getToothlessDialog(response, toothlessImage)
            );
        } catch (ToothlessException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getExceptionDialog(e.getMessage(), toothlessImage)
            );
        } finally {
            userInput.clear();
        }
    }
}
