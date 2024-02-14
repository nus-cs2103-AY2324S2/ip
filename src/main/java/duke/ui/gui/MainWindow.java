package duke.ui.gui;

import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.ui.Gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow of the GUI. Provides the layout for the other
 * controls.
 *
 * @author Ryan NgWH
 */
public class MainWindow extends AnchorPane {
    // Elements of the main GUI window
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // Instance of the GUI
    private Gui gui;

    // Images for the chatbot
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    /**
     * Initialize the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Set GUI instance of the main window
     *
     * @param gui GUI instance to set
     */
    public void setGui(Gui gui) {
        this.gui = gui;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws StorageException {
        String userText = this.userInput.getText();
        String dukeText = "";
        try {
            dukeText = gui.getResponse(this.userInput.getText());
        } catch (DukeException e) {
            dukeText = String.format("Sorry! I got the following error:\n %s", e.getMessage());
        } finally {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, this.userImage),
                    DialogBox.getDukeDialog(dukeText, this.dukeImage));

            this.userInput.clear();
        }

    }
}
