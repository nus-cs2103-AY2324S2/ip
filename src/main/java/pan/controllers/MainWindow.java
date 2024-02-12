package pan.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pan.Pan;
import pan.Storage;
import pan.TaskList;
import pan.Ui;

/**
 * MainWindow - Represents the MainWindow Class responsible with loading individual FXML elements.
 * @author Jerome Goh
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

    private Pan pan = new Pan(new Ui(), new TaskList(new Storage()));

    private Image smurf = new Image(this.getClass().getResourceAsStream("/images/clumsy.jpeg"));
    private Image panda = new Image(this.getClass().getResourceAsStream("/images/pan.png"));

    /**
     * Initalises the scrollPane and dialogContainer to render the initial Dialog Box.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(PanDialogBox.getPandaDialog(this.pan.sayHi(), panda));
    }

    /**
     * Updates the Main Pan Instance.
     */
    public void setPan(Pan p) {
        pan = p;
    }

    /**
     * Processes the user input by ensuring that dialogue is retained is being passed to the PanDialog.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pan.parseInput(input);
        dialogContainer.getChildren().addAll(
            PanDialogBox.getUserDialog(input, smurf),
            PanDialogBox.getPandaDialog(response, panda));
        userInput.clear();
    }
}
