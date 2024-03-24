package fireraya.Ui;

import fireraya.main.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Here we have a controller for MainWindow. Provides the layout for the other controls.
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

    private Fireraya fireraya;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/efren.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ronnie.png"));


    /**
     * Initializes the GUI's window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.printWelcome();
    }

    /**
     * Setter for the Fireraya class.
     */
    public void setFireraya(Fireraya f) {
        fireraya = f;
    }

    /**
     * Creates two dialog boxes, one echoing user input and one containing Duke's reply.
     * Then, appends them to the dialog container and clears the user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == "bye") {
            Platform.exit();
        }
        String response = fireraya.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Prints the pre-determined welcome message.
     */
    @FXML
    private void printWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.startMessage(), dukeImage)
        );
        userInput.clear();
    }
}
