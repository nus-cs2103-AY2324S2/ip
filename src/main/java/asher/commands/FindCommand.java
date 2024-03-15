package asher.commands;

import asher.BotException;
import asher.tasks.TaskList;
import asher.tasks.Task;
import asher.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructs a Find Command object with the given input string.
     *
     * @param input The input string for the Find Command.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the Find Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for Find Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            if (input.length() <= 5) {
                throw new BotException("Keyword not specified!");
            }
            String keyword = input.substring(5).trim();
            ArrayList<Task> matchingTasks = taskList.searchKeyword(keyword);
            return ui.showMatchingTasks(matchingTasks);
        } catch (Exception e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}