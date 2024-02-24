package axolotl;

import axolotl.command.Command;
import axolotl.command.CommandResult;
import axolotl.parser.Parser;
import axolotl.storage.Storage;
import axolotl.task.TaskList;
import axolotl.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Axolotl {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // For JavaFX
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image axolotl = new Image(this.getClass().getResourceAsStream("/images/DaAxolotl.png"));

    public Axolotl() {
        storage = new Storage();
    }

    /**
     * Executes commands and displays output message
     * @param command
     * @return
     */
    public CommandResult executeCommand(Command command) {
        command.configureStorageFile(storage);
        return command.execute();
    }

    /**
     * Gets the response of the user and parses it as command.
     * Command is then executed and returns result
     */
    public String getResponse(String input) {
        Command command = new Parser().parse(input);
        CommandResult commandResult = executeCommand(command);
        return commandResult.toString();
    }

}
