package shuheng.frontend;

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
import shuheng.ShuHeng;
import shuheng.Ui;
import shuheng.exceptions.InvalidTaskException;
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

    private boolean isExit = false;

    private ShuHeng shuheng;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/shuheng.png"));

    /**
     * Initialises our javaFX components.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String shuhengText = shuheng.sayHi();
        dialogContainer.getChildren().addAll(
            DialogBox.getShuhengDialog(shuhengText, dukeImage)
        );
    }

    public void setShuheng(ShuHeng d) {
        shuheng = d;
    }

    /**
     * Creates two dialog boxes for user and shuheng replies.
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getShuhengDialog(dukeText, dukeImage)
        );
        try {
            isExit =
              this.shuheng.getParser().getCommand(userInput.getText().split(" ")).equals(Ui.Command.BYE);
        } catch (InvalidTaskException e) {
            System.out.println(e);
        }
        if (isExit) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        } else {
            userInput.clear();
        }
    }

    /**
     * Gets response from shuheng!
     *
     * @param input The user's input into the chatbot.
     */
    @FXML
    private String getResponse(String input) {
        String response = this.shuheng.getUi().run(input);
        return response;
    }
}

