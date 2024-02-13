import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private Ypxmm ypxmm;

    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sayHello();
    }

    public void setYpxmm(Ypxmm y) {
        ypxmm = y;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates the welcome message.
     */
    @FXML
    private void sayHello() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello, I'm Ypxmm.\nNeed me do what for you?", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ypxmm.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        Label responseLabel = new Label(response);
        responseLabel.setWrapText(true);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();

        if (input.equals("bye")) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> stage.close()));
            timeline.play();
        }
    }
}