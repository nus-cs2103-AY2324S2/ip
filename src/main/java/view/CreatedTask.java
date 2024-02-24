package view;

import objects.Task;
import objects.TaskList;

/**
 * The CreatedTask class provides a method to display information about a newly created task.
 */
public class CreatedTask extends UI {

    /**
     * Displays information about a newly created task, including the task details and the total number of tasks.
     *
     * @param tasks The TaskList containing the tasks.
     * @param task  The newly created task.
     * @return
     */
    public static String display(TaskList tasks, Task task) {
        return  String.format("I've added the task :D:\n  %s\nYou now have %d tasks.", task, tasks.size());
    }
}
