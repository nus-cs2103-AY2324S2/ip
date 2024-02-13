package duke.ui;

import duke.main.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane dukeScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField dukeUserInput;
    @FXML
    private Button dukeSendButton;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog("hello world", dukeImage));
        dukeScrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    @FXML
//    private void handleUserInput() {
//        String input = dukeUserInput.getText();
//        String response = duke.run(input);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getDukeDialog(response, dukeImage));
//        if (input.equalsIgnoreCase("bye")) {
//            Platform.exit();
//        }
//        dukeUserInput.clear();
//    }
    @FXML
    private void handleUserInput() {
        String input = dukeUserInput.getText();
        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(response, dukeImage));
        dukeUserInput.clear();
    }
}
