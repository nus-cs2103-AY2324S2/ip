package aegis;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * The code for this class was taken from the tutorial for implementing JavaFX @ https://se-education.org/guides/tutorials/javaFx.html
 *
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
    private Aegis aegis;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image aegisImage = new Image(this.getClass().getResourceAsStream("/images/aegis.png"));

    /**
     * Initializes the chat window GUI and displays a chat box containing the Aegis assistant greeting.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getAegisDialog("Greetings! I am Aegis. How may I assist you?", aegisImage)
        );
    }

    /**
     * Sets the Aegis object to be used in this instance of MainWindow.
     *
     * @param a Aegis object.
     */
    public void setAegis(Aegis a) {
        aegis = a;
    }

    /**
     * Creates two or more dialog boxes, one echoing user input and the others containing Aegis's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isBlank()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(" ", userImage),
                    DialogBox.getAegisDialog("Did you say something?", aegisImage)
            );
            return;
        }

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        ArrayList<String> response = aegis.getResponse(input);
        if (response.get(0).equals("bye")) {
            userInput.clear();
            dialogContainer.getChildren().add(
                    DialogBox.getAegisDialog(response.get(1), aegisImage)
            );
            userInput.setDisable(true);
            sendButton.setDisable(true);
            return;
        } else {
            for (String s : response) {
                dialogContainer.getChildren().add(
                        DialogBox.getAegisDialog(s, aegisImage)
                );
            }
        }

        userInput.clear();
    }
}
