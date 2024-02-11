package duke.ui;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Main controller for the Main screen of the chatbot.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    /**
     * Initialises preprocessing required before the gui comes out.
     */
    @FXML
    public void initialize() {
        duke = new Duke("data/tasks.txt", 2000);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        printDukeMessage(Ui.sayWelcome());

    }

    @FXML
    private void printDukeMessage(String msg) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(msg, dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                                             DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
        //Inspired from :
        // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        if (duke.hasExit()) {
            PauseTransition stopSystem = new PauseTransition(Duration.seconds(5));
            stopSystem.setOnFinished(event -> System.exit(0));
            stopSystem.play();
        }
    }
}
