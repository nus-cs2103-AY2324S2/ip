package joy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Joy joy;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Pikachu.png")));
    private Image joyImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/joy.png")));

    /**
     * Initializes the mainwindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setDuke(Joy j) {
        joy = j;
        String response = ("Welcome to the Task Management Center!"
            + "\n I'm Nurse Joy, ready to help you organize your tasks efficiently, just like I care for Pokemon."
            + "\n Todo task, use the format: todo [description]"
            + "\n Deadline, use the format: deadline [description] /by [yyyy-mm-dd]"
            + "\n Event, use the format: event [description] /from [time] /to [time]");
        dialogContainer.getChildren().addAll(

                DialogBox.getDukeDialog(response, joyImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = joy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, joyImage)
        );
        userInput.clear();
    }
}
