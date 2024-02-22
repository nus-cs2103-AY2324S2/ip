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
     * @param taskList The task list to which the task will be added.
     * @param description The description of the to-do task.
     * @return A string representing the result of adding the ToDo task.
     * @throws GeorgieException If an error occurs while adding the task.
     */
    public static String addToDoTask(TaskList taskList, String description) throws GeorgieException {
        assert ("todo " + description).length() > "todo ".length() : "Input not handled properly";

        taskList.addTask(new ToDo(description));

        int newSize = taskList.size();
        assert newSize > 0 : "Task not added successfully";

        String result = "Got it. I've added this task:\n" +
                taskList.getTask(taskList.size() - 1).getStatusIcon() + "\n" +
                "Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.";
        System.out.println(result);
        return result;
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
