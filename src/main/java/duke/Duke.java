package duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/chad.jpeg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/wojak.jpeg"));
    private Parser parser = new Parser();

    public Duke() {
    }
    @Override
    public void start(Stage stage) {

    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.interpret(input);
    }

}
