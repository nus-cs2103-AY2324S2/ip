package plato.ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import plato.Plato;

/**
 * Main controller for the Main window of the chat application.
 */
public class MainWindow extends AnchorPane {

    private static final int SAVE_FREQUENCY = 2000;
    private static final String PATH_SAVE = "data/tasks.txt";
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Plato duke;

    /**
     * Initialises preprocessing required before the gui comes out.
     */
    @FXML
    public void initialize() {
        duke = new Plato(PATH_SAVE, SAVE_FREQUENCY);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        printDukeMessage(Ui.saySmartStuff());
        printDukeMessage(Ui.sayWelcome());

    }

    @FXML
    private void printDukeMessage(String msg) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(msg, dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input).trim();
        if (response.matches("((?i)clear)")) {
            dialogContainer.getChildren().clear();
        } else {
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                                                 DialogBox.getDukeDialog(response, dukeImage));
        }

        userInput.clear();
        //Inspired from :
        // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        if (duke.hasExit()) {
            PauseTransition stopSystem = new PauseTransition(Duration.seconds(3));
            stopSystem.setOnFinished(event -> System.exit(0));
            stopSystem.play();
        }
    }
}
