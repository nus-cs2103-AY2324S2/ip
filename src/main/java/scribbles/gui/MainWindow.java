package scribbles.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import scribbles.Scribbles;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private Scribbles scribbles;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image scribblesImage = new Image(this.getClass().getResourceAsStream("/images/scribbles.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setScribbles(Scribbles s) {
        scribbles = s;
    }

    @FXML
    public void displayGreetingMessage(String greetingMessage) {
        dialogContainer.getChildren().add(
                DialogBoxScribbles.getScribblesDialog(greetingMessage, scribblesImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = scribbles.getResponse(input);

        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBoxYou.getUserDialog(input, userImage),
                    DialogBoxScribbles.getScribblesDialog(response, scribblesImage)
            );
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                Platform.exit();
                System.exit(0);
            }, 2, TimeUnit.SECONDS);
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBoxYou.getUserDialog(input, userImage),
                    DialogBoxScribbles.getScribblesDialog(response, scribblesImage)
            );
        }
        userInput.clear();
    }
}
