import duke.command.Command;
import duke.command.CommandType;
import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.geometry.Insets;
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


/**
 * Duke class
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/corgi.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/golden_retriever.jpg"));

    /**
     * Constructor of Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(CommandType.FILEPATH.toString());
        this.storage.getStorageFromHardDisk(this.tasks);
    }


    /**
     * Starts communication with chatbot
     */
    public void startChat() {
        ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();

            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public String getResponse(String userInput) {
        assert userInput != null;
        Command command = Parser.parse(userInput);
        return command.getExecuteMessage(tasks, ui, storage);
    }
}
