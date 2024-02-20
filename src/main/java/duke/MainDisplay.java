package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainDisplay.
 */
public class MainDisplay extends AnchorPane {
    public static final String USERIMGPATH = "/images/user.png";
    public static final String HARIIMGPATH = "/images/hari.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField textInput;
    @FXML
    private Button enterButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USERIMGPATH));

    private Image dukeImage = new Image(this.getClass().getResourceAsStream(HARIIMGPATH));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke dukes) {
        duke = dukes;
    }

    /**
     * Creates two dialog boxes, one for user input and the other containing Duke's reply.
     * Input is cleared after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = textInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        textInput.clear();
    }
}
