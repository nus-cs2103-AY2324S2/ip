package ghbot;

import java.io.IOException;
import java.time.DateTimeException;

import ghbot.exception.UiException;
import ghbot.executor.ByeExecutor;
import ghbot.executor.Executor;
import ghbot.parser.Parser;
import ghbot.storage.Storage;
import ghbot.task.TaskList;
import ghbot.ui.Ui;

/**
 * GhBot Class.
 * This is the main class for the chatbot.
 */
public class GhBot {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * GhBot Constructor.
     * @param filename The name of the file.
     */
    public GhBot(String filename) {
        this.storage = new Storage(filename);
        this.taskList = new TaskList(storage.getInputFromFile());
    }

    /**
     * Returns the response with respect to the user input.
     * @param input A string containing the user input.
     * @return A string containing the respective response of the given input.
     */
    public String getResponse(String input) {
        this.ui = new Ui(input);
        try {
            String[] subStr = this.ui.validateInput();
            Executor executor = Parser.parse(subStr);

            if (executor instanceof ByeExecutor) {
                this.storage.writeDataToFile(taskList.toList());
                return executor.execute();
            }

            if (executor != null) {
                executor.set(this.taskList);
                return executor.execute();
            }
        } catch (UiException | IOException | DateTimeException e) {
            return e.getMessage();
        }
        return "";
    }
}
