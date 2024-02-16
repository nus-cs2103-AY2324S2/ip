package grizzly.ui;

import grizzly.Grizzly;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Class that implements the Main Window of the bot
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

    private Grizzly grizzly;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image grizzlyImage = new Image(this.getClass().getResourceAsStream("/images/Grizzly.png"));

    private String startupMessage = "Heyo im Grizzly.\nWhat can I do for you?";
    private String goodbyeMessage = "While waiting for you to return, I shall munch on these cookies I have here";

    /**
     * Initializations for the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initalises the Grizzly bot used together with the interface to provide functionality and responses.
     */
    public void setGrizzly(Grizzly g) {
        grizzly = g;
        String loadStatus = g.loadSave("data/data.txt");
        dialogContainer.getChildren().addAll(
            DialogBox.getGrizzlyDialog(loadStatus, grizzlyImage),
            DialogBox.getGrizzlyDialog(startupMessage, grizzlyImage)
        );

        assert g.isRunning();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Grizzly's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = grizzly.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getGrizzlyDialog(response, grizzlyImage)
        );
        userInput.clear();

        if (!grizzly.isRunning()) {
            dialogContainer.getChildren().addAll(
                DialogBox.getGrizzlyDialog(goodbyeMessage, grizzlyImage)
            );
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
