package judy.javafx;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import judy.Judy;
import judy.ui.Ui;

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
    private Judy judy;
    private Ui ui = new Ui();
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image judyImage = new Image(this.getClass().getResourceAsStream("/images/Judy.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        String greetMessage = ui.showWelcome();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        dialogContainer.getChildren().addAll(
                DialogBox.getJudyDialog(greetMessage, judyImage)
        );
    }

    /**
     * Sets Judy object.
     * @param j The Judy object to set.
     */
    public void setJudy(Judy j) {
        this.judy = j;
    }

    /**
     * Handles user input when user clicks 'send'.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = judy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJudyDialog(response, judyImage)
        );
        if (judy.isExit()) {
            exitApp();
        }
        userInput.clear();
    }

    /**
     * Exits the app when the application is exited.
     */
    private void exitApp() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();

        ui.showGoodbye();
    }
}
