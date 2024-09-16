// @@author hjuntan-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with modifications

package toothless;

import javafx.animation.PauseTransition;
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

    private Toothless toothless;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image toothlessImage = new Image(this.getClass().getResourceAsStream("/images/DaToothless.png"));
    private Image warningImage = new Image(this.getClass().getResourceAsStream("/images/DaWarning.gif"));
    private Image toothlessDanceImage = new Image(this.getClass().getResourceAsStream(
            "/images/DaToothlessDance.gif"));
    private Image skyImage = new Image(this.getClass().getResourceAsStream("/images/DaSky.jpg"));

    @FXML
    public void initialize() {
        dialogContainer.backgroundProperty().set(new Background(
                new BackgroundImage(skyImage, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setToothless(Toothless t) {
        toothless = t;
    }

    /**
     * Initializes the application with Toothless' loading tasks and greeting.
     */
    public void startUp(){
        dialogContainer.getChildren().addAll(
                DialogBox.getToothlessDialog(toothless.loadingTasks(), toothlessImage),
                DialogBox.getToothlessDialog(toothless.load(), toothlessImage),
                DialogBox.getToothlessDialog(toothless.greet(), toothlessImage)
        );
    }

    /**
     * Creates two dialog boxes,
     * one echoing user input and the other containing Toothless' reply.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = toothless.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getToothlessDialog(response, toothlessDanceImage)
            );
        } catch (ToothlessException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getExceptionDialog(e.getMessage(), warningImage)
            );
        } finally {
            if (toothless.isExit()) {
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
                pauseTransition.setOnFinished(event -> {
                    Platform.exit();
                });
                pauseTransition.play();
                userInput.setDisable(true);
                sendButton.setDisable(true);
            }
            userInput.clear();
        }
    }
}

// @@ author
