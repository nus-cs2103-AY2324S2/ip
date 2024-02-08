package jayne;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
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
    private Jayne jayne;
    private boolean isEnd = false;
    @FXML
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/SnowieeCrop.png"));
    @FXML
    private Image jayneImage = new Image(this.getClass().getResourceAsStream("/images/Jayne.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String startMessage = "Wakey Wakey Snowiee\n" + "its time for schooooll.\n"
                + "What do you need?";

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startMessage, jayneImage));
    }

    public void setJayne(Jayne d) {
        jayne = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jayne.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, jayneImage)
        );
        if (Objects.equals(response, "Hey, Snowieeee, Bye. Hope to see you again soon!")) {
            this.isEnd = true;
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            pause.play();
        }
        userInput.clear();
    }
}
