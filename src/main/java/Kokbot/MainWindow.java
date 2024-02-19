package Kokbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.application.Platform;

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

    private Kokbot kokbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Dauser.jpg"));
    private Image kokbotImage = new Image(this.getClass().getResourceAsStream("/images/Daduke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToHeight(false);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    public void setKokbot(Kokbot k) {
        kokbot = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kokbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, kokbotImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            closeMainWindow();
        }
    }

    private void closeMainWindow() {
        Platform.exit();
    }
}