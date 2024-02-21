import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final int TIME_TO_EXIT_SECONDS = 1;
    private static final String MSG_WHEN_BOOT = "This is Steven!\nHow can I advise?\nSteven's advice: Don't know what "
            + "commands I understand? Use \"help\"!";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Steven steven;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image stevenImage = new Image(this.getClass().getResourceAsStream("/images/Steven.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSteven(Steven s) {
        steven = s;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(MSG_WHEN_BOOT, stevenImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = steven.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, stevenImage)
        );
        if (input.equals("bye")) {
            exit();
        }
        userInput.clear();
    }

    private void exit() {
        ScheduledExecutorService exitSchedule = Executors.newSingleThreadScheduledExecutor();
        exitSchedule.schedule(() -> Platform.exit(), TIME_TO_EXIT_SECONDS, TimeUnit.SECONDS);
        exitSchedule.shutdown();
    }
}
