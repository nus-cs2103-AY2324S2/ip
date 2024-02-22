package georgie;

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

    private Georgie georgie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DinosaurRight.png"));
    private Image georgieImage = new Image(this.getClass().getResourceAsStream("/images/DinosaurLeft.png"));

    /**
     * Initializes the JavaFX components when the FXML file is loaded.
     * Binds the vertical scroll value of the scroll pane to the height property of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        welcome();
    }


    /**
     * Sets the Duke instance for the controller.
     *
     * @param georgie The Georgie instance to be set.
     */
    public void setGeorgie(Georgie georgie) {
        this.georgie = georgie;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = georgie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGeorgieDialog(response, georgieImage)
        );
        userInput.clear();
    }

    @FXML
    private void welcome() {
        Ui ui = new Ui();
        String response = ui.showWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getGeorgieDialog(response, georgieImage)
        );

    }
}
