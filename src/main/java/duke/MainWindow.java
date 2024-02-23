package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Ursa duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/kekw.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ursa.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Ensure the dialog container fills the width of the scroll pane
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
        dialogContainer.setFillWidth(true);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.hello, dukeImage));
    }

    public void setDuke(Ursa d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.ui.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
        if (duke.ui.isExitCommand(input)) {
            ((Stage) userInput.getScene().getWindow()).close();
        }
    }

    @FXML
    protected void handleFileError(String response) {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(response, dukeImage));
    }
}
