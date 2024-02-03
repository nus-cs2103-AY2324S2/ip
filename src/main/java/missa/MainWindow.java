package missa;

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
 * Controller for MainWindow.
 * Provides the layout for the other controls.
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

    private Image user = new Image(
            this.getClass().getResourceAsStream("/images/customer.png"));
    private Image host = new Image(
            this.getClass().getResourceAsStream("/images/host.png"));
    private MissA missA;

    /**
     * Initialises scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialises MainWindow.
     *
     * @param missA A chatbot used to communicate with users.
     */
    public void setMissA(MissA missA) {
        this.missA = missA;
        dialogContainer.getChildren().add(
                DialogBox.getMissADialog(missA.getWelcomeMsg(), host));
    }

    /**
     * Creates two dialog boxes, one echoing user input,
     * the other containing Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        boolean isBye = missA.checkBye(userText);
        String dukeText = missA.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getMissADialog(dukeText, host)
        );
        userInput.clear();
        if (isBye) {
            new Timer().schedule(
                    new TimerTask() {
                        public void run() {
                            Platform.exit();
                            System.exit(0);
                        }
                    }, 1500);
        }
    }
}
