
package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane implements GuiObserver {
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

    @Override
    public void showMessage(String message) {
        Platform.runLater(() -> {
            dialogContainer.getChildren().add(new Text(message));
        });
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Ui ui = duke.getUi();

        String input = userInput.getText();

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        duke.processUserInput(input); // This should trigger a response from Duke
        // Assuming processUserInput updates `lastMessage` in Ui
        String dukeResponse = duke.getUi().getLastMessage();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeResponse, dukeImage));
        userInput.clear();
    }
}
