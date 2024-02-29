package damon.gui;

import damon.Damon;
import damon.exceptions.NoDescriptionException;
import damon.exceptions.WrongInputException;
import damon.response.Response;
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

import java.io.IOException;

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

    private Damon damon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gorrilaz.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Damon.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Damon d) {
        damon = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Response().getWelcomeMessage(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        damon.run(input);
        assert !damon.getResponse().equals(null);
        String response = damon.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));

        userInput.clear();

        //Solution below adapted from https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        if (damon.getIsExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished( event -> Platform.exit() );
            delay.play();
        }
    }
}



