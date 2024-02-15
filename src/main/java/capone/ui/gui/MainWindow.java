package capone.ui.gui;

import capone.Capone;
import capone.exceptions.TaskListCorruptedException;
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
    /** The ScrollPane that will contain the dialogs. */
    @FXML
    private ScrollPane scrollPane;

    /** The container for dialog boxes (images and labels). */
    @FXML
    private VBox dialogContainer;

    /** The text field for user input. */
    @FXML
    private TextField userInput;

    /** The button to send user input. */
    @FXML
    private Button sendButton;

    /** The instance of the Capone application. */
    private Capone capone;

    /** The image representing the user in dialog boxes. */
    private Image userImage = new Image(this.getClass().getResourceAsStream(DialogBox.getUserImgPath()));

    /** The image representing Capone in dialog boxes. */
    private Image caponeImage = new Image(this.getClass().getResourceAsStream(DialogBox.getCaponeImgPath()));

    /**
     * Initializes the controller. Binds the scroll
     * pane to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Sets the instance of the Capone application.
     *
     * @param capone The Capone application instance to be set.
     */
    public void setCapone(Capone capone) {
        this.capone = capone;
    }

    /**
     * Runs the instance of the Capone application.
     *
     * @param capone The Capone application instance to be run.
     * @throws TaskListCorruptedException If the task list file is corrupted.
     */
    public void runCapone(Capone capone) {
        try {
            this.dialogContainer.getChildren().addAll(
                DialogBox.getCaponeDialog(this.capone.getUi().printWelcomeMsg(), this.caponeImage)
            );
            capone.initCapone();
        } catch (TaskListCorruptedException e) {
            this.dialogContainer.getChildren().addAll(
                    DialogBox.getCaponeDialog(e.getMessage(), this.caponeImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.capone.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getCaponeDialog(response, this.caponeImage)
        );
        this.userInput.clear();
    }
}
