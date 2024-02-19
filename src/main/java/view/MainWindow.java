package view;

import java.io.IOException;

import controller.Zero;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

//@@author jeremiahlzz-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
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

    private Zero zero;

    // Images free from: https://www.irasutoya.com/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gal_o_man.png"));
    private Image zeroImage = new Image(this.getClass().getResourceAsStream("/images/otaku_girl_fashion.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setZero(Zero zero) {
        this.zero = zero;
    }

    public void showGreet(String s) {
        dialogContainer.getChildren().add(DialogBox.getZeroDialog(s, zeroImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = zero.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZeroDialog(response, zeroImage)
        );
        if (response.equals(Ui.showBye())) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            // Close the application after some time
            FadeTransition fadeToExit = new FadeTransition(Duration.seconds(2.5), dialogContainer);
            fadeToExit.setToValue(0.2);
            fadeToExit.setOnFinished(event -> Platform.exit());
            fadeToExit.play();
        }
        userInput.clear();
    }
}
//@@author
