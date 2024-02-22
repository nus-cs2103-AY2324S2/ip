package osiris;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the MainWindow.fxml file.
 * Handles user interactions and updates the main chat window accordingly.
 */
public class MainWindow extends AnchorPane {

    /** The ScrollPane component used to contain the dialog elements and enable scrolling. */
    @FXML
    private ScrollPane scrollPane;

    /** The VBox component serving as a container for the dialog elements. */
    @FXML
    private VBox dialogContainer;

    /** The TextField where the user inputs messages. */
    @FXML
    private TextField userInput;

    /** An instance of the Osiris class, responsible for processing messages and interactions. */
    private Osiris osiris;

    /** Image of Osiris. */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    /** Image of User. */
    private final Image osirisImage = new Image(this.getClass().getResourceAsStream("/images/osiris.png"));

    /**
     * Initializes the controller after its root element has been completely processed.
     * It binds the vertical value property of the scroll pane to the height property of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initializes the MainWindow with the provided Osiris instance.
     * This method is typically called to start a chat session.
     *
     * @param chatBot The Osiris instance to be associated with this MainWindow.
     */
    public void initialiseOsiris(Osiris chatBot) {
        osiris = chatBot;
        String status = osiris.startChat();
        outputOsirisDialog(status);
    }

    /**
     * Handles user input when the send button is clicked.
     * Processes the user input through the Osiris instance and updates the dialog container accordingly.
     * Clears the user input field after processing.
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

    /**
     * Outputs a dialog from Osiris to the dialog container.
     *
     * @param outputDialog The dialog string to be displayed.
     */
    private void outputOsirisDialog(String outputDialog) {
        dialogContainer.getChildren().addAll(DialogBox.getOsirisDialog(outputDialog, osirisImage));
    }
}
