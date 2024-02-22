package lilybot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private LilyBot lilyBot;
    private String lastCommand;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDog.jpeg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaMorningDog.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLilyBot(LilyBot l) {
        lilyBot = l;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = lilyBot.getResponse(input, lastCommand);
        DialogBox user = DialogBox.getUserDialog(input, userImage);
        DialogBox bot = DialogBox.getBotDialog(response, botImage);


        dialogContainer.getChildren().addAll(user, bot);
        this.lastCommand = input;

        userInput.clear();
    }

}
