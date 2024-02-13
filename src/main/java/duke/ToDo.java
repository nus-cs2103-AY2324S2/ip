package duke;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description Description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Adds a new ToDo task to the specified task list with the provided description.
     *
     * @param taskList    The task list to which the task will be added.
     * @param description The description of the to-do task.
     * @throws DukeException If an error occurs while adding the task.
     */
    public static void addToDoTask(TaskList taskList, String description) throws DukeException {
        taskList.addTask(new ToDo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.size() - 1).getStatusIcon());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    /**
     * Retrieves the status icon and description of the ToDo task.
     *
     * @return A string representing the status icon, type, and description of the ToDo task.
     */
    @Override
    public String getStatusIcon() {
        return "[T] " + super.getStatusIcon() + " " + description;
    }

    /**
     * Converts the ToDo task to a string representation for storing in a file.
     *
     * @return A string representing the ToDo task in file-compatible format.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
