package iggly;

import iggly.command.Command;
import iggly.command.ExitCommand;
import iggly.duke.DukeException;
import iggly.duke.Parser;
import iggly.duke.Storage;
import iggly.javafx.Launcher;
import iggly.model.TaskList;

/**
 * {@link Duke} is the main class of this program.
 *
 * @author Tsui Yi Wern (yiwern5)
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private boolean isExit;

    /**
     * Constructor for {@link Duke} to initialize the storage or
     * get task list from the storage.
     *
     * @param filePath the file path of the storage.
     */
    public Duke(String filePath) {
        assert filePath != null && !filePath.isEmpty();
        this.storage = new Storage(filePath);
        this.isExit = false;
        if (storage.isExistingFile()) {
            this.taskList = new TaskList(storage.load());
        } else {
            storage.createNewFile();
            this.taskList = new TaskList();
            storage.updateFile(taskList.getTaskList());
        }
    }

    /**
     * Starts the application.
     *
     * @param args the arguments passed into the application.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }

    /**
     * Generates a response based on the user input. Processes the input by checking for specific commands,
     * parsing the input using a parser, and executing the corresponding command.
     *
     * @param input The user input to be processed.
     * @return A string representing the response generated based on the input.
     */
    public String getResponse(String input) {
        String response = null;
        if (input.equals(ExitCommand.COMMAND_WORD)) {
            response = new ExitCommand().execute(storage);
            this.isExit = true;
        } else {
            try {
                Command c = new Parser(input, taskList).parse();
                response = c.execute(storage);
            } catch (DukeException e) {
                assert e.getMessage() != null && !e.getMessage().isEmpty();
                response = e.getMessage();
            }
        }
        return response;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
