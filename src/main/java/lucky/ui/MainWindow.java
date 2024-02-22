package lucky.ui;

import lucky.Lucky;

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

    private Lucky lucky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/huhcat.png"));
    private Image luckyImage = new Image(this.getClass().getResourceAsStream("/images/Lucky.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer
                .getChildren()
                .add(DialogBox.getLuckyDialog("Hello! I'm Lucky the cat\nWhat can I do for you?", luckyImage));
        scrollPane.setStyle("-fx-background: #0a1526;");
    }

    public void setLucky(Lucky d) {
        lucky = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Lucky's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lucky.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLuckyDialog(response, luckyImage)
        );
        userInput.clear();
    }
}

