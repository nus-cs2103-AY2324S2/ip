package jimmy.gui;

import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.Timer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jimmy.Jimmy;

/**
 * O
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.png")));
    private final Image jimmyImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/jimmy.png")));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Jimmy jimmy;

    public void setJimmy(Jimmy newBot) {
        jimmy = newBot;
        dialogContainer.getChildren().addAll(
                DialogBox.getJimmyDialog(jimmy.greet(), jimmyImage)
        );
    }

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Jimmy's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jimmy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJimmyDialog(response, jimmyImage)
        );
        userInput.clear();

        if (input.trim().equals("bye")) {
            int delay = 3000; // delay of Jimmy closing in milliseconds
            ActionListener taskPerformer = event -> Platform.exit();
            Timer timer = new Timer(delay, taskPerformer);
            timer.setRepeats(false);
            timer.start();
        }
    }
}

