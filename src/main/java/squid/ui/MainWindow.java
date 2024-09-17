package squid.ui;

import static squid.Squid.parseInput;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import squid.Response;
import squid.Squid;
import squid.tasks.Tasks;


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

    private Squid squid;

    private Image imageUser = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private Image imageSquid = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaSquid.png")));

    /**
     * Initialize the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSquidDialog(new Label(Squid.hello()), new ImageView(imageSquid))
        );
    }

    public void setSquid(Squid d) {
        squid = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Response response = parseInput(true, userInput.getText());
        Label dukeText = new Label(response.getResponse());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(imageUser)),
                DialogBox.getSquidDialog(dukeText, new ImageView(imageSquid))
        );
        userInput.clear();
        boolean isLoop = response.getIsLoop();
        Tasks.save();
        if (!isLoop) {
            Platform.exit();
            System.exit(0);
        }
    }
}
