package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
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

    private Ui ui;
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDevGPT.png"));

    /**
     * Initializes the main window.
     *
     * @param duke the Duke object to handle user commands
     * @param ui the Ui object to handle messages for display
     */
    @FXML
    public void initialize(Duke duke, Ui ui) {
        assert duke != null : "Duke object should not be null";
        assert ui != null : "Ui object should not be null";
        botIntro(ui);
        this.duke = duke;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    @FXML
    private void botIntro(Ui ui) {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(ui.showWelcome(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one from user and one from Duke which are then added to the dialog container.
     * The user's command is parsed to duke and the response is displayed.
     * If the user inputs "bye", the application will close
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert response != null && !response.isEmpty() : "Response should not be null or empty";
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog("User:\n\t" + input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );

        if (input.equals("bye")) {
            Platform.exit();
            System.exit(0);
        }

        userInput.clear();
    }
}
