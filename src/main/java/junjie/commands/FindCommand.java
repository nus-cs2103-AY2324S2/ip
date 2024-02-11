package junjie.commands;

import junjie.TaskList;
import junjie.Ui;
import junjie.tasks.Task;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    /**
     * The command word to be matched with the user input.
     */
    public static final String COMMAND_WORD = "find";
    private static final String MESSAGE = "found for u liao:\n%s";

    private final String keyword;

    /**
     * Constructs a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList filteredTasks = new TaskList("");

        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        ui.print(String.format(MESSAGE, filteredTasks.toString()));
    }
}
