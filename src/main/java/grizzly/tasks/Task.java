package grizzly.tasks;

/**
 * This class implements functionality for Tasks in the bot.
 *
 * @author delishad21
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates Task object.
     *
     * @param isDone Marks if task is completed.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks task as done by setting isDone to true.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Marks task as undone by setting isDone to false
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Prints task marker, private method used by toString.
     *
     * @return marker depending on whether this task is completed.
     */
    private String statusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Gets description value.
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns Task as a viewable String
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.statusIcon() + "] " + this.description;
    }

    /**
     * Converts Task into a String for saving in save file.
     *
     * @return String
     */
    public String toSave() {
        return "[" + this.statusIcon() + "]|" + this.description;
    }
}
