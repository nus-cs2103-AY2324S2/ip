package pyrite;

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

    private Pyrite pyrite;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image pyriteImage = new Image(this.getClass().getResourceAsStream("/images/DaPyrite.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPyrite(Pyrite d) {
        pyrite = d;
        String greeting = "Hello! I'm " + pyrite.getName() + ", your task-tracking rock.";
        dialogContainer.getChildren().addAll(
                DialogBox.getPyriteDialog(greeting, pyriteImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        DialogBox responseDialog;
        try {
            responseDialog = DialogBox.getPyriteDialog(pyrite.getResponse(input), pyriteImage);
        } catch (PyriteException e) {
            responseDialog = DialogBox.getPyriteErrorDialog(e.toString(), pyriteImage);
        }
        dialogContainer.getChildren().addAll(responseDialog);
        userInput.clear();
    }
}
