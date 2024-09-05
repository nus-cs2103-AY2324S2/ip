package javafxgui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lulu.Lulu;

/**
 * Controller for javafx.MainWindow. Provides the layout for the other controls.
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

    private Lulu lulu;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image luluImage = new Image(this.getClass().getResourceAsStream("/images/DaLulu.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLulu(Lulu d) {
        lulu = d;
    }

    /**
     * Handles the user's input in the chat application. Displays the user's input, Lulu's response, and updates the UI
     * accordingly. If the user enters "bye," schedules a program exit after 2 seconds.
     */
    @FXML
    public void handleUserInput() {
        String userText = userInput.getText().toLowerCase();
        String luluText = lulu.getResponse(userText);

        // Add user and Lulu dialog boxes to the dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getLuluDialog(luluText, luluImage)
        );

        // Clear the user input field
        userInput.clear();

        // Check if the user entered "bye" and schedule program exit after 3 seconds
        if (userText.equals("bye")) {
            exit();
        }
    }

    /**
     * Displays the initial greeting message from Lulu when the chat application starts.
     * The message includes a welcome greeting and an inquiry about how Lulu can assist the user.
     */
    public void displayStartMessage() {
        String greetingMessage = "Wassup boss! I'm Lulu \nHow can I assist you?";
        dialogContainer.getChildren().add(DialogBox.getLuluDialog(greetingMessage, luluImage));
    }

    /**
     * Schedules the program to exit after a 3-second delay. This method is typically called when the user inputs "bye."
     * Uses a ScheduledExecutorService to execute the Platform.exit() method after the specified delay.
     */
    public void exit() {
        // Schedule program exit after 2 seconds
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> Platform.exit(), 2, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
