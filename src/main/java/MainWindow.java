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

    private Snom snom;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/trainer.png"));
    private Image snomImage = new Image(this.getClass().getResourceAsStream("/images/snom.png"));

    /**
     * Set up the scrollPane
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setSnom(Snom s) {
        this.snom = s;
        dialogContainer.getChildren().addAll((DialogBox.getSnomDialog(snom.getStartMessage(), snomImage)));
    }

    /**
     * Creates two dialog boxes, one to echo the input of the user and one for
     * Snom to reply
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.snom.runCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSnomDialog(response, snomImage)
        );

        userInput.clear();

        if (response.equals(snom.getExitMessage())) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> System.exit(0)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }



}
