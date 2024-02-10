package duke;

/**
 * Represents a todo task, a subclass of Task.
 * Todo tasks are basic tasks without any date/time attached.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     * The string includes the type of task (represented by "[T]"), the status (done or not done),
     * and the task's description.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatus() + " " + super.toString();
    }

    /**
     * Returns the string representation of the Todo task in a format suitable for saving to a file.
     * The format includes the task type, status, and description.
     *
     * @return The string representation of the Todo task in a save format.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + super.description;
    }

    /**
     * Static method to create a Todo task from its saved format.
     * The method parses a string array containing the saved information of the task and
     * reconstructs the Todo task from this information.
     *
     * @param info The array of strings containing the task's saved data.
     * @return A Todo task reconstructed from the saved data.
     */
    public static Todo fromSaveFormat(String[] info) {
        Todo loadedTask = new Todo(info[2]);
        boolean isSavedTaskDone = info[1].equals("1");
        if (isSavedTaskDone) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }
}
