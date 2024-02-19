import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. It sets up the scroll pane to
     * automatically scroll to the bottom whenever the dialog container's height
     * changes. It also calls the greet method to display the initial greeting
     * message in the dialog.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.greet();
    }

    /**
     * Sets the Duke instance for the controller. This method allows the
     * integration of the main logic of the Duke application with this controller,
     * enabling interaction between the UI and the Duke application logic.
     *
     * @param d The Duke instance to be used by this controller.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays a greeting message in the dialog container when the application starts.
     * The greeting includes a custom message and a graphical representation of the
     * application's name, TaskYapper, to welcome the user.
     */
    private void greet() {
        String greeting = "\n" + "ᴛᴀsᴋʏᴀᴘᴘᴇʀ";

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("*YAP* Good morning YAPPER! *YAP*\nGreetings from\n" + greeting, dukeImage)
        );
    }

    /**
     * Displays a exit message in the dialog container when the application ends.
     */
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
        assert input != null;
        String response = duke.getResponse(input);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);


        if (input.equals("bye")) {
            response = response + this.exit();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> Platform.exit()));
            timeline.play();
        }


        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        if (response.startsWith("Error")) {
            dukeDialog.setDialogStyle("-fx-text-fill: red;");
        }

        dialogContainer.getChildren().addAll(userDialog, dukeDialog);
        userInput.clear();

    }
}
