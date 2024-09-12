package kervyn.Tasks;

/**
 * Represents a task with a description, status, and type.
 * The type can be TODO, DEADLINE, or EVENT.
 */
public class Task {
    private String description;
    private boolean isCompleted;
    private Type type;

    /**
     * Constructs a new Task.
     *
     * @param description The description of the task.
     * @param status The status of the task, where true indicates completed and false indicates not completed.
     * @param type The type of the task, which can be TODO, DEADLINE, or EVENT.
     */
    public Task(String description, boolean isCompleted, Type type) {
        this.description = description;
        this.isCompleted = isCompleted;
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
    public boolean getCompleted() {
        return this.isCompleted;
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
        this.isCompleted = !this.isCompleted;
    }

    /**
     * Converts the task to a string representation, including type, status, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        char letterType = this.getCapitalType();
        switch (letterType) {
            case 'T':
                ToDo toDoTask = (ToDo) this;
                return toDoTask.toString();
            case 'D':
                Deadline deadlineTask = (Deadline) this;
                return deadlineTask.toString();
            case 'E':
                Event eventTask = (Event) this;
                return eventTask.toString();
        }
        return "";
    }
}
