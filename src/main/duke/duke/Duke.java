package duke.duke;

import duke.tasks.TaskList;
import duke.ui.Skibidi;

import java.io.IOException;

/**
 * Class that runs the chatbot.
 *
 * @author Lim Zi Jia
 */
public class Duke {
    /** The storage object that is used in Duke. */
    private static Storage storage;

    /** A list that contains all your tasks. */
    private static TaskList tasks = new TaskList();

    /** The class that prints to screen and chat. */
    private Skibidi skibidi = new Skibidi();

    /** The parser that understands the input and generates the reply. */
    private Parser parser;

    public Duke() {}

    /**
     * Creates a Duke object that is capable of running a chatbot.
     *
     * @param filePath The path of the saved data file.
     * @param fileName The name of the saved data file.
     */
    public Duke(String filePath, String fileName) {
        storage = new Storage(filePath, fileName);
        parser = new Parser();
    }

    /**
     * Takes in a String input and outputs the corresponding output.
     * @param input User's String input.
     * @return The reply from the bot to the user.
     */
    public String getResponse(String input) {
        String response = parser.parse(input);
        return skibidi.formatOutput(response);
    }

    public Storage getStorage() {
        return Duke.storage;
    }

    public TaskList getTaskList() {
        return Duke.tasks;
    }

    public void setStorage(Storage storage) {
        Duke.storage = storage;
    }

    public void setTaskList(TaskList tl) {
        Duke.tasks = tl;
    }
}
