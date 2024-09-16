package linus;

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

    private Linus linus;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Bad_Luck_Brian.png"));
    private Image linusImage = new Image(this.getClass().getResourceAsStream("/images/linus_image.png"));

    @FXML
    public void initialize() {
        linus = new Linus();
        dialogContainer.setStyle("-fx-background-color: #CCB3FF");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(linus.loadData(), linusImage),
                DialogBox.getDukeDialog(linus.greeting(), linusImage)
  
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = linus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, linusImage)
        );
        userInput.clear();
    }
}
