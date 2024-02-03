package huyang;

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

    private Huyang huyang;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Minion.png"));
    private Image huyangImage = new Image(this.getClass().getResourceAsStream("/images/Huyang.png"));

    /**
     * Initializes the MainWindow controller.
     *
     * This method is automatically called after the FXML layout is loaded and all @FXML-annotated fields are initialized.
     * It sets up the scroll pane to automatically scroll to the bottom of the dialog container and displays a greeting
     * message from Huyang in the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = Ui.getGreetingMessage();
        dialogContainer.getChildren().add(DialogBox.getHuyangDialog(greetingMessage, huyangImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Huyang instance for the MainWindow controller.
     *
     * @param h The Huyang instance to associate with this controller.
     */
    public void setHuyang(Huyang h) {
        huyang = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Huyang's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = huyang.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHuyangDialog(response, huyangImage)
        );
        userInput.clear();
    }
}
