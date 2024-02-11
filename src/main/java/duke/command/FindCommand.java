package duke.command;

import duke.TaskList;
import duke.task.Task;

/**
 * Find a task.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor.
     *
     * @param keyword The keyword of the task to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks) {
        TaskList tasksWithKeyword = new TaskList();
        for (Task task : tasks) {
            if (task.contains(this.keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return "Here are the matching tasks in your list:\n" + tasksWithKeyword + "\n";
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof FindCommand)
                && (this.keyword.equals(((FindCommand) other).keyword));
    }
}
