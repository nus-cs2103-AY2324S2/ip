import commands.Command;
import commands.Parser;
import exceptions.DukeException;
import javafx.application.Application;
import main.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Duke is the main class for the task management application.
 * It initializes the application and starts the interaction loop with the user.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Duke object.
     * Initializes the UI, storage, and task list components of the application.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        assert userInput != null;
        try {
            Command c = Parser.parse(userInput);
            assert c != null;
            return c.execute(taskList, ui, storage, userInput);
        } catch (DukeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
