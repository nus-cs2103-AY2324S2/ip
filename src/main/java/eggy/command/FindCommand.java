package eggy.command;

import eggy.exception.IncompleteTaskException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Task;
import eggy.ui.Ui;

import java.util.List;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    /** The keyword to search for. */
    private String keyword;

    /**
     * Constructs a FindCommand.
     *
     * @param commands The array of commands.
     * @throws IncompleteTaskException If the task description is incomplete.
     */
    public FindCommand(String[] commands) throws IncompleteTaskException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("find");
        }
        this.keyword = commands[1];
    }

    /**
     * Finds tasks in the task list that match the keyword and prints them.
     *
     * @param tasks The task list of the chatbot.
     * @param ui The user interface of the chatbot.
     * @param storage The storage of the chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findMatchingTasks(this.keyword);
        ui.printMatchingTasks(matchingTasks);
    }
}