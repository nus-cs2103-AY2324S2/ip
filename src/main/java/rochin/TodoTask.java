package rochin;

/**
 * Represent a Todo task.
 */
class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String getAdditionalDetails() {
        return "";
    }

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

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s", getTaskType(), isDone ? 1 : 0, description);
    }
}

