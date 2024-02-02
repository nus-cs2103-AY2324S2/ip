package nollid.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nollid.Nollid;

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

    private Nollid nollid;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserProfilePic.png"));
    private Image nollidImage = new Image(this.getClass().getResourceAsStream("/images/NollidProfilePic.png"));

    /**
     * Initializes the main window, binding the scroll pane's vertical value to the dialog container's height property.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        makeNollidSay("Hello! I'm Nollid.\n" + "What can I do for you?");
    }

    /**
     * Sets the given Nollid instance for interaction.
     */
    public void setNollid(Nollid n) {
        this.nollid = n;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nollid's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nollid.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNollidDialog(response, nollidImage)
        );
        userInput.clear();
    }

    /**
     * Makes Nollid say the specified message by adding a DialogBox to the dialogContainer.
     */
    public void makeNollidSay(String message) {
        dialogContainer.getChildren().add(DialogBox.getNollidDialog(message, nollidImage));
    }
}
