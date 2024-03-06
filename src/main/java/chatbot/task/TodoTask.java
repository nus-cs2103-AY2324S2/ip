package chatbot.task;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {

    /**
     * Constructor for TodoTask.
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructor for TodoTask.
     * @param description The description of the task.
     * @param isCompleted Whether the task is completed.
     */
    public TodoTask(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        return "[T]" + status + " " + super.getDescription();
    }

    @Override
    public String exportToSave() {
        String status = super.isCompleted() ? "1" : "0";
        return "T," + status + "," + super.getDescription();
    }

}
