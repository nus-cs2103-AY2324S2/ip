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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private static Ui ui;
    private static ArrayList<Task> tasks;
    /**
     * The main method of the application. This runs the required
     * methods to start the application.
     *
     * @param args
     * @throws IOException if the file path is not found.
     */
    public static void main(String[] args) throws IOException {
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

    public Duke() throws FileNotFoundException {
        ui = new Ui();
        tasks = Storage.init();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String[] strArrCommand = ui.readCommand(input);
        String response;
        try {
            Command command = ParseCommand.parse(strArrCommand);
            command.execute(tasks, strArrCommand);
            response = command.getCommandResponse();
        } catch (CommandException | IOException e) {
            return Ui.printOutput(e.getMessage());
        }
        return response;
    }
}
