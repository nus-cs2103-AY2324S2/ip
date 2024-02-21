package BadApple.main;

import BadApple.uiElements.DialogBox;
import BadApple.uiElements.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private BadPingGuo badApple;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Sunny.jpeg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/MARI.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Upon setting up the Stage and starting the app, run the commands
     * below.
     * @param d a BadPingGuo instance.
     */
    public void setBadApple(BadPingGuo d) {
        badApple = d;
        showWelcomeMessage();
        showInitialList();
    }

    public void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                Ui.showWelcome(), dukeImage
        ));
    }

    public void showInitialList() {
        dialogContainer.getChildren().addAll(badApple.getInitialList());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = badApple.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog((input), (userImage)),
                DialogBox.getDukeDialog((response), (dukeImage))
        );

        if (input.equals("bye")) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException i) {
                // There's no need to do anything, we're already going to exit
                System.exit(0);
            } finally {
                System.exit(0);
            }
        }

        userInput.clear();
    }
}

