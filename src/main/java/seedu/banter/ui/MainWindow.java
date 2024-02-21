package seedu.banter.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.banter.Banter;


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

    private Banter banter;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/User.jpeg")));
    private final Image banterImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/Banter.jpeg")));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getBanterDialog(Ui.GREET_MESSAGE.getString(), banterImage));
    }

    public void setBanter(Banter b) {
        banter = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Banter's reply and then appends
     * them to the dialog container. Clears the user input after processing.
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
