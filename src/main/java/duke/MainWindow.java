package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String COMMAND_EXIT = "bye";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private HughJazz duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaChatbot.png"));

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     * It sets up the initial dialog box with a welcome message and configures the scroll pane.
     */
    @FXML
    public void initialize() {
        duke = new HughJazz();
        duke.init();

        String welcomeMessage = "Hello! I'm HughJazz. How can I help you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for the main window.
     * This method allows the injection of a specific instance of HughJazz (Duke) into the controller.
     *
     * @param d The HughJazz instance to be used by this controller.
     */
    public void setDuke(HughJazz d) {
        duke = d;
    }

    /**
     * Handles the action of the user pressing the send button or pressing Enter after typing their input.
     * It creates dialog boxes for both the user's input and Duke's response, appends them to the dialog container,
     * and then clears the user input field. If the user types "bye", it closes the application.
     *
     * @param event The action event triggered by the user's interaction.
     */
    @FXML
    private void handleUserInput(ActionEvent event) {
        assert duke != null : "Duke application logic handler must be initialized";
        assert dialogContainer != null : "Dialog container must be initialized";

        String input = userInput.getText();

        if (COMMAND_EXIT.equals(input.trim().toLowerCase())) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }
}

