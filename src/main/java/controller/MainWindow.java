package controller;

import cal.Cal;
import exceptions.CalException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * represents the main view for the application consisting of chat window,
 * text field and send button.
 */
public class MainWindow {
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;
    @FXML
    private TextField userInput;

    private Cal cal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image calImage = new Image(this.getClass().getResourceAsStream("/images/cal.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCal(Cal cal) {
        this.cal = cal;
    }

    @FXML
    private void handleUserInput() throws CalException {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        try {
            String response = cal.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage, "#FCEBA6"),
                    DialogBox.getCalDialog(response, calImage, "#DDC9F5")
            );
        } catch (CalException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getCalDialog("Error: " + e.getMessage(), calImage, "#DDC9F5")
            );
        }

        userInput.clear();
    }

    @FXML
    private void handleKeyPress(KeyEvent event) throws CalException {
        if (event.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }
}
