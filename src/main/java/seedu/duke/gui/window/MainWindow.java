package seedu.duke.gui.window;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import seedu.duke.Duke;
import seedu.duke.gui.component.DialogBox;


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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * This method will display the initial message to the dialog box
     */
    public void displayInitMessage() {
        assert duke != null;
        if (duke.getLoadingExceptionMessage() != null) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.getLoadingExceptionMessage(), dukeImage));
        }
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.getInitMessage(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (duke.isExit()) {
            return;
        }
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (duke.isExit()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("WindBro left the chat room", dukeImage)
            );
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(event -> {
                Platform.exit();
            });
            pauseTransition.play();
        }
    }
}
