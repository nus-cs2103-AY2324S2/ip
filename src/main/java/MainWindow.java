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
 * Controller class for the main window of the application. This class manages the layout and interaction
 * of various GUI elements such as dialog containers, text fields, buttons, and scroll panes.
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

    /**
     * Initializes the controller after its root element has been completely processed. This method is
     * automatically called by the JavaFX framework after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sayHello();
    }

    /**
     * Sets the Ypxmm instance for this controller. This allows the controller to interact with the
     * underlying logic and respond to user inputs appropriately.
     * @param y The Ypxmm instance to be set.
     */
    public void setYpxmm(Ypxmm y) {
        ypxmm = y;
    }

    /**
     * Sets the stage for this controller. The stage is necessary for certain operations such as closing
     * the application window.
     * @param stage The Stage instance to be set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates the welcome message.
     */
    @FXML
    private void sayHello() {
        String greeting = "Hello, I'm Ypxmm.\nNeed me do what for you?\nType in \"getcommands\" if you don't know"
                + " how to boss me around yet.";
        greeting = greeting.trim();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage)
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
