package dav;

/**
 * Represents a generic task with a description and completion status.
 */
class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing the completion status of the task.
     * @return Status icon as a string ("[X]" for completed, "[ ]" for not completed).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets the description representing the task.
     * @return Description of the task as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Converts the task to a string for data storage.
     * @return Formatted string for data storage.
     */
    public String toDataString() {
        return "";
    }

    /**
     * Parses task data string and returns a Task object.
     * @param data Data string representing the task.
     * @return Task object if parsing is successful, otherwise null.
     */
    public static Task parseTask(String data) {
        if (data.startsWith("T")) {
            return TodoTask.parseTask(data);
        } else if (data.startsWith("D")) {
            return DeadlineTask.parseTask(data);
        } else if (data.startsWith("E")) {
            return EventTask.parseTask(data);
        }

        return null;
    }

    /**
     * Converts the task to a string for display.
     * @return Formatted string for display.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

