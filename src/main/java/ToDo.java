/**
 * Task with no time limit attached.
 *
 * @author KohGuanZeh
 */
public class ToDo extends Task {
    // Format to create todo task in program.
    public static String CREATE_TODO_FORMAT = "todo <task-name>";

    /**
     * Creates a new task with no time limit attached.
     *
     * @param description Description of task.
     */
    private ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new ToDo task based on user input.
     *
     * @param input Input String after the "todo" command.
     * @return New ToDo task.
     * @throws TaskException Exception when task cannot be created.
     */
    public static ToDo createToDo(String input) throws TaskException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new TaskException("Error. Unable to create task.\nFormat: " + ToDo.CREATE_TODO_FORMAT);
        }
        return new ToDo(input);
    }

    /**
     * Returns the task, task type and its completion status.
     * Tasks of ToDo type are marked with [T].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status and description.
     */
    @Override
    public String getTaskInformation() {
        return "[T]" + super.getTaskInformation();
    }
}
