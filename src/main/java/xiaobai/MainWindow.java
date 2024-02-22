package xiaobai;

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

    private XiaoBai xiaoBai;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image xiaoBaiImage = new Image(this.getClass().getResourceAsStream("/images/XiaoBai.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeString = XiaoBai.showWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getDialog(welcomeString, xiaoBaiImage));
    }

    public void setXiaoBai(XiaoBai d) {
        xiaoBai = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = xiaoBai.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDialog(response, xiaoBaiImage));
        userInput.clear();
    }
}
