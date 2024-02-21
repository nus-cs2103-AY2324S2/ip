package jojo.gui;

import exceptions.JojoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jojo.Jojo;

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

    private Jojo jojo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image jojoImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJojo(Jojo j) {
        jojo = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            if (input.strip().equals("bye")) {
                Platform.exit();
            }
            String response = jojo.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJojoDialog(response, jojoImage)
            );
            jojo.saveTasks();
        } catch (JojoException e) {
            String response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJojoDialog(response, jojoImage)
            );
        } finally {
            userInput.clear();
        }
    }

    /**
     * Returns the starting message.
     */
    public void showStartingMsg() throws JojoException {
        String response = jojo.getStartingMsg();
        dialogContainer.getChildren().addAll(
                DialogBox.getJojoDialog(response, jojoImage)
        );
    }
}
