package judy;

import judy.commands.Command;
import judy.commands.GreetCommand;
import judy.exceptions.DukeException;
import judy.javafx.Launcher;
import judy.parser.Parser;
import judy.storage.Storage;
import judy.task.TaskList;
import judy.ui.Ui;

/**
 * Judy is the main class to run this program.
 *
 * @author Yeo Zi Yi (ziyi22)
 */
public class Judy {
    private static final String FILE_PATH = "judy.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private boolean isExit;

    /**
     * A constructor to initialize Judy with a specific file path
     */
    public Judy() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);

        // Load tasks from storage if file exists, else create a new file
        if (storage.isFileExists()) {
            this.taskList = new TaskList(storage.load());
        } else {
            storage.createNewFile();
            this.taskList = new TaskList();
            storage.save(taskList.getTaskList());
        }
    }

    /**
     * The main method to run Judy.
     * Initiates the user interface, greets the user, and handles user commands.
     */
    public void run() {
        ui.showLine();
        new GreetCommand().execute(storage, ui);
        ui.showLine();
        boolean isExit = false;

        // Process user commands
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Parser parser = new Parser(input, taskList);
                Command c = parser.parse();
                c.execute(storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the Judy application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }

    /**
     * Handle user input by parsing input and executing specific command.
     *
     * @param input A string that represent user input.
     * @return A string that to be response.
     */
    public String getResponse(String input) {
        Command c;
        String response = null;
        try {
            Parser parser = new Parser(input, taskList);
            c = parser.parse();
            response = c.execute(storage, ui);
            isExit = c.isExit();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Check if this Judy Application is exit.
     *
     * @return boolean indicating whether the app is exit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
