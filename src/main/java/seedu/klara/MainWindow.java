package seedu.klara;

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

    private Klara klara;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image klaraImage = new Image(this.getClass().getResourceAsStream("/images/Klara.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKlara(Klara d) {
        klara = d;
    }

    /**
     * Represents the welcome message when Klara starts.
     */
    public void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getKlaraDialog(klara.welcomeMessage(), klaraImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Klara's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userImage != null : "User image should not be null";
        assert klaraImage != null : "Klara image should not be null";
        String input = userInput.getText();
        String response = klara.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKlaraDialog(response, klaraImage)
        );
        userInput.clear();
    }
}
