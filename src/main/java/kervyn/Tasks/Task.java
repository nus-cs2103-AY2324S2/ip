package kervyn.Tasks;

/**
 * Represents a task with a description, status, and type.
 * The type can be TODO, DEADLINE, or EVENT.
 */
public class Task {
    private String description;
    private boolean status;
    private Type type;

    /**
     * Constructs a new Task.
     *
     * @param description The description of the task.
     * @param status The status of the task, where true indicates completed and false indicates not completed.
     * @param type The type of the task, which can be TODO, DEADLINE, or EVENT.
     */
    public Task(String description, boolean status, Type type) {
        this.description = description;
        this.status = status;
        this.type = type;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task, where true indicates completed and false indicates not completed.
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Gets the first letter of the task type in uppercase.
     *
     * @return The first letter of the task type in uppercase.
     */
    public char getCapitalType() {
        switch(this.type) {
            case TODO:
                return 'T';
            case DEADLINE:
                return 'D';
            case EVENT:
                return 'E';
        }
        return ' ';
    }

    /**
     * Toggles the status of the task between complete and incomplete.
     */
    public void updateStatus() {
        this.status = !this.status;
    }

    /**
     * Converts the task to a string representation, including type, status, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        char check = this.getStatus() ? 'X' : ' ';
        char letterType = this.getCapitalType();
        switch (letterType) {
            case 'T':
                return "[" + letterType + "] " + "[" + check + "] " + this.description;
            case 'D':
                Deadline deadlineTask = (Deadline) this;
                return "[" + letterType + "] " + "[" + check + "] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDeadline() + ")";
            case 'E':
                Event eventTask = (Event) this;
                return "[" + letterType + "] " + "[" + check + "] " + eventTask.getDescription() + " (from: " + eventTask.getStartDate() + " to: " + eventTask.getEndDate() + ")";
        }
        return "";
    }
}
