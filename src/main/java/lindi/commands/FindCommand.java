package lindi.commands;

import lindi.storage.Storage;
import lindi.task.TaskList;

/**
 * Represents a command that filters out tasks in the task list upon execution
 */
public class FindCommand extends Command {
    public static final String COMMAND_FIND = "find";
    private final String keyword;

    /**
     * Creates command to find a task by keyword
     *
     * @param keyword keyword to look for in task description
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Loads and filters the tasks in task list, matching this.keyword, into status msg to be printed.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        TaskList tasksToDisplay = new TaskList();
        tasks.getTasks().forEachRemaining(
                task -> {
                    if (task.getDescription().contains(this.keyword)) {
                        tasksToDisplay.add(task);
                    }
                }
        );
        this.statusMsg = tasksToDisplay.size() == 0
                ? "There are no tasks containing that search key!" // Replaces default no tasks message in TaskList
                : tasksToDisplay.toString();
    }
}
