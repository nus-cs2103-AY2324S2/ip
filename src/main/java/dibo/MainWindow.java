package dibo;

import dibo.exception.DiboException;
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
 * Referenced <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">FXML tutorial</a>.
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

    private Dibo dibo;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser2.png"));
    private final Image diboImage = new Image(this.getClass().getResourceAsStream("/images/DaDibo.png"));

    /**
     * Initialize the MainWindow Controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDibo() {
        try {
            this.dibo = new Dibo();
            sayHi();
        } catch (DiboException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDiboDialog(e.getMessage(),
                            diboImage)
            );
            delayedExit();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dibo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDiboDialog(response, diboImage)
        );
        if (dibo.hasEnded()) {
            delayedExit();
        }
        userInput.clear();
    }

    @FXML
    private void sayHi() {
        String helloMessage = "Hello sir! I'm Dibo.\nWhat can I do for you today?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDiboDialog(helloMessage,
                        diboImage)
        );
    }

    @FXML
    private void delayedExit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(6));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}

