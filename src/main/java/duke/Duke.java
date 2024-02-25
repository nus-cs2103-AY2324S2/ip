package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import duke.command.Command;
import duke.utility.DukeException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.task.Task;

/**
 * Class that represents the Duke Chatbot.
 */
public class Duke {
    /** TaskList Object to be used to store Tasks. */
    private TaskList taskList;
    /** Ui Object for User Interactions. */
    private Ui userInterface;
    /** Storage Object to store and load Tasklist states. */
    private Storage fileStorage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/PenguUser.png"));
    private Image pengu = new Image(this.getClass().getResourceAsStream("/images/PenguBot.png"));

    /**
     * Constucts a Duke Object that will be loaded with existing TaskList state.
     *
     * @throws DukeException
     * @throws IOException
     */
    public Duke() throws DukeException, IOException {
        this.userInterface = new Ui();
        this.fileStorage = new Storage("./data/tasks.txt");
        userInterface.showWelcome();
        if (this.fileStorage.isOccupied) {
            ArrayList<Task> loadedList = fileStorage.loadStorage();
            taskList = new TaskList(loadedList);
        } else {
            taskList = new TaskList();
        }
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parseInstructions(input);
            return c.execute(taskList, userInterface, fileStorage);
        } catch (DukeException e) {
            return userInterface.showError(e.getMessage());
        }
    }
}
