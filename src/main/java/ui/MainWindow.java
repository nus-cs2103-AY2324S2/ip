package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jux.Jux;

import java.lang.reflect.InvocationTargetException;

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
    @FXML
    private HBox startDialog;

    private Jux jux;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private Image juxImage = new Image(this.getClass().getResourceAsStream("/images/dog.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        welcomeMessage();

        this.ui = new Ui();


    }

    public void setJux(Jux jux) {
        this.jux = jux;
    }

    /**
     * Creates the initial dialog box by Jux, only contains the welcome message then
     * appends to the dialog container. Located as a child in the fxml file
     */
    @FXML
    private void welcomeMessage(){
        String message = "Hello! I'm Jux\n" +
                "What can I do for you?\n";
        //message += jux.getStorageList(); whats up with this?
        startDialog = DialogBox.getJuxDialog(message, juxImage);
        dialogContainer.getChildren().addAll(
                startDialog
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jux.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJuxDialog(response, juxImage)
        );
        userInput.clear();
    }
}

