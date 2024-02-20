import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mike.Mike;
import mike.MikeResponse;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final double DEFAULT_DELAY = 1.5;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Mike mike;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/cartoon_sully.png"));
    private final Image mikeImage = new Image(this.getClass().getResourceAsStream("/images/mike.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Setter for Mike.
     */
    public void setMike(Mike mike) {
        this.mike = mike;
    }

    /**
     * Starting behaviour.
     */
    public void start() {
        dialogContainer.getChildren().addAll(DialogBox.getMikeDialog(mike.getLogo(), mikeImage));
        Timeline timeline = new Timeline();
        timeline.setCycleCount(0);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(2.0),
                        event -> displayMikeText(mike.getGreeting()))
        );
        timeline.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mike's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String inputText = userInput.getText();
        userInput.clear();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(0);

        MikeResponse response = mike.getResponse(inputText);
        String responseText = response.toString();

        // normal behaviour
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0),
                        event -> displayUserText(inputText)),
                new KeyFrame(Duration.seconds(DEFAULT_DELAY),
                        event -> displayMikeText(responseText)));

        // on exit
        if (response.isExit()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(DEFAULT_DELAY),
                            event -> Platform.exit()));
        }

        timeline.play();
    }
    private void displayMikeText(String text) {
        dialogContainer
                .getChildren()
                .add(DialogBox.getMikeDialog(text, mikeImage));
    }
    private void displayUserText(String text) {
        dialogContainer
                .getChildren()
                .add(DialogBox.getUserDialog(text, userImage));
    }
}

/* TODO for the next commit:
 *  5. change completed tasks to have strikethrough
 */
