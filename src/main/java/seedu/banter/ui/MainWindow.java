package seedu.banter.ui;

import seedu.banter.Banter;
import seedu.banter.ui.DialogBox;
import seedu.banter.ui.Ui;

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

    private Banter banter;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image banterImage = new Image(this.getClass().getResourceAsStream("/images/Banter.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getBanterDialog(Ui.GREET_MESSAGE.getString(), banterImage));
    }

    public void setBanter(Banter b) {
        banter = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Banter's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = banter.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBanterDialog(response, banterImage)
        );
        userInput.clear();
    }
}
