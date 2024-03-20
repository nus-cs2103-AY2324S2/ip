package duke.control;

import java.util.Objects;

import duke.Duke;
import duke.exceptions.BaseException;
import duke.ui.UI;
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
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String WELCOME_MESSAGE = new UI().onEnter();
    private static final String ERROR_MASSAGE = "Oops, some error occurs";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage =
        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image dukeImage =
        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/bot.png")));

    /**
     * Initializes the chatbot.
     */
    @FXML
    public void initialize() {
        try {
            this.duke = new Duke();
            String welcomeString = WELCOME_MESSAGE + "\n" + duke.getNextDueTasks();
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            // Show welcome message @A1WAYSD
            dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeString, dukeImage)
            );
        } catch (BaseException e) {
            // If there is an exception occur, exit the screen @A1WAYSD
            dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(ERROR_MASSAGE, dukeImage)
            );
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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
        if (response.equals(new UI().onExit())) {
            // Close the window after 2 seconds @A1WAYSD
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }
}

