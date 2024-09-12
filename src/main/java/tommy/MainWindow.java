package tommy;

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
    private Tommy tommy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bobIcon.png"));
    private Image tommyImage = new Image(this.getClass().getResourceAsStream("/images/bearIcon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTommy(Tommy t) {
        tommy = t;
        dialogContainer.getChildren().add(
                DialogBox.getTommyDialog(tommy.getGreetings(), tommyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        boolean isActive;

        String response = tommy.getResponse(input);
        isActive = tommy.isActive();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTommyDialog(response, tommyImage)
        );

        userInput.clear();

        if (!isActive) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                Stage stage = (Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });

            pause.play();
        }
    }
}
