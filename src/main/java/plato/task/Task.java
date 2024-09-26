package plato.task;

/**
 * Basic class of a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with name.
     *
     * @param description name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a task uncompleted
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Formats the item and output it in save format.
     *
     * @return A String containing the items in save format.
     */
    public String saveFile() {
        return "U" + "|" + done() + "|" + this.description;
    }

    /**
     * Returns the completion status of a task.
     *
     * @return A String containing X or O to indicate the completion of a task.
     */
    public String done() {
        if (this.isDone) {
            return "x";
        } else {
            return "o";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Displays the completion status in a nicely formatted String.
     *
     * @return A String indicating the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks what type of task this is .
     * @return The type of Task it is
     */
    public Actions getType() {
        return null;
    }

}

