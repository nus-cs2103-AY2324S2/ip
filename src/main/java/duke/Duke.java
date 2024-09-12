package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.CommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.utils.Parser;

/**
 * Main chatbot program.
 *
 * @author KohGuanZeh
 */
public class Duke {
    // File directory of stored data.
    private static final String FILE_DIRECTORY = "data";
    // File name of stored data.
    private static final String FILE_NAME = "tasks.txt";

    private Storage storage;
    private TaskList taskList;

    /**
     * Initializes a new Duke instance.
     * Sets up necessary components for chatbot interaction.
     *
     * @throws IOException Exception when IO errors are encountered in initializing save file.
     */
    public Duke() throws IOException {
        this.taskList = new TaskList();
        this.storage = new Storage(Duke.FILE_DIRECTORY, Duke.FILE_NAME);
    }

    /**
     * Returns greeting message to be presenting on chatbot start up.
     *
     * @return Greeting message from chatbot.
     */
    public String getGreeting() {
        return "This is Duke Zeh. What can I do for you today?";
    }

    /**
     * Loads TaskList from directed save file and returns status of tasks.
     * Status refers to how many tasks were loaded successfully and unsuccessfully.
     * If IOException is encountered, feedback to restart the program will be given.
     *
     * @return Status message of tasks loaded.
     */
    public String getLoadStatus() {
        try {
            int failedCount = taskList.loadTasks(this.storage.load());
            if (failedCount > 0) {
                return "Loaded tasks from previous session. Failed to load " + failedCount
                        + " tasks from improper format.";
            } else {
                return "Loaded all tasks from previous session successfully.";
            }
        } catch (IOException e) {
            return "Error loading tasks from save file. Please restart...";
        }
    }

    /**
     * Returns feedback message based on user command.
     *
     * @param input Input command and parameters for Duke to process.
     * @return Message on command execution.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseInput(input);
            return command.run(this.taskList, this.storage);
        } catch (CommandException | TaskListException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "An error has occurred with the save file. Please restart.";
        }
    }
}
