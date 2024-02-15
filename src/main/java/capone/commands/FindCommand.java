package capone.commands;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.exceptions.InvalidCommandException;
import capone.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 * Inherits from the Command class.
 *
 * @author Tay Rui-Jie
 */
public class FindCommand extends Command {
    /**
     * Constant string that provides information to the user on how to use the command.
     */
    private static final String USAGE_STRING = "Usage: find [keyword]";

    /** The list of input strings provided by the user for the find command. */
    private final ArrayList<String> inputList;

    /**
     * Constructs a FindCommand instance with the specified input list.
     *
     * @param inputList The list of input strings provided by the user.
     */
    public FindCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the find command, searching for tasks containing a specific keyword.
     *
     * @param taskList The list of tasks to search within.
     * @param ui The user interface for displaying messages.
     * @param storage The storage to handle data storage operations.
     * @return The String output of the bot after executing the user's command.
     * @throws CaponeException If there is an issue executing the find command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (this.inputList.size() == 1) {
            throw new InsufficientArgumentException("Insufficient arguments!\n"
                    + FindCommand.USAGE_STRING);
        } else if (this.inputList.size() > 2) {
            throw new InvalidCommandException("Too many arguments entered - Please only enter one keyword:\n"
                    + FindCommand.USAGE_STRING);
        }

        // The second word entered by the user is the keyword to be searched.
        String keyword = inputList.get(1);

        TaskList filteredList = new TaskList();

        StreamSupport.stream(taskList.spliterator(), false)
                .filter(task -> task.getDescription().contains(keyword))
                .forEach(filteredList::addTask);

        if (filteredList.isEmpty()) {
            return ui.sendNoResults(keyword);
        } else {
            return new ListCommand().execute(filteredList, ui, storage);
        }
    }
}
