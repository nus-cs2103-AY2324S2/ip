public class Task {
    private String description;
    private int taskNumber;
    private boolean isComplete;

    // Private constructor to create a new Task with the given description
    private Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Static factory method to create a new Task with the given description
     * @param description Description of the task
     * @return A new task object with the specified description
     */
    public static Task createTask(String description) {
        return new Task(description);
    }

    // The description of the task
    public String getDescription() {
        return description;
    }


    // Mark task as done
    public void markAsDone() {
        this.isComplete = true;
    }

    // True if the task is done, else no
    public boolean isDone() {
        return isComplete;
    }
}