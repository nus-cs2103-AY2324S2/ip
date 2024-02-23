package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * List of tasks entry by user
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Finds all the tasks containing the keyword.
     *
     * @param tasks the task list
     * @param keyword the keyword for searching
     * @return all the matching tasks
     */
    public TaskList findTasks(TaskList tasks, String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
