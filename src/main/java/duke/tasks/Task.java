package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.TaskList;
import duke.exceptions.InvalidMarkException;

/**
 * The Task class represents a generic task.
 */
public abstract class Task {
    private boolean isDone;
    private String desc;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        this.isDone = false;
        this.desc = desc;
    }

    /**
     * Constructs a Task object with the specified description and completion status.
     *
     * @param desc   the description of the task
     * @param isDone the completion status of the task ("1" for done, "0" for not done)
     */
    public Task(String desc, String isDone) {
        this.desc = desc;
        if (isDone.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Converts a LocalDateTime object to a string with the format "MMM dd yyyy HH:mm".
     *
     * @param dateTime the LocalDateTime object to be converted
     * @return a string representation of the LocalDateTime object in the specified format
     */
    public static String toStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Returns whether the task's description contains the keyword.
     *
     * @return true if the task's description contains the keyword, false otherwise
     */
    public boolean containsKeyword(String keyword) {
        if (this.desc.contains(keyword)) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns the index of the task in the current TaskList.
     *
     * @return the index of the task in the current TaskList
     */
    public int getIndex(TaskList tasks) {
        return tasks.getIndex(this);
    }


    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task
     */
    public abstract String getStatusIcon();

    /**
     * Updates the task to retrieve relevant information, only relevant for DoAfterTask.
     */
    public void update(TaskList tasks) {}

    /**
     * Marks the task as done.
     */
    public void markAsDone() throws InvalidMarkException {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    public abstract String toString();

    /**
     * Returns a string in a standardised format to represent the task for saving to file.
     *
     * @param tasks the param is not used in this method
     * @return a string in a standardised format to represent the task for saving to file
     */
    public abstract String save(TaskList tasks);
}
