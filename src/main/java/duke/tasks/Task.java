package duke.tasks;

import duke.ui.Ui;

import java.time.format.DateTimeFormatter;

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
    public void markDone(Ui ui) {
        isDone = true;
        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        ui.showLine();
    }

    /**
     * Marks the task as not done.
     *
     * @param ui The user interface object used to display messages.
     */
    public void unmark(Ui ui) {
        isDone = false;
        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        ui.showLine();
    }

    /**
     * Displays the task information along with the total number of tasks in the list.
     *
     * @param size The total number of tasks in the list.
     */
    public void displayTask(int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
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
