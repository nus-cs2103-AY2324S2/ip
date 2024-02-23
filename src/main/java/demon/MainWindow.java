package demon;

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
    public Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Demon demon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaAngel.png"));
    private Image demonImage = new Image(this.getClass().getResourceAsStream("/images/DaDemon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sayHi();
    }

    public void setDemon(Demon demon) {
        this.demon = demon;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = demon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDemonDialog(response, demonImage)
        );
        if (!demon.isRun) {
            delayedExit();
        }
        userInput.clear();
    }

    @FXML
    private void delayedExit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> javafx.application.Platform.exit());
        delay.play();
    }

    @FXML
    private void sayHi() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDemonDialog("Hello Master, I'm Demon \nWhat can I do for you today?",
                        demonImage)
        );
    }
}
