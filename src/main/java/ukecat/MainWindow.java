package ukecat;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

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
//    private Button sendButton;

    private UkeCat ukeCat;
    private final Image USER_IMG = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/DaUser.png")));
    private final Image UKECAT_IMG = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.WELCOME_GUI, UKECAT_IMG)
        );
    }

    public void setUkeCat(UkeCat u) {
        ukeCat = u;
    }

    /**
     * Handles user input events triggered by the user pressing the Enter key or clicking the submit button.
     * Processes the user input, generates a response from the UkeCat application,
     * and updates the dialog display.
     * If the user's input triggers a "bye" response, a pause is introduced before initiating a system exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Parser.parse(input);
        String reply = ukeCat.ui.getReply(Storage.words);
        String response = ukeCat.getResponse(reply);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input + "  ", USER_IMG),
                DialogBox.getDukeDialog(response, UKECAT_IMG)
        );
        userInput.clear();

        if (response.equals(Ui.BYE)) {
            // If the user entered "bye," add a pause before system exit
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }
}
