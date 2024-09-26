package duke.gui;

import duke.Duke;
import duke.commands.Command;
import duke.commands.ExitProgram;
import duke.exceptions.StorageException;
import duke.fileUtils.FileUtil;
import duke.fileUtils.FilePaths;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;



    @FXML
    public void initialize() {
        String greetingMsg = "Ayyo whatsup bro. I'm RahhBot, your unpaid PA. Type Help for guides!";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingMsg));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() throws StorageException {
        String input = userInput.getText();
        String response;
        if (input.equalsIgnoreCase("bye")) {
            response = exitBot();
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response));
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        } else {
            response = duke.getResponse(input);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response)
        );
        userInput.clear();
    }

    private String exitBot() {
        return "Bye! ＼( ･_･)";
    }

}
