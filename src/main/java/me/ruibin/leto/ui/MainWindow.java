package me.ruibin.leto.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import me.ruibin.leto.Leto;
import me.ruibin.leto.parser.Result;
import me.ruibin.leto.parser.ResultTypes;
import me.ruibin.leto.tasklist.TaskList;

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

    private Leto leto;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/paul.png"));

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke_leto.png"));


    /**
     * Initializes the main window of the app. Display welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.hvalueProperty().bind(dialogContainer.widthProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.letoLogo(), dukeImage),
                DialogBox.getDukeDialog(TaskList.initFromCsvFile().getMessage(), dukeImage)
        );
    }

    public void setDuke(Leto d) {
        leto = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Result result = leto.getResponse(input);
        String response = result.getMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (result.getType().equals(ResultTypes.EXIT)) {
            System.exit(0);
        }
    }
}
