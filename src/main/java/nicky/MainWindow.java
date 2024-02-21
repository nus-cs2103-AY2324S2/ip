package nicky;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller class for the main window of the Nicky application.
 * Manages the interaction between the user and the application's GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nicky nicky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image nickyImage = new Image(this.getClass().getResourceAsStream("/images/DaNicky.png"));

    /**
     * Initializes the main window components and binds properties for dynamic layout changes.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(
                scrollPane.widthProperty().subtract(20)
        );
    }

    /**
     * Sets the Nicky instance for the window and displays the welcome message.
     *
     * @param n Instance of Nicky to be used.
     */
    public void setNicky(Nicky n) {
        this.nicky = n;
        showWelcomeMessage();
    }

    /**
     * Displays the welcome message in the dialog container.
     */
    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getNickyDialog(nicky.getWelcomeMessage(), nickyImage));
    }

    /**
     * Method to handle user input and generate Nicky's response.
     * It also checks for an exit command to close the application.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nicky.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNickyDialog(response, nickyImage)
        );
        userInput.clear();

        if (response.equals("Bye. Hope to see you again soon!")) {
            waitAndExit(5000);
        }
    }

    /**
     * Closes the application after a specified delay.
     *
     * @param millisDelay The delay in milliseconds before closing the application.
     */
    private void waitAndExit(long millisDelay) {
        userInput.setDisable(true);
        sendButton.setDisable(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(Platform::exit);
                System.exit(0);
            }
        }, millisDelay);
    }
}
