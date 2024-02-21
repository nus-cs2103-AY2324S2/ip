package ghbot.javafx;

import java.util.Timer;
import java.util.TimerTask;

import ghbot.GhBot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private GhBot ghBot;

    private Timer timer = new Timer();

    private Image ghBotImage = new Image(this.getClass().getResourceAsStream("/images/bear.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sheep.png"));

    /**
     * Initializes the GUI for GhBot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            DialogBox.getGhBotDialog("Hi, I'm GhBot!\nWhat can I do for you?", ghBotImage)
        );
    }

    /**
     * Sets GhBot instance.
     * @param ghBot GhBot instance.
     */
    public void setGhBot(GhBot ghBot) {
        this.ghBot = ghBot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing GhBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ghBot.getResponse(input);
        if (input.equalsIgnoreCase("bye")) {
            timer.schedule(new ExitProgramTask(), 1500);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGhBotDialog(response, ghBotImage)
        );
        userInput.clear();
    }

    /**
     * ExitProgramTask Class.
     * An inner class to exit the program after 1.5sec to show the goodbye message.
     */
    private static class ExitProgramTask extends TimerTask {
        @Override
        public void run() {
            Platform.exit();
            System.exit(0);
        }
    }
}
