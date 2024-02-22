package dune;

import javafx.animation.PauseTransition;
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

    private Dune dune;

    private Image userPic = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dunePic = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Dune d) {
        this.dune = d;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // Get user input from text field
        String userText = userInput.getText();
        System.out.println(userText);

        // Process user input
        String duneText = getResponse(userText);
        System.out.println(duneText);


        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userPic),
                DialogBox.getDukeDialog(duneText, dunePic)
        );
        if (userText.equals("bye")) {
            // Create a PauseTransition to introduce a delay
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();

        }
        userInput.clear();
    }

    /**
     * Gets response from the chatbot given the user input.
     */
    private String getResponse(String input) {
        return this.dune.response(input);
    }
}

