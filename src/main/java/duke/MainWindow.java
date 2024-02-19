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

    private Duke duck;

    // Image resources for user and Duke avatars
    private Image userimage = new Image(this.getClass().getResourceAsStream("/images/PandaPo.jpg"));
    private Image bearduckyimage = new Image(this.getClass().getResourceAsStream("/images/Bearduckyimage.jpg"));

    /**
     * Initializes the MainWindow controller.
     * @param duck The Duke object representing the chatbot.
     * @param ui The Ui object providing user interface interactions.
     */
    @FXML
    public void initialize(Duke duck, Ui ui) {
        this.duck = duck;
        // Ensure scrollPane always scrolls to the bottom when new content is added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Add a greeting message from Duke to the dialog container
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(ui.greet(), bearduckyimage));
    }

    /**
     * Sets the Duke object for the MainWindow controller.
     * @param d The Duke object representing the chatbot.
     */
    public void setDuke(Duke d) {
        duck = d;
    }

    /**
     * Handles user input events.
     * Echoes user input and displays Duke's reply in the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duck.getResponse(input);
        // Add user and Duke dialog boxes to the dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userimage),
                DialogBox.getDukeDialog(response, bearduckyimage)
        );
        userInput.clear();

        // Exit the application if the user input is "bye"
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}
