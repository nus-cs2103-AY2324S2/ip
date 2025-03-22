import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import naruto.Naruto;

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

    private Naruto naruto;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/user.png")));
    private final Image narutoImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/naruto.png")));

    /**
     * Initializes the main window.
     * Binds the vertical scroll position of the scroll pane to the height of the dialog container.
     * Adds a dialog box with a message from Naruto to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getNarutoDialog("Hello! I'm Naruto, and I'm "
                        + "training to become a ninja!\nBelieve it!", narutoImage));
        scrollPane.setStyle("-fx-background: #C0C0C0;");
    }

    public void setNaruto(Naruto n) {
        naruto = n;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Naruto's reply and
     * then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = naruto.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNarutoDialog(response, narutoImage)
        );
        userInput.clear();
        if (input.trim().equals("bye") || input.trim().equals("bb")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1)); // Adjust the delay as needed
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }

    }
}
