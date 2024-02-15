package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
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
    private Duke duke;
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/surprised_pikachu.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the instance of Duke to be used by the GUI.
     *
     * @param d Duke instance to be used by GUI.
     */
    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Greets the user upon loading up the GUI.
     */
    public void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.greet(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();
    }
}
