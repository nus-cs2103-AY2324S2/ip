package jade;

import jade.commands.Command;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.parser.Parser;
import jade.storage.Storage;
import jade.ui.Ui;

/**
 * A <code>Jade</code> object is a personal chatbot assistant that
 * helps users with task management with the option of adding dates available.
 *
 * @author Feiyang Shang
 * @version v1.0
 * @since 2024-01-23
 */
public class Jade {
    private static final String USER_TASKS_FILE_PATH = "data/jadeList.txt";
    private TaskList taskList; // list that stores all user tasks
    private final Storage storage; // storage object to load from and save to local file
    private boolean shouldExit;
    /**
     * Class constructor.
     */
    public Jade() {
        this.taskList = new TaskList();
        this.storage = new Storage(USER_TASKS_FILE_PATH);
        this.shouldExit = false;
        try {
            this.taskList = new TaskList(storage.load());
        } catch (JadeException e) {
            System.out.print(Ui.LOADING_ERROR_MESSAGE);
            this.taskList = new TaskList();
        }
    }
    /**
     * Generates a response from user input.
     * @param input The user input.
     * @return The response message by executing user command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.shouldExit = c.isExit();
            return c.execute(taskList, storage);
        } catch (JadeException e) {
            return e.getMessage();
        }
    }
    public boolean shouldExit() {
        return this.shouldExit;
    }
}
