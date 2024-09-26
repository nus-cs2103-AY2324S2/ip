package duke.tasks;

import java.time.format.DateTimeFormatter;

import duke.ui.Ui;


/**
 * Represents a task in the task list.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task object with the given description and completion status.
     *
     * @param description The description of the task.
     * @param status      The completion status of the task.
     */
    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon ("[X]" if the task is done, "[ ]" otherwise).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     *
     * @param ui The user interface object used to display messages.
     */
    public String markDone(Ui ui) {
        isDone = true;
        return "Nice! I've marked this task as done:\n" + this.toString();
    }

    /**
     * Marks the task as not done.
     *
     * @param ui The user interface object used to display messages.
     */
    public String unmark(Ui ui) {
        isDone = false;
        return "OK, I've marked this task as not done yet:\n" + this.toString();
    }

    /**
     * Displays the task information along with the total number of tasks in the list.
     *
     * @param size The total number of tasks in the list.
     */
    public String displayTask(int size) {
        return "Got it. I've added this task:\n" + this.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Searches for a keyword within the description of the task.
     *
     * @param keyword The keyword to search for within the description.
     * @return {@code true} if the keyword is found within the description, {@code false} otherwise.
     */
    public boolean search(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Converts the task to a string format suitable for storing in a file.
     *
     * @param storeFormatter The formatter used to format dates and times for storage.
     * @return A string representation of the task in the file format.
     */
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
