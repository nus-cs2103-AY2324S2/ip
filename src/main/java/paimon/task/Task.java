package paimon.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task within the application. This abstract class provides
 * a foundation for different types of tasks (e.g., Todo, Deadline, Event) and defines
 * common properties and methods that are shared among all task types.
 */
public abstract class Task {
    /**
     * The date and time format used for representing task deadlines or event timings.
     */
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task. True if the task is completed, false otherwise.
     */
    protected boolean isDone;


    /**
     * Constructs a new Task instance with the specified description. By default, the task
     * is not completed (isDone is false).
     *
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for display purposes. The specific format
     * of the string depends on the type of task (implemented in subclasses).
     *
     * @return A string representing the task.
     */
    public abstract String getTask();

    /**
     * Returns a string representation of the task suitable for storage in a file. This typically
     * includes the task type, status, description, and any dates/times formatted as needed.
     *
     * @return A string suitable for file storage that encapsulates the task's details.
     */
    public abstract String toFileString();

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The new completion status of the task; true if the task is completed, false otherwise.
     */
    public void setTaskState(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

}
