package gui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Class that handles a Duke instance
 */
public class MainWindow extends AnchorPane {

    private Duke duke = new Duke("data", "tasks.txt");

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/niko.png"));
    private Image ai = new Image(this.getClass().getResourceAsStream("/images/alula.png"));

    public MainWindow() {}

    /**
     * Uwu
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greetUser();
    }

    @FXML
    private void greetUser() {
        dialogContainer.getChildren().add(
            DialogBox.getAiDialog(duke.greet(), ai));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getAiDialog(duke.getReply(input), ai)
        );
        userInput.clear();
    }
}
