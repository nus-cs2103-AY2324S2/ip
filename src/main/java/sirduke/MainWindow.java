package sirduke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Scanner;

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

    private SirDuke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/LeUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/SirDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSirDuke(SirDuke d) {
        duke = d;
    }

    String getResponse(String input) {
        return duke.runForGUI(input);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        // Right now we have SirDuke returning all his responses to the terminal. Now
        // we need to capture the output and paste it into the DialogBox
        // look at Socket and getInputStream and getOutputStream
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();
    }
}
