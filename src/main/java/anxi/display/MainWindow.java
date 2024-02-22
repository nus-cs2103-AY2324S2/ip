package anxi.display;

import java.util.Timer;
import java.util.TimerTask;

import anxi.command.Ui;
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

    private Anxi duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Anxi d) {
        duke = d;
    }

    /**
     * Creates the welcome message to be printed.
     */
    public void welcomeMessage() {
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.printWelcomeMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input, the other containing Duke's reply.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.duke.getResponse(input);

        if (input.length() > 70) {
            String updatedInput = "";
            for (int i = 0; i < input.length(); i += 70) {
                updatedInput = new StringBuffer(input).insert(i, "\n").toString();
            }

            input = updatedInput;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.contains("Bye")) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            }, 1000);
        }
    }
}
