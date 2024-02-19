package kai;

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

    private Kai duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image kaiImage = new Image(this.getClass().getResourceAsStream("/images/Kai.png"));

    /**
     * Handles user input by sending it to Duke for processing and displaying the response.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Kai d) {
        duke = d;
    }

    /**
     * Displays the welcome message when Duke starts.
     */
    public void showWelcomeMessage() {
        String welcomeText = duke.displayWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeText, kaiImage));
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        // Display user and Duke dialogue boxes
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, kaiImage)
        );

        // Clear user input after processing
        userInput.clear();
    }
}
