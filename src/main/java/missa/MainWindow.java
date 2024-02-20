package missa;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
            this.getClass().getResourceAsStream("/images/dog.png"));
    private Image host = new Image(
            this.getClass().getResourceAsStream("/images/missa.png"));
    private MissA missA;

    /**
     * Initialises scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Sets background image.
        // Solution below inspired by
        // https://stackoverflow.com/questions/9738146/javafx-how-to-set-scene-background-image
        Image img = new Image("/images/arab.jpeg", 400, 600, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                img,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        dialogContainer.setBackground(new Background(backgroundImage));
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
        try {
            String userText = userInput.getText();
            boolean isBye = missA.checkBye(userText);
            String missaText = missA.getResponse(userText);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getMissADialog(missaText, host)
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
        } catch (IOException e) {
            String missaText = "Sorry, I am unable to update data file.\n"
                    + "Bye. Hope you have a nice day!";
            String userText = "bye";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getMissADialog(missaText, host)
            );
            userInput.clear();
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
