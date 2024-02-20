package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * A Task object, which is a abstract class of ToDo, Event, Deadline classes.
 */
public class Task {
    public static DateTimeFormatter dateTimeString = DateTimeFormatter.ofPattern(
            "MMM dd yyyy hh:mma"); // datetime formatter for toString() methods

    private String taskName;
    private boolean status;

    /**
     * Constructor for common variables
     * @param taskName
     * @param status
     */
    public Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Return the string representation of this task's status,
     * @return [X] or [ ]
     */
    public String getStatusIcon() {
        return status ? "[X]" : "[ ]";
    }

    public boolean isDone() {
        return status;
    }

    /**
     * Set status as true. (i.e. done task)
     */
    public void done() {
        status = true;
    }

    /**
     * Set status as false. (i.e. undone task)
     */
    public void undone() {
        status = false;
    }

    /**
     * Return the string representation of task
     * @return [<status>] <task_name>
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + taskName;
    }

    /**
     * Return the string representation of a task to be stored in local file,
     * @return ,<status (true/false)>,<task_name>
     */
    public String toStore() {
        return "," + status +  "," + taskName;
    }
}
