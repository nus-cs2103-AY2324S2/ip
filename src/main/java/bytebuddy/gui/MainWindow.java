package bytebuddy.gui;

import static bytebuddy.constants.Messages.BYE_MESSAGE;
import static bytebuddy.constants.Messages.START_MESSAGE;

import bytebuddy.ByteBuddy;
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

    private ByteBuddy byteBuddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image byteBuddyImage = new Image(this.getClass().getResourceAsStream("/images/bytebuddy.jpeg"));

    /**
     * Initializes the main window of the ByteBuddy GUI application.
     * This method is automatically called by JavaFX after the FXML file is loaded
     * and the corresponding UI components are injected.
     *
     * @see javafx.scene.control.ScrollPane
     * @see javafx.scene.layout.VBox
     * @see bytebuddy.gui.DialogBox
     * @see bytebuddy.constants.Messages
     * @see javafx.fxml.FXML
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getByteBuddyDialog(START_MESSAGE, byteBuddyImage));
    }

    public void setByteBuddy(ByteBuddy bb) {
        byteBuddy = bb;
    }

    /**
     * Creates two dialog boxes, one echoing user input and
     * the other containing main.java.ByteBuddy's reply and
     * then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = byteBuddy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getByteBuddyDialog(response, byteBuddyImage)
        );
        userInput.clear();
        if (response.equals("\t " + BYE_MESSAGE)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.0));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
