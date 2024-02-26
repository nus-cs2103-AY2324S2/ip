package alpa.main;

import alpa.commands.Command;
import alpa.commands.Parser;
import alpa.exceptions.AlpaException;
import javafx.fxml.FXML;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alpa alpa;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image alpaImage = new Image(this.getClass().getResourceAsStream("/images/Alpa.png"));

    /**
     * Initializes the main window.
     * Binds the scrollPane to the dialogContainer and shows the welcome message.
     */
    @FXML
    public void initialize() {
        // Load the images
        userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
        alpaImage = new Image(this.getClass().getResourceAsStream("/images/Alpa.png"));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Sets the Alpa object to be used by the main window.
     *
     * @param a The Alpa object to be used by the main window.
     */
    public void setAlpa(Alpa a) {
        alpa = a;
    }

    /**
     * Shows the welcome message from Alpa.
     */
    private void showWelcome() {
        String welcomeMessage = "Hello Human! I am your fluffy assistant, Alpa the Alpaca!\n"
                                + "I'm here to help you sort through the woolly world of tasks.\n";
        dialogContainer.getChildren().add(DialogBox.getAlpaDialog(welcomeMessage, alpaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * @throws AlpaException
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isBlank()) {
            try {
                Command command = Parser.parse(input);
                String response = alpa.getResponse(input);
                dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
                dialogContainer.getChildren().add(DialogBox.getAlpaDialog(response, alpaImage));
                userInput.clear();

                if (command.isExit()) {
                    closeApplication();
                }
            } catch (AlpaException e) {
                dialogContainer.getChildren().add(DialogBox.getErrorDialog(e.getMessage(), alpaImage));
                userInput.clear();
            }
        }
    }

    /**
     * Closes the application window.
     * Performs any necessary cleanup before closing the window.
     */
    private void closeApplication() {
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }
}
