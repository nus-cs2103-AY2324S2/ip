package gui;

import balkan.BalkanBot;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private BalkanBot balkanBot;

    private GuiUi guiUi;

    private boolean isExit = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User Jordan.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke Zizek.jpeg"));

    @FXML
    public void initialize() {
        guiUi = new GuiUi();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(guiUi.printWelcome(), dukeImage)
        );
    }

    public void setBalkanBot(BalkanBot b) {
        balkanBot = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Balkan Bot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = balkanBot.getResponse(input);
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}
