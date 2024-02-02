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
}
