package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.Task;

import java.util.ArrayList;

/**
 * Represents a command to find and list all tasks that contain a specified keyword.
 * This command extends the abstract Command class.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the given TaskList, Ui, Storage, and search keyword.
     *
     * @param tasks   The TaskList to search within.
     * @param ui      The Ui instance for user interaction.
     * @param storage The Storage instance associated with the command (not directly used).
     * @param keyword The keyword to search for within task descriptions.
     */
    public FindCommand(TaskList tasks, Ui ui, Storage storage, String keyword) {
        super(tasks, ui, storage);
        this.keyword = keyword;
    }

    /**
     * Executes the find command. Searches the TaskList for tasks that contain the keyword
     * and displays the matching tasks to the user using the Ui.
     */
    @Override
    public String execute() {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }
}
