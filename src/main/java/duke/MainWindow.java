package duke;

import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private Duke duke;
    private Ui ui = new Ui();
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaOnlyFriend.png"));

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     * It sets up the initial configuration of the GUI components,
     * particularly the scroll pane and the dialog container.
     * A welcome message is displayed in the dialog container as part of the initialization process to greet the user
     * when the application starts.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = ui.showHello();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    /**
     * Sets the Duke instance for this window controller. This method allows the main application class to pass
     * a reference to the Duke logic controller, enabling interaction between the GUI components and the application logic.
     *
     * @param d The Duke instance to be used by this controller.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (ui.showBye().equals(response)) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            PauseTransition delay = new PauseTransition(Duration.seconds(1)); // Delay for 1 second
            delay.setOnFinished(event -> {
                Stage stage = (Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });
            delay.play();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
