package duke.command;

import java.util.List;

import duke.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        super("find", List.of(keyword));
        this.keyword = keyword;
    }

    @Override
    public TaskList execute(TaskList tasks) {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(matchingTasks);
        return tasks;
    }
}
