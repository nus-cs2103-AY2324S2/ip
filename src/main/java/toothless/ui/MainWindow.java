package toothless.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import toothless.Toothless;

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

    private Toothless toothless;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image toothlessImage = new Image(this.getClass().getResourceAsStream("/images/DaToothless.jpg"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(ToothlessDialogBox.getDialog(
                " /\\_/\\\n"
                        + "( o.o )\n"
                        + "> ^ <\n"
                        + "Nya-ice to meet you! I'm Toothless :D\n"
                        + "What can I do for you?", toothlessImage)
        );
    }

    /**
     * Sets Toothless object.
     *
     * @param t Toothless object.
     */
    public void setToothless(Toothless t) {
        toothless = t;
        String initializeErrorMessage = toothless.initialize();
        if (!initializeErrorMessage.isBlank()) {
            dialogContainer.getChildren().add(
                    ToothlessDialogBox.getDialog(initializeErrorMessage, toothlessImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Toothless's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = toothless.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getDialog(input, userImage),
                ToothlessDialogBox.getDialog(response, toothlessImage)
        );
        userInput.clear();
    }
}
