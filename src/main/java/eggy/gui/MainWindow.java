package eggy.gui;

import eggy.Eggy;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    private Eggy eggy;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image eggyImage = new Image(this.getClass().getResourceAsStream("/images/DaEggy.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setFont(Font.font("Arial", 14));
        sendButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    }

    public void setEggy(Eggy eggy) {
        this.eggy = eggy;
    }

    /**
     * Shows the initial message from Eggy.
     */
    public void showInitMessage() {
        assert eggy != null : "Eggy should not be null";
        assert dialogContainer != null : "Dialog container should not be null";
        if (eggy.getInitExceptionMessage() != null) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getEggyDialog(eggy.getInitExceptionMessage(), eggyImage, true)
            );
        }
        dialogContainer.getChildren().addAll(DialogBox.getEggyDialog(eggy.getWelcomeMessage(), eggyImage, false));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Eggy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "User input should not be null";
        assert eggy != null : "Eggy should not be null";
        assert dialogContainer != null : "Dialog container should not be null";
        String input = userInput.getText();
        String response = eggy.getResponse(input);

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        PauseTransition pause = new PauseTransition(Duration.seconds(0.4));
        pause.setOnFinished(event -> {
            if (response.startsWith("EGGIES!!!")) {
                dialogContainer.getChildren().addAll(DialogBox.getEggyDialog(response, eggyImage, true));
            } else {
                dialogContainer.getChildren().addAll(DialogBox.getEggyDialog(response, eggyImage, false));
            }
        });
        pause.play();
        userInput.clear();

        if (eggy.isExit()) {
            PauseTransition exitPause = new PauseTransition(Duration.seconds(3));
            exitPause.setOnFinished(event -> System.exit(0));
            exitPause.play();
        }
    }
}
