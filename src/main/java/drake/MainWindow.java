package drake;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_PATH = "/images/user.jpeg";
    private static final String DRAKE_PATH = "/images/drake.jpeg";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Drake drake;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(USER_PATH)));
    private final Image drakeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(DRAKE_PATH)));

    /**
     * Initializes Dialog Container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the instance of the Drake class.
     * @param d Drake instance to be set.
     */
    public void setDrake(Drake d) {
        drake = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = drake.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, drakeImage)
        );
        userInput.clear();
    }

    /**
     * Sets a welcome message.
     *
     * @param message Welcome message.
     */
    public void setWelcomeMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(message, drakeImage));
    }
}
