package riri;

import static java.lang.Thread.sleep;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Riri riri;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image ririImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRiri(Riri r) {
        riri = r;
    }
    /**
     * Sends a welcome message for user from Riri.
     */
    public void welcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(riri.welcome(), ririImage)
        );
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Riri's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = riri.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, ririImage)
        );
        userInput.clear();

        if (input.equals("bye") || input.equals("exit")) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(riri.endingResponse(), ririImage)
            );
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            } finally {
                Platform.exit();
            }
        }
    }
}
