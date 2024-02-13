package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jux.Jux;

import java.lang.reflect.InvocationTargetException;

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

    private Jux jux;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private Image juxImage = new Image(this.getClass().getResourceAsStream("/images/dog.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setJux(Jux jux) {
        this.jux = jux;
    }

    /**
     * Creates the initial dialog box by Jux, only contains the welcome message then
     * appends to the dialog container. Located as a child in the fxml file
     */

    public void welcomeMessage(){
        String message = "Previously on Jux...." + "\n";
        message += jux.getStorageList();
        if (jux.isNewStart()) {
            message = "Hello! I'm Jux\n" +
                    "What can I do for you?\n";
        }

        dialogContainer.getChildren().add(DialogBox.getJuxDialog(message, juxImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jux.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJuxDialog(response, juxImage)
        );
        userInput.clear();
    }
}

