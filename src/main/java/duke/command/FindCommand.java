package duke.command;

import java.util.NoSuchElementException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to find all tasks with a matching keyword in the current task list.
 * This class extends the Command class and overrides the execute method to print a list of tasks.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructs FindCommand object storing specified keyword to be searched when executed.
     *
     * @param keyword a String representing a keyword to be matched with the task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches task list for case-insensitive matches with the specified keyword.
     *
     * @param tasks The TaskList object on which the command will operate
     * @param storage The Storage object that will read and write to files
     * @param ui The Ui object that handles displaying messages
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        super.tasks = tasks;
        super.storage = storage;
        super.ui = ui;
        try {
            return super.ui.displayList("Land ho! I've spied these tasks over yonder!",
                    super.tasks.findTasks(this.keyword));
        } catch (NoSuchElementException e) {
            return super.ui.printMessage(e.getMessage());
        }
    }
}
