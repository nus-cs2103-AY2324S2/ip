package tes.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tes.Tes;

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

    private Tes tes;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image tesImage = new Image(this.getClass().getResourceAsStream("/images/tes.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays greeting when initializing the chatbot.
     */
    public void displayGreet() {
        String greet = tes.greet();
        dialogContainer.getChildren().add(DialogBox.getTesDialog(greet, tesImage));
    }

    /**
     * Sets up the chatbot.
     * Greets the user too when initializing the chatbot.
     * @param t
     */
    public void setTes(Tes t) {
        tes = t;
        displayGreet();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if ("bye".equals(input.trim().toLowerCase())) {
            String closingLine = tes.exit();
            dialogContainer.getChildren().add(DialogBox.getTesDialog(closingLine, tesImage));

            // Creates a pause transition for 1 second
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                // Closes the application after the pause
                Platform.exit();
                System.exit(0);
            });
            pause.play(); // Starts the pause
        } else {
            // Handles other input as normal
            String response = tes.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getTesDialog(response, tesImage)
            );
        }
        userInput.clear();
    }
}
