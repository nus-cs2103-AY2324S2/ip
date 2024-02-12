package jayne;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Jayne jayne;
    private boolean isEnd = false;
    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/Images/SnowieeCrop.png")));
    private Image jayneImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/Images/Jayne.png")));
    /**
     * Initializes the UI components for the application. This method binds the scroll pane's
     * vertical scroll property to the dialog container's height to ensure automatic scrolling
     * as content increases. It also displays a starting message in the dialog container using
     * a {@link DialogBox} with a predefined image.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String startMessage = "Wakey Wakey Snowiee\n" + "its time for schooooll.\n"
                + "What do you need?";

        dialogContainer.getChildren().add(DialogBox.getJayneDialog(startMessage, jayneImage));
    }

    public void setJayne(Jayne d) {
        jayne = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jayne's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jayne.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJayneDialog(response, jayneImage)
        );
        if (Objects.equals(response, "Hey, Snowieeee, Go to sleep ok, stop bullying Slurpee")) {
            this.isEnd = true;
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            pause.play();
        }
        userInput.clear();
    }
}
