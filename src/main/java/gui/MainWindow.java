package gui;

import commands.Command;
import exceptions.DukeException;
import howie.Howie;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import parser.Parser;
import storage.Storage;
import tasklists.TaskList;

import java.io.IOException;

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

    private Howie howie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ernie.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bert.png"));

    public MainWindow() throws DukeException {
    }


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog("Hello! How can I help you today?", dukeImage));
    }

    public void setHowie(Howie h) {
        howie = h;
    }

    Storage storage = new Storage();
    TaskList taskList = storage.readFile();

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        Command command;

        try {
            command = new Parser().parseCommand(input.split(" "));
            command.setData(taskList);
            String response =  command.executeCommand();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("Me: " + input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("Me: " + input, userImage),
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        } finally {
            if (input.equals("bye")) {
                System.exit(0);
            }

            userInput.clear();
        }
    }

}
