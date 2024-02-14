package duke;

import duke.commands.Command;
import duke.commands.CommandException;
import duke.commands.ParseCommand;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.DialogBox;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * The main method of the application. This runs the required
     * methods to start the application.
     *
     * @param args
     * @throws IOException if the file path is not found.
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Task> tasks = Storage.init();
        Ui ui = new Ui();
        ui.printWelcomeMsg();
        while (true) {
            try {
                String[] strArrCommand = ui.readCommand();
                Command command = ParseCommand.parse(strArrCommand);
                command.execute(tasks, strArrCommand);
            } catch (CommandException | IOException e) {
                Ui.printOutput(e.getMessage());
            }
        }
    }

    public Duke() {

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
