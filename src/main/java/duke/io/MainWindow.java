package duke.io;

import duke.application.*;
import javafx.fxml.FXML;
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

    private Duke duke;
    private TaskList taskList;
    private Parser parser;

    private Image nervousUnicorn = new Image(this.getClass().getResourceAsStream("/images/nervousUnicorn.png"));

    private Image happyUnicorn = new Image(this.getClass().getResourceAsStream("/images/happyUnicorn.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d, TaskList taskList, Parser parser) {
        this.duke = d;
        this.taskList = taskList;
        this.parser = parser;
    }

    public void showWelcomeMessage() {
        String welcomeText = Ui.showWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeText, happyUnicorn));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Main.getResponse(input, taskList, parser);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, nervousUnicorn),
                DialogBox.getDukeDialog(response, happyUnicorn)
        );
        userInput.clear();
    }

}