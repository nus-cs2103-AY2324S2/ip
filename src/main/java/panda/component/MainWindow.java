package panda.component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import panda.Panda;
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

    private Panda panda;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image pandaImage = new Image(this.getClass().getResourceAsStream("/images/DaPanda.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPanda(Panda p) {
        panda = p;
        dialogContainer.getChildren().add(DialogBox.getPandaDialog(panda.welcomeMessage(), pandaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = panda.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPandaDialog(response, pandaImage)
        );
        userInput.clear();

        // Request layout to update the VBox's layout
        dialogContainer.requestLayout();
        if(panda.isExit) {
            // Create a PauseTransition for a  3-second delay
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            
            // Set an action to be executed after the pause
            pause.setOnFinished(event -> {
                // Ensure the UI update is performed on the JavaFX Application Thread
                Platform.runLater(() -> {
                    // Close the application window
                    Platform.exit();
                });
            });
            
            // Start the pause
            pause.play();
        }

        // Scroll to the bottom of the ScrollPane
        // scrollPane.setVvalue(1.0);
    }
}

