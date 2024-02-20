package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.Random;

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

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user2.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/sophia.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        printDefaultMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void printDefaultMessage() {
        // Array of welcome messages
        String[] welcomeMessages = {
                "Hey there! I'm Sophia 😊\nReady to get things done today?",
                "Good day! Sophia at your service. 🌟\nHow can I assist you?",
                "Hello! 🎉 I'm Sophia, your friendly AI sidekick!\nLet's tackle your to-do list together.",
                "Hi! I'm Sophia, your digital helper. 🤖\nTell me, what's on the agenda today?",
                "Yo! Sophia here. 😎\nReady to crush some tasks? Let's dive in!"
        };

        Random rand = new Random();
        String defaultMessage = welcomeMessages[rand.nextInt(welcomeMessages.length)];

        dialogContainer.getChildren().add(DukeDialogBox.getDukeDialog(defaultMessage, dukeImage));
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                DukeDialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}
