package youdon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the main window of the YoudonBot application. Provides the layout and functionality
 * for interacting with the bot.
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

    private Youdon youdon;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image youdonBot = new Image(this.getClass().getResourceAsStream("/images/YoudonBot.jpg"));

    /**
     * Initializes the main window layout and binds the scroll pane to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getWelcomeDialog(youdonBot));
    }

    /**
     * Sets the Youdon instance for the main window.
     *
     * @param youdon The Youdon instance to be set.
     */
    public void setYoudon(Youdon youdon) {
        this.youdon = youdon;
    }

    /**
     * Handles user input by processing it and displaying the appropriate response from the bot.
     * Appends dialog boxes representing user input and bot response to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.isEmpty();
        String response = youdon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getYoudonDialog(response, youdonBot)
        );
        userInput.clear();
    }
}
