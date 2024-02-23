package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Represents the Main Display Window of the HariUp chat application
 */
public class MainWindow extends AnchorPane {
    public static final String USERIMGPATH = "/images/user.PNG";
    public static final String HARIIMGPATH = "/images/hari.PNG";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USERIMGPATH));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(HARIIMGPATH));

    /**
     * Creates the main display window for the user interface
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets duke object (dukes) to be associated with the main display window
     * Includes the display of the application greeting message upon startup
     */
    public void setDuke(Duke dukes) {
        duke = dukes;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Ui().greeting(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one for user input and the other containing the reply from the bot
     * Input is cleared after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.userOps(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
