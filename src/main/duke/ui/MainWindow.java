package duke.ui;

import duke.duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Duke duke;

    private final Image SKIBIDI_TOILET =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/SkibidiToilet.png")));
    private final Image SKIBIDI_AGENT =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/SkibidiAgent.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String load = Duke.storage.load();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Skibidi.GREETING, SKIBIDI_TOILET),
                DialogBox.getDukeDialog(load, SKIBIDI_TOILET)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, SKIBIDI_AGENT),
                DialogBox.getDukeDialog(response, SKIBIDI_TOILET)
        );
        userInput.clear();
    }
}