package dook;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import task.TaskList;

/**
 * Dook class that represents the bot.
 */
public class Dook {

    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "dook.txt"));
    private TaskList tasks;
    private Storage storage;

    /**
     * Dook constructor.
     */
    public Dook() {
        this.tasks = new TaskList();
        this.storage = new Storage(FILE_PATH);
        this.storage.checkFile();
    }

    /**
     * Parses the input, executes the input as a command.
     * @param input the command input
     * @return the output of the command
     */
    public String getResponse(String input) {
        try {
            this.tasks = this.storage.loadTaskListFromFile();
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
