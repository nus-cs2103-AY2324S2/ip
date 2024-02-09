package CinnamoRoll;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Cinnamo.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    /**
     * Initializes GUi with the greeting messages, displaying the images for users and the chatbot
     */
    // Code for greeting the user part was aided by chatgpt:
    // https://chat.openai.com/c/25edc1c9-5acb-422b-902a-05b3e9924510
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = "Hello! I'm CinnamoRoll!" + "\n" + "What can I do for you? \n";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greetingMessage, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    //Referred to ChatGPT code for exiting the program and added pausing function in order
    // to have more natural human-like conversation:
    // https://chat.openai.com/c/25edc1c9-5acb-422b-902a-05b3e9924510
    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            String exit = "See you again! Hope you had great time with me >.<";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(exit, dukeImage)
            );
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            pause.play();
        }
    }
}
