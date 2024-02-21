package zoe;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for zoe.MainWindow. Provides the layout for the other controls.
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

    private Zoe zoe;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image zoeImage = new Image(this.getClass().getResourceAsStream("/images/DaZoe.png"));

    @FXML
    public void initialize(Zoe zoe, Ui ui) {
        this.zoe = zoe;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getZoeDialog(ui.saysHi(), zoeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zoe's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Thrown exception is to allow for pausing the bot for 2 seconds so that it can say bye to the user
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zoe.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZoeDialog(response, zoeImage)
        );

        userInput.clear();

        if (response.equals(new Ui().saysBye())) {
            pauseAndStop();
        }
    }

    /**
     * Pauses the app for 1 second then closes so that it can display the bye message
     */
    private void pauseAndStop() {
        Timer timer = new Timer();
        userInput.setDisable(true);
        sendButton.setDisable(true);
        timer.schedule(new stopApp(), 500);
    }

    /**
     * Helps the pauseAndStop function by providing the stopping functionality
     */
    private class stopApp extends TimerTask {
        @Override
        public void run() {
            Platform.exit();
            System.exit(0);
        }
    }
}
