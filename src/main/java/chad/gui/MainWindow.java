package chad.gui;

import chad.Chad;
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

    private Chad chad;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image chadImage = new Image(this.getClass().getResourceAsStream("/images/Chad.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChad(Chad chad) {
        this.chad = chad;
        dialogContainer.getChildren().add(DialogBox.getChadDialog(chad.greet(), chadImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chad's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chad.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChadDialog(response, chadImage)
        );
        userInput.clear();
    }
}

