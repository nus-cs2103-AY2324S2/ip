package riri;
/**
 * This class models the Todo objects in the task list. This class extends from the parent Task class
 */
public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    /**
     * Parses a formatted string to create a Todo object.
     *
     * This static method is designed to create a Todo object from a string formatted
     * in a specific way. The expected format is "[T][ ] task description".
     * The method extracts the task description and completion status from the input
     * string to instantiate a Todo object.
     *
     * @param inputString The formatted string to parse into a Todo object.
     *                    It should follow the pattern "[T][ ] task description".
     *
     * @return A Todo object representing the parsed task with its completion status.
     *
     */
    public static Todo parseTodoFromString(String inputString) {
        // Assuming the inputString is formatted as "[T][ ] task description"
        String taskDescription = inputString.substring(7); // Extract the task description
        Todo todo = new Todo(taskDescription.trim());

        // Check the status and mark the Todo as done if needed
        if (inputString.charAt(4) == 'X') {
            todo.markDone();
        }
        return todo;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
