package duke;

import javafx.application.Platform;
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

    private Duke duck;

    private Image userimage = new Image(this.getClass().getResourceAsStream("/images/PandaPo.jpg"));
    private Image bearduckyimage = new Image(this.getClass().getResourceAsStream("/images/Bearduckyimage.jpg"));

    @FXML
    public void initialize(Duke duck, Ui ui) {
        this.duck = duck;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(ui.greet(), bearduckyimage));
    }
    public void setDuke(Duke d) {
        duck = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duck.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userimage),
                DialogBox.getDukeDialog(response, bearduckyimage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}