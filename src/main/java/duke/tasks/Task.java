package duke.tasks;

/**
 * The Task class represents a general task with a description and completion status.
 * <p>
 * A task can be marked as done or not done, and it includes a description and a done icon.
 * </p>
 * <p>
 * This class serves as the base class for specific types of tasks such as deadline tasks and event tasks.
 * </p>
 * <p>
 * Tasks can be marked as done or not done, and their completion status can be updated accordingly.
 * </p>
 * <p>
 * The class provides methods to retrieve the task description, done icon, and to update the completion status.
 * </p>
 * <p>
 * Note: The done icon is represented as "[ ]" for not done and "[X]" for done.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String doneIcon;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.doneIcon = "[ ]";
    }

    /**
     * Updates the done icon based on the completion status of the task.
     */
    public void updateDoneIcon() {
        if (this.isDone) {
            this.doneIcon = "[X]";
        } else {
            this.doneIcon = "[ ]";
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the done icon representing the completion status of the task.
     *
     * @return the done icon of the task.
     */
    public String getDoneIcon() {
        return this.doneIcon;
    }

    /**
     * Marks the task as done and updates its completion status and done icon.
     */
    public void markDone() {
        this.isDone = true;
        updateDoneIcon();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(this);
    }

    /**
     * Marks the task as not done and updates its completion status and done icon.
     */
    public void markNotDone() {
        this.isDone = false;
        updateDoneIcon();
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(this);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return getDoneIcon() + " | " + getDescription();
    }
}
