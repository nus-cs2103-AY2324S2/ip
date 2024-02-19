package gui;

import java.io.IOException;

import chronos.Chronos;
import exception.ChronosException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tool.Ui;

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

    private Chronos chronos;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pochacco.jpg"));
    private Image chronosImage = new Image(this.getClass().getResourceAsStream("/images/robot.jpg"));

    @FXML
    public void initialize() {
        String text = Ui.greetUser();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setPadding(new Insets(15));
        dialogContainer.setSpacing(15);
        dialogContainer.getChildren().add(
                DialogBox.getChronosDialog(text, chronosImage)
        );
    }

    public void setChronos(Chronos c) {
        chronos = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chronos' reply and then appends them to
     * the dialog container.
     */
    @FXML
    private void handleUserInput() throws IOException, ChronosException {
        String input = userInput.getText();
        String response = chronos.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChronosDialog(response, chronosImage)
        );
        userInput.clear();
    }
}
