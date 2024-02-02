package duke.frontend;

import duke.Duke;
import duke.UI;
import duke.exceptions.InvalidTaskException;
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

    private boolean isExit = false;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("../images/user.png"));

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("../images/shuheng.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes for user and shuheng replies.
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        try {
            isExit =
              this.duke.getParser().getCommand(userInput.getText().split(" ")).equals(UI.Command.BYE);
        } catch (InvalidTaskException e) {
            System.out.println(e);
        }
        userInput.clear();
    }

    /**
     * Gets response from shuheng!
     *
     * @param input The user's input into the chatbot.
     */
    @FXML
    private String getResponse(String input) {
        return this.duke.getUI().run(input);
    }
}

