package dook;

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

    private Dook dook;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Meow2.png"));
    private Image dookImage = new Image(this.getClass().getResourceAsStream("/images/Meow1.png"));

    private String logo = " ____              _  \n"
            + "|  _ \\  ___   ___ | | __      ╱|\n"
            + "| | | |/ _ \\ / _ \\| |/ /    (˚. 。7  \n"
            + "| |_| | |_| | |_| |   <      |、˜〵 \n"
            + "|____/ \\___/ \\___/|_|\\_\\     \u3058\u3057_,)ノ\n";
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(logo, dookImage));
    }

    public void setDook(Dook d) {
        dook = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dook.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, dookImage));
        userInput.clear();
    }
}
