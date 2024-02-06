package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/minnie.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/spinminnie.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {//
        String userInputText = userInput.getText();
        String dukeResponseText = duke.getResponse(userInputText);

        // Pass the text and the image to getUserDialog and getDukeDialog
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInputText, userImage),
                DialogBox.getDukeDialog(dukeResponseText, dukeImage)
        );
        userInput.clear();
    }
}
