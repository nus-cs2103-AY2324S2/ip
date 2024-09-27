package jade.gui;

import jade.Jade;
import jade.ui.Ui;
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

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_IMG_PATH = "/images/user.png";
    private static final String JADE_IMG_PATH = "/images/jade.png";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jade jade;

    private final Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMG_PATH));
    private final Image jadeImage = new Image(this.getClass().getResourceAsStream(JADE_IMG_PATH));

    /**
     * Initializes the scrollPane with the launch message for user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(JadeDialogBox.getDialog(Ui.LAUNCH_MESSAGE, jadeImage));
    }

    public void setJade(Jade j) {
        jade = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jade.getResponse(input);
        var ud = UserDialogBox.getDialog(input, userImage);
        var jd = JadeDialogBox.getDialog(response, jadeImage);
        dialogContainer.getChildren().addAll(ud, jd);
        actOnExit();
        userInput.clear();
    }
    /**
     * Checks if user has required for exiting the program,
     * and sets exit actions.
     */
    private void actOnExit() {
        if (jade.shouldExit()) {
            userInput.setEditable(false);
            sendButton.setDisable(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event ->
                    Platform.exit());
            pause.play();
        }
    }
}
