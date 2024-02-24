import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Taylor taylor;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Taylor.png"));
    private Image tayImage = new Image(this.getClass().getResourceAsStream("/images/TS.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialises Taylor chatBot
     */
    public void setTaylor(Taylor t) {
        taylor = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String tayText = this.taylor.runCommand(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getTaylorDialog(tayText, tayImage)
        );

        userInput.clear();

        if ("Bye".equalsIgnoreCase(userText.trim())) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> System.exit(0)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
}
