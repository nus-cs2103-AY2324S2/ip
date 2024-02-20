package duke.gui;

import duke.core.MeanDuke;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    /**
     * Initialises the MainWindow controller, which hosts the MeanDuke application
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(MeanDuke.initialise(this))
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        String response = MeanDuke.getResponse(input, this);
        assert !response.isEmpty();
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response)
        );
        this.userInput.clear();
    }

    /**
     * Tells this controller to display a message from MeanDuke
     *
     * @param message To be displayed
     */
    public void showMessage(String message) {
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(message)
        );
    }
}

