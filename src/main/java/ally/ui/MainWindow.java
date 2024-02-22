package ally.ui;

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

    private Ally ally;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image allyImage = new Image(this.getClass().getResourceAsStream("/images/ally.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAlly(Ally a) {
        ally = a;
        a.linkMainWindow(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ally.getResponse(input);
        assert response != null;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAllyDialog(response, allyImage)
        );

        userInput.clear();
    }

    /**
     * Creates a Dialog Bog containing specified Message.
     * @param s String to be added
     */
      public void addMessage(String s) {
        dialogContainer.getChildren().addAll(
                DialogBox.getAllyDialog(s, allyImage)
        );
    }
}
