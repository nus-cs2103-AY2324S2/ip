package judy;

import judy.commands.Command;
import judy.commands.GreetCommand;
import judy.exceptions.DukeException;
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

    /**
     * A constructor to initialize Judy with a specific file path.
     *
     * @param filePath The path of the storage where tasks are stored.
     */
    public Judy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

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
     * Creates an instance of Judy and runs it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Judy(FILE_PATH).run();
    }
}
