package duke.ui;

import duke.Duke;
import duke.ui.DialogBox;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user2.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/sophia.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        printDefaultMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void printDefaultMessage() {
        String defaultMessage = "Hi there! I'm Sophia :)\n" +
                "I'm your AI Assistant and I'm here\n" +
                "to help you with anything.";
        dialogContainer.getChildren().add(DukeDialogBox.getDukeDialog(defaultMessage, dukeImage));
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                DukeDialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}
