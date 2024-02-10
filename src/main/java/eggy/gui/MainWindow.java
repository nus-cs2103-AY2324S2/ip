package eggy.gui;

import eggy.Eggy;
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

    private Eggy eggy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image eggyImage = new Image(this.getClass().getResourceAsStream("/images/DaEggy.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setEggy(Eggy eggy) {
        this.eggy = eggy;
    }

    /**
     * Shows the initial message from Eggy.
     */
    public void showInitMessage() {
        if (eggy.getInitExceptionMessage() != null) {
            dialogContainer.getChildren().addAll(DialogBox.getEggyDialog(eggy.getInitExceptionMessage(), eggyImage));
        }
        dialogContainer.getChildren().addAll(DialogBox.getEggyDialog(eggy.getWelcomeMessage(), eggyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Eggy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (eggy.isExit()) {
            System.exit(0);
        }
        String input = userInput.getText();
        String response = eggy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEggyDialog(response, eggyImage)
        );
        userInput.clear();
    }
}
