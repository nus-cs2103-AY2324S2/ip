package rick;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import rick.ui.DialogBox;

/**
 * A controller for the main window.
 */
public class MainController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button button;
    @FXML
    private TextField userInput;
    @FXML
    private VBox vBox;
    @FXML
    private Rick rick;
    @FXML
    private Image userImage = new Image(Main.class.getResourceAsStream("/images/morty.png"));
    @FXML
    private Image rickImage = new Image(Main.class.getResourceAsStream("/images/rick.png"));

    /**
     * Binds the value of xBox's height to scrollpane's vvalue
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(vBox.heightProperty());
    }

    /**
     * Shows UI changes after user inputs string.
     */
    @FXML
    protected void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            DialogBox dialogUser = DialogBox.getUserDialog(input, userImage);
            DialogBox dialogRick = DialogBox.getRickDialog(rick.getResponse(input), rickImage);
            vBox.getChildren().addAll(dialogUser, dialogRick);
            userInput.clear();
        }
    }

    /**
     * Initializes the Rick instance to be used in the application.
     * @param rick
     */
    public void setRick(Rick rick) {
        this.rick = rick;
    }
}
