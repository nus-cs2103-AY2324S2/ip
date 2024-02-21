package tony.frontend;

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
import tony.Tony;
import tony.Ui;

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

    private Tony tony;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the javaFX components.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String dukeText = Ui.greeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getTonyDialog(dukeText, dukeImage)
        );
    }

    public void setTony(Tony t) {
        tony = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tony's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTonyDialog(response, dukeImage)
        );
        userInput.clear();
        if (response == this.goodbye) {
            PauseTransition exit = new PauseTransition(Duration.seconds(1));
            exit.setOnFinished(event -> {
                Platform.exit();
            });
            exit.play();
        }
    }

    /**
     * Gets response from tony!
     *
     * @param input The user's input into tony.
     */
    @FXML
    private String getResponse(String input) {
        String response = this.tony.run(input);
        return response;
    }

    private String goodbye = "_______________________\n"
            + "See ya later dawg!\n"
            + "_______________________\n";
}
