package junjie;

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

    private Junjie junjie;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Junjie.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane is null";
        assert dialogContainer != null : "DialogContainer is null";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Junjie instance for the main window.
     *
     * @param junjie The Junjie instance to set.
     */
    public void setJunjie(Junjie junjie) {
        assert junjie != null : "Junjie is null";
        this.junjie = junjie;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "UserInput is null";
        assert dialogContainer != null : "DialogContainer is null";
        assert junjie != null : "Junjie is null";

        String input = userInput.getText();
        String response = junjie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (junjie.getExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}
