package duke.util;

import java.util.Arrays;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main window of the chatbot.
 */
public class MainWindow extends AnchorPane {
    private static final String SPLIT_COMMAND = "\\s*\\|\\|\\s*";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    /**
     * Initializes the scene.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.requestFocus();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String[] commands = input.split(SPLIT_COMMAND);
            Arrays.stream(commands)
                    .forEach(command -> {
                        String response = duke.getResponse(command);
                        dialogContainer.getChildren().addAll(
                                DialogBox.getUserDialog(command, userImage),
                                DialogBox.getDukeDialog(response, dukeImage)
                        );
                    });
            userInput.clear();
            if (duke.isExit()) {
                Stage stage = (Stage) dialogContainer.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays welcome message.
     */
    public void welcome() {
        String response = duke.getLogo();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
