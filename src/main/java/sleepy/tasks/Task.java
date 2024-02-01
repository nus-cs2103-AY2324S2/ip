package sleepy.tasks;

import sleepy.tools.Ui;

/**
 * This class is an abstract class for the tasks in the list.
 *
 * @author kjw142857
 */
public abstract class Task {
    // Description of the task (without timings).
    private String description;

    // Raw description of the task with all details included.
    private String rawDescription;
    private boolean isDone = false;

    public Task(String rawDescription, String description) {
        this.rawDescription = rawDescription;
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
        Ui.printLine("Nice! I've marked this task as done:");
        Ui.printLine(this.getDescription());
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        isDone = false;
        Ui.printLine("OK, I've marked this task as not done yet:");
        Ui.printLine(this.getDescription());
    }

    /**
     * Returns the description of this task.
     *
     * @return Description of this task.
     */
    public String getDescription() {
        if (isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }

    /**
     * Returns the raw description of this task.
     *
     * @return Raw description of this task.
     */
    public String getRawDescription() {
        return this.rawDescription;
    }
}