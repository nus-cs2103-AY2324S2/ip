package duke;

/**
 * ToDo Class is a type of task the user can create.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo Class
     * @param Task
     * @param taskType
     */
    public ToDo(String Task, String taskType) {
        super(Task,taskType);
    }

    /**
     * Informs the user a new ToDo has been created.
     * @return String informing a new ToDo has been created.
     */
    public String announcement() {
        return "New ToDo created!";
    }

    /**
     * Returns a string representation of the ToDo for the user to view.
     * @return String representation of the ToDo
     */
    public String toString() {
        return this.getTaskType() + this.getStatus() + " " + this.getTask();
    }

    /**
     * Returns a string representation of the ToDo for txt file saving purposes.
     * @return String representation of the ToDo for txt file saving purposes.
     */
    public String saveString() {
        return this.getTaskTypeSingle() + "|" + this.getStatusBinary() + "|" + this.getTask();
    }
}
