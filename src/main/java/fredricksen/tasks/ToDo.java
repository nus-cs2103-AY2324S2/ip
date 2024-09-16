package fredricksen.tasks;

/**
 * Represents a "ToDo" class, which extends the Task base class.
 * A "ToDo" Task is a task that creates a "ToDo" object with the
 * task description, the task type, and the status of the task isDone,
 * represented by boolean values.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo instance with the specified task description,
     * the task type, and the status of the task isDone, represented by boolean values.
     *
     * @param task The task description as per user input.
     * @param type The task type.
     * @param isDone The status of the task isDone, represented by boolean values.
     */
    public ToDo(String task, String type, boolean isDone) {
        super(task, type, isDone);
    }

    /**
     * Get the description of the ToDo task from user input command.
     *
     * @param fullCommand The full command that the user input.
     * @return A String of the description of the command.
     */
    public String getTodoDescription(String fullCommand) {
        int todoIndex = fullCommand.indexOf("todo");
        return fullCommand.substring(todoIndex + 5);
    }

    @Override
    public String toString() {
        return super.toString() + getTodoDescription(this.getTask());
    }
}
