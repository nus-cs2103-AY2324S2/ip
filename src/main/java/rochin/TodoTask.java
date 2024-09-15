package rochin;

/**
 * Represent a Todo task.
 */
class TodoTask extends Task {

    /**
     * Constructs a TodoTask with the given description.
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Gets the additional details of the todo task.
     * @return An empty string since todo tasks do not have additional details.
     */
    @Override
    public String getAdditionalDetails() {
        return "";
    }

    /**
     * Gets the type of the task.
     * @return The type of the task (in this case, "T" for TodoTask).
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return a string representation of the task to be stored in the file.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s", getTaskType(), isDone ? 1 : 0, description);
    }
}

