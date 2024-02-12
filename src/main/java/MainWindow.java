import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/perrytheplatypus.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/drheinzdoofenshmirtz.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.greet();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void greet() {
        String greeting = "\n" +
                "ᴛᴀsᴋʏᴀᴘᴘᴇʀ";

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("*YAP* Good morning YAPPER! *YAP*\nGreetings from\n" + greeting, dukeImage)
        );
    }

    private String exit() {
        return "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        if (input.equals("bye")) {
            response = response + this.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        if (input.equals("bye")) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> Platform.exit()));
            timeline.play();
        }
    }
}
