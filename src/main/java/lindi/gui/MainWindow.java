package lindi.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lindi.Lindi;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final Duration DELAY_DURATION_BEFORE_EXIT = Duration.seconds(1);
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Lindi lindi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image lindiImage = new Image(this.getClass().getResourceAsStream("/images/DaLindi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLindi(Lindi l) {
        lindi = l;
    }

    public boolean isLindiExit() {
        return lindi.isExit();
    }

    /**
     * Exits the GUI program after a delay.
     */
    private void exitProgramAfterDelay() {
        PauseTransition delay = new PauseTransition(DELAY_DURATION_BEFORE_EXIT);
        delay.setOnFinished(event -> System.exit(0));
        delay.play();
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lindi.getResponse(input);
        createDialogBoxes(input, response);
        userInput.clear();
        if (isLindiExit()) {
            exitProgramAfterDelay();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply.
     * Adds them to the dialog container.
     *
     * @param input user input
     * @param response response of Lindi to the user input
     */
    private void createDialogBoxes(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, lindiImage)
        );
    }
}
