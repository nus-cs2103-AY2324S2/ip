package floofy;

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

    private Floofy floofy;

    private Image flooferImage = new Image(this.getClass().getResourceAsStream("/images/Floofer.png"));
    private Image floofBossImage = new Image(this.getClass().getResourceAsStream("/images/FloofBoss.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setFloofy(Floofy f) {
        floofy = f;
        dialogContainer.getChildren().addAll(DialogBox.getFloofyDialog(f.getWelcomeMessage(), floofBossImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = floofy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, flooferImage),
                DialogBox.getFloofyDialog(response, floofBossImage)
        );
        userInput.clear();
    }
}
