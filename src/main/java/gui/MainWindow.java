package gui;

import andelu.Andelu;
import andelu.AndeluException;
import andelu.Parser;
import command.Command;
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

    /** Scroll Pane for the GUI. */
    @FXML
    private ScrollPane scrollPane;

    /** DialogContainer to store all the DialogBox. */
    @FXML
    private VBox dialogContainer;

    /** TextField to get the user input.*/
    @FXML
    private TextField userInput;

    /** A button for the user to submit his enquires. */
    @FXML
    private Button sendButton;

    /** An Andelu Object. */
    private Andelu andelu;

    /** The image of the user. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.jpg"));

    /** The image of Andelu bot. */
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/BotImage.png"));

    /**
     * Initialises some properties for GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .addAll(DialogBox.getAndeluDialog("Hello! I'm AndrewOng2066.\nWhat can I do for you?", botImage));
    }

    /**
     * Create the Andelu Object.
     *
     * @param filename the filename to store the data.
     */
    public void setAndelu(String filename) {
        andelu = new Andelu(filename);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Andelu's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command c = Parser.parse(input);
        String response;
        try {
            response = c.executeCommand(andelu.getTasks(), andelu.getUi(), andelu.getStorage());
        } catch (AndeluException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAndeluDialog(response, botImage)
        );
        userInput.clear();
    }
}
