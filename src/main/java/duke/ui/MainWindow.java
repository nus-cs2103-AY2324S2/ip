package duke.ui;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Class that implements the Main Window of the bot
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private String name = "CBBW";
    private String startupMessage = "Hello! I'm " + name + "\nWhat can I do for you?";
    private String goodbyeMessage = "See you again soon!";

    /**
     * Initializations for the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initalises the Duke bot used together with the interface to provide functionality and responses.
     */
    public void setDuke(Duke d) {
        duke = d;
        String loadStatus = d.loadSave("data/data.txt");
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(loadStatus, dukeImage),
            DialogBox.getDukeDialog(startupMessage, dukeImage)
        );

        assert d.isRunning();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (!duke.isRunning()) {
            dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(goodbyeMessage, dukeImage)
            );
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
