package duke.ui;

import duke.main.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class serves as the controller for the main window of the Duke application.
 * It provides the layout for the other controls and handles user interactions.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane dukeScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField dukeUserInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Initializes the controller after the FXML file has been loaded.
     * It binds the vertical value property of the scroll pane to the height property of the dialog container,
     * ensuring that the scroll pane automatically scrolls to the bottom when new content is added.
     */
    @FXML
    public void initialize() {
        dukeScrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke object for the MainWindow.
     *
     * @param d The Duke object to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Customizes the initialization of the MainWindow by setting spacing for dialog containers and adding the
     * introductory message from Duke.
     */
    public void customisedInitialise() {
        dialogContainer.setSpacing(20);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getIntroMessage(), dukeImage)
        );
    }

    /**
     * Handles user input. Echoes user input and displays Duke's response in dialog boxes.
     * Clears the user input after processing. If the user input is "bye", exits the application.
     */
    @FXML
    private void handleUserInput() {
        String input = dukeUserInput.getText();
        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.trim().toLowerCase().equals("bye")) {
            Platform.exit();
        }
        dukeUserInput.clear();
    }
}
