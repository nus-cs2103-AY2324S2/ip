package dylanbot.controllers;

import java.util.concurrent.atomic.AtomicInteger;

import dylanbot.DylanBot;
import dylanbot.DylanBotException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private DylanBot dylanBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DylanBotUser.jpeg"));
    private Image dylanBotImage = new Image(this.getClass().getResourceAsStream("/images/DylanBotBot.jpeg"));

    /**
     * Initializes the MainWindow with the scrollPane and dialogContainer
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setDylanBot(DylanBot d) throws DylanBotException {
        dylanBot = d;
        String greeting = "Hello! I'm DylanBot. How can I assist you?";
        dialogContainer.getChildren().add(DialogBox.getDylanBotDialog(greeting, dylanBotImage));
        String helpMessage = "Try using some of these commands to get started:"
                + "\n\t1. todo <description>" + "\n\t2. deadline <description> /by <date>"
                + "\n\t3. event <description> /from <start> /to <end>" + "\n\t4. list" + "\n\t5. mark/unmark <index>"
                + "\n\t6. delete <index>" + "\n\t7. find <keyword>" + "\n\t8. tag <index> <tag>" + "\n\t9. filter <tag>"
                + "\n\t10. bye";
        dialogContainer.getChildren().add(DialogBox.getDylanBotDialog(helpMessage, dylanBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DylanBotException {
        String input = userInput.getText();
        String response = dylanBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDylanBotDialog(response, dylanBotImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            String exitMessage = "Exiting in 5 seconds...";
            dialogContainer.getChildren().add(DialogBox.getDylanBotDialog(exitMessage, dylanBotImage));

            AtomicInteger countdownSeconds = new AtomicInteger(5);
            Label countdownLabel = new Label(String.valueOf(countdownSeconds.get()));
            Timeline countdownTimeline = getCountdownTimeline(countdownSeconds, countdownLabel);
            countdownTimeline.setCycleCount(countdownSeconds.get());
            countdownTimeline.play();
        }
    }

    private Timeline getCountdownTimeline(AtomicInteger countdownSeconds, Label countdownLabel) {
        String countdownTimer = "Exiting in: ";

        return new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds.getAndDecrement();
                    countdownLabel.setText(countdownTimer + String.valueOf(countdownSeconds.get()));
                    dialogContainer.getChildren().add(DialogBox.getDylanBotDialog(
                            countdownTimer + countdownSeconds.get(), dylanBotImage));
                    if (countdownSeconds.get() == 0) {
                        Platform.exit();
                    }
                })
        );
    }
}
