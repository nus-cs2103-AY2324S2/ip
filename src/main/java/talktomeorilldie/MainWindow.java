package talktomeorilldie;

import java.util.Objects;

import javafx.fxml.FXML;
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

    private TalkToMeOrIllDie talktomeorilldie;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image talktomeorilldieImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets TalkToMeOrIllDie to the current TalkToMeOrIllDie object.
     * @param d The TalkToMeOrIllDie object to set.
     */
    public void setTalktomeorilldie(TalkToMeOrIllDie d) {
        talktomeorilldie = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing TalkToMeOrIllDie's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = talktomeorilldie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.gettalktomeorilldieDialog(response, talktomeorilldieImage)
        );
        userInput.clear();
    }
}
