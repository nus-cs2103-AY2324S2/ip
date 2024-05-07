package sky;

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

    private Sky sky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image skyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Sets the Sky instance.
     * @param d Sky instance.
     */
    public void setSky(Sky d) {
        sky = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sky's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sky.getResponse(input);
        assert response != null : "Response should not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSkyDialog(response, skyImage)
        );
        userInput.clear();
        if (input.toUpperCase().equals("BYE")) {
            sky.saveData();
            exit();
        }
    }

    /**
     * Shows the welcome message from Sky.
     */
    @FXML
    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getSkyDialog(Sky.UI.sayGreetings(), skyImage)
        );
    }

    /**
     * Exits the application.
     */
    @FXML
    private void exit() {
        Stage stage = (Stage) scrollPane.getScene().getWindow();
        stage.close();
    }
}
