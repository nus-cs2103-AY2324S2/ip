package tasks;

/**
 * Represents a generic task in a task management application.
 * This class serves as a base class for different types of tasks.
 *
 * @author Muhammad Rizki Bayuaji
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, false otherwise.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public boolean checkDone(){
        return this.isDone;
    }


    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Marks the task as done.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public void setMark() {
        isDone = true;
    }

    /**
     * Unmarks the task, setting its status to not done.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public void setUnMark() {
        isDone = false;
    }

    /**
     * Retrieves the status icon of the task, indicating whether it's done or not.
     *
     * @return A string representing the status icon.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     *
     * @author Muhammad Rizki Bayuaji
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
