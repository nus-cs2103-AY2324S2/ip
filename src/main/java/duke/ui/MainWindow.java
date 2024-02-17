package duke.ui;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.command.ByeCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that acts as controller for MainWindow.
 * Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
    // @@author KohGuanZeh-reused
    // Source: https://se-education.org/guides/tutorials/javaFxPart4.html
    private static final Image USER_IMAGE = new Image(Duke.class.getResourceAsStream("/images/user.png"));
    private static final Image DUKE_IMAGE = new Image(Duke.class.getResourceAsStream("/images/dukezeh.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    /**
     * Initializes MainWindow components before showing.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Stores instance of Duke program.
     *
     * @param duke Duke program instance.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Displays task loading status and greets user on start.
     * Should only display once when MainWindow first opens.
     */
    public void onStartUp() {
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(this.duke.getLoadStatus(), MainWindow.DUKE_IMAGE),
                DialogBox.getDukeDialog(this.duke.getGreeting(), MainWindow.DUKE_IMAGE));
    }

    /**
     * Creates response dialog boxes based on user input.
     * First DialogBox belongs to user, and second DialogBox contains Duke's response.
     * DialogBoxes are then appended to the MainWindow.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = duke.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, MainWindow.USER_IMAGE),
                DialogBox.getDukeDialog(response, MainWindow.DUKE_IMAGE)
        );
        this.userInput.clear();
        if (response.equals(ByeCommand.BYE_MESSAGE)) {
            waitAndExit(1000);
        }
    }
    // @@author
    /**
     * Closes program after the bye command.
     * Sleeps for specified milliseconds before closing the stage.
     * During this time, userInput and sendButton will be disabled.
     *
     * @param millisDelay Delay before closing in milliseconds.
     */
    private void waitAndExit(long millisDelay) {
        this.userInput.setDisable(true);
        this.sendButton.setDisable(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                Platform.exit();
            }
        }, millisDelay);
    }
}
