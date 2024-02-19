import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    private ChatPal duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/corgi.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/golden_retriever.jpg"));

    /**
     * Initializes Dialog Container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    public void setDuke(ChatPal d) {
        duke = d;
    }

    /**
     * Displays greeting message.
     */
    private void greet() {
        String text = "Hi Hi!! Your personal ChatPal here! How can I help you?\n";
        DialogBox greetDialog = DialogBox.getDukeDialog(text, dukeImage);
        dialogContainer.getChildren().add(greetDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChatPal's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        assert userText != null;

        String dukeText = duke.getResponseMessage(userText);

        DialogBox userDialog = DialogBox.getUserDialog(userText, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, dukeImage);

        if (dukeText.contains("Error:")) {
            dukeDialog.setBackground(new Background(new BackgroundFill(Color.web("#ffbfbf"), null, null)));
        }
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        dialogContainer.setPadding(new Insets(10));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.clear();
        if (userText.equals("bye")) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> Platform.exit()));
            timeline.play();
        }
    }
}
