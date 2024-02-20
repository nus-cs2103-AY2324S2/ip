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
 * Controller class for the MainWindow.fxml file.
 * It provides the layout for the main window and handles user interactions.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane; // Scroll pane for the dialog container
    @FXML
    private VBox dialogContainer; // VBox container for dialog boxes
    @FXML
    private TextField userInput; // Text field for user input
    @FXML
    private Button sendButton; // Button for sending user input

    private Duke duck; // The Duke instance for processing user input

    // Images for user and Duke avatars
    private Image userimage = new Image(this.getClass().getResourceAsStream("/images/PandaPo.jpg"));
    private Image bearduckyimage = new Image(this.getClass().getResourceAsStream("/images/Bearduckyimage.jpg"));

    /**
     * Initializes the MainWindow with a Duke instance and a Ui instance.
     * Binds the scrollPane to the heightProperty of the dialogContainer.
     *
     * @param duck The Duke instance for processing user input.
     * @param ui   The Ui instance for user interface operations.
     */
    @FXML
    public void initialize(Duke duck, Ui ui) {
        this.duck = duck;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(ui.greet(), bearduckyimage));
    }

    /**
     * Sets the Duke instance for processing user input.
     *
     * @param d The Duke instance to be set.
     */
    public void setDuke(Duke d) {
        duck = d;
    }

    /**
     * Handles user input by processing it with Duke and updating the dialog container.
     * Clears the user input field after processing.
     * Exits the application if the user inputs "bye".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duck.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userimage),
                DialogBox.getDukeDialog(response, bearduckyimage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}
