package felix.gui;

import felix.Felix;
import felix.utils.Ui;

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

    private Felix felix;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image felixImage = new Image(this.getClass().getResourceAsStream("/images/felix.jpg"));

    /**
     * Initialises the app with a display introductory message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String introductionMessage = new Ui().getIntroduction();
        dialogContainer.getChildren().add(
                DialogBox.getFelixDialog(introductionMessage, felixImage)
        );
    }

    public void setFelix(Felix felix) {
        this.felix = felix;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = felix.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFelixDialog(response, felixImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            pause.play();
        }
    }
}