package jelly;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * MainWindow class
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

    private Jelly jelly;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Jelly_user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Jelly_icon.png"));

    /**
     * Initializes Window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getJellyDialog("Hello! I'm Jelly\nWhat can I do for you?", dukeImage)
        );
    }

    public void setJelly(Jelly j) {
        jelly = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = jelly.getResponse(input);

        if (response.equals("bye")) {

            jelly.saveStorage();
            response = jelly.getFarewell();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJellyDialog(response, dukeImage)
        );

        userInput.clear();
    }
}
