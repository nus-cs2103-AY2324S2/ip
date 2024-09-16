package reacher.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import reacher.Reacher;

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

    private Reacher reacher;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        showIntro();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setReacher(Reacher r) {
        reacher = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = reacher.getResponse(input);

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox reacherDialog = DialogBox.getReacherDialog(response, dukeImage);

        dialogContainer.getChildren().addAll(userDialog, reacherDialog);

        userInput.clear();

        if (response.equals("Bye!")) {
            Platform.exit();
        }
    }

    /**
     * Adds welcome message to DialogBox.
     */
    private void showIntro() {
        String message = "Welcome!\n" +
                "My name is Reacher\n" +
                "I can help record tasks!" +
                "Commands are:\n" +
                "find, add, edit, list, edit, bye\n" +
                "If you need help, just type help in the text box";
        DialogBox reacherDialog = DialogBox.getReacherDialog(message, dukeImage);
        dialogContainer.getChildren().addAll(reacherDialog);
    }

}