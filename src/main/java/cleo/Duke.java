package cleo;
import command.Command;
import java.util.ArrayList;


/**
 * Main application class for Duke.
 * <p>
 * This class is responsible for initializing the application,
 * loading data from storage, and starting the main program loop.
 */
public class Duke {

    private UI ui;
    private TaskList tasks;

    /**
     * Constructs a new instance of Duke application.
     * <p>
     * This constructor initializes the user interface and loads tasks from storage.
     */
    public Duke() throws DukeException {
        this.ui = new UI();
        try {
            ArrayList<Task> tasks = TaskStorage.loadTasks();
            System.out.println("Loaded tasks: " + tasks);
            this.tasks = new TaskList(tasks);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>()); // Initialize with an empty list in case of error
        }
    }

    /**
     * Starts the application and runs the main loop.
     * <p>
     * This method continually accepts user input and executes commands until the exit command is issued.
     */
    public void run() throws DukeException {
        ui.showWelcomeMessage();
        boolean isExit = false;
        String message = "";
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(tasks, fullCommand);
                message = c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                message = ui.showErrorMessage(e.getMessage());
            }
        }
        TaskStorage.saveTasks(tasks.getAllTasks());
        System.out.println("saved");
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }


    public String getResponse(String input) {
        try {
            Command c = Parser.parse(tasks, input);
            TaskStorage.saveTasks(tasks.getAllTasks());
            return c.execute(tasks, ui);
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

}
