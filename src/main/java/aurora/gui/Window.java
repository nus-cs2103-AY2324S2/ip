package aurora.gui;

import aurora.Aurora;

import aurora.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The Window class represents a controller for the main application window.
 * Code adapted and reused from: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Window extends AnchorPane {

    /** Scrollpane to be used such that users can scroll when giving multiple commands. */
    @FXML
    private ScrollPane scrollPane;

    /** Dialog container to encapsulate the dialogboxes. */
    @FXML
    private VBox dialogContainer;

    /** Box for user to type in commands. */
    @FXML
    private TextField userInput;

    /** User profile image. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Chillet.jpg"));

    /** Aurora profile image. */
    private Image auroraImage = new Image(this.getClass().getResourceAsStream("/images/Aurora.jpg"));

    /** Aurora API. */
    private Aurora aurora;

    /** Initializing function for some fields in the object. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = Ui.getOpeningMessage();
        dialogContainer.getChildren().addAll(DialogBox.getAuroraResponse(greeting, auroraImage));
    }

    public void setAurora(Aurora a) {
        this.aurora = a;
    }

    /** Handles a user command and outputs a response from the application. */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String response = getResponse(userInput.getText());
        String exitString = Ui.getExitMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getAuroraResponse(response, auroraImage)
        );
        if(exitString.equals(response)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) {
        String response = this.aurora.executeGui(input);
        return response;
    }
}
