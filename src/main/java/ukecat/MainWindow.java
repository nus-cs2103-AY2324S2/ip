package ukecat;

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
    private static final int EXIT_WAIT_TIME = 1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button  sendButton;
    private UkeCat ukeCat;
    private final Image userImg = new Image("/images/User.png");
    private final Image botImg = new Image("/images/UkeCat.png");



    /**
     * Initializes the FXML components for the main window.
     * Binds the vertical scroll value of the scroll pane to the height of the dialog container.
     * Adds an initial Duke dialog to the dialog container.
     *
     * <p>This method is automatically called by JavaFX when loading the FXML file.</p>
     *
     * @throws AssertionError If any of the critical FXML components
     *         (ScrollPane, DialogContainer, or UserInput) is not initialized.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane not initialized";
        assert dialogContainer != null : "DialogContainer not initialized";
        assert userInput != null : "UserInput not initialized";
        assert sendButton != null : "Button not initialized";

        dialogContainer.setStyle("-fx-background-color: black; -fx-border-color: #00ff29;" );
        userInput.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-family: Consolas; " +
                "-fx-font-size: 14; -fx-border-color: #00ff29; -fx-border-width: 2px");
        sendButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-family: Consolas; " +
                "-fx-font-size: 14; -fx-border-color: #00ff29; -fx-border-width: 2px");
        scrollPane.setStyle("-fx-background-color: #00ff29; -fx-border-color: green; -fx-border-width: 2px;");

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(Ui.WELCOME_GUI, botImg)
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
        assert userInput != null : "UserInput is null";
        assert ukeCat != null : "UkeCat is null";

        String input = userInput.getText();
        Parser.parse(input);
        String reply = ukeCat.ui.getReply(Storage.getWords());
        String response = ukeCat.getResponse(reply);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input + "  ", userImg),
                DialogBox.getBotDialog(response, botImg)
        );
        userInput.clear();

        if (response.equals(Ui.BYE)) {
            // If the user entered "bye," add a pause before system exit
            PauseTransition pause = new PauseTransition(Duration.seconds(EXIT_WAIT_TIME));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }
}
