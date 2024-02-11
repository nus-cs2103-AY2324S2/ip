package duke.commands;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute FindCommand
 */
public class FindCommand extends Command {
    private String[] words;
    /**
     * Constructs a FindCommand object with the given array of command words.
     *
     * @param words The array of strings containing the command and its arguments.
     */
    public FindCommand(String[] words) {
        super();
        this.words = words;
    }
    /**
     * Executes the find command, searching for tasks containing the specified keyword.
     *
     * @param tasks The TaskList containing the list of tasks.
     * @param ui The UI object for displaying messages.
     * @param storage The Storage object for saving data to a file.
     * @return False indicating that the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) {
        ui.displayFoundTask(tasks.findTasksWithString(words[1].trim()));
        return false;
    }
}
