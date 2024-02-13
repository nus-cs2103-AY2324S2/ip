import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

    private Fredricksen fredricksen;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Russell.jpeg"));
    private Image fredricksenImage = new Image(this.getClass().getResourceAsStream("/images/Fredricksen.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        BackgroundFill bgFill = new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgFill);
        dialogContainer.setBackground(bg);
    }

    public void setFredricksen(Fredricksen d) {
        fredricksen = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fredricksen.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, fredricksenImage)
        );
        userInput.clear();
    }
}
